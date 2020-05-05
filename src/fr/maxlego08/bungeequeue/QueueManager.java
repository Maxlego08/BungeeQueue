package fr.maxlego08.bungeequeue;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import fr.maxlego08.bungeequeue.utils.TimerBuilder;
import fr.maxlego08.bungeequeue.utils.TitleMessage;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class QueueManager {

	private final BungeeQueue plugin;
	private final Map<UUID, Player> players = new HashMap<>();
	private final Deque<Player> queue = new LinkedList<>();
	private boolean isRunning = false;
	private final int queueSpeed = 5;
	private final TimeUnit timeUnit = TimeUnit.SECONDS;

	public QueueManager(BungeeQueue plugin) {
		super();
		this.plugin = plugin;
	}

	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * 
	 * @param isRunning
	 */
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void run() {

		if (isRunning)
			return;

		ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);

		isRunning = true;

		ses.scheduleAtFixedRate(() -> {

			// On désactive la task
			if (!isRunning) {
				ses.shutdown();
				return;
			}

			this.connectPlayers();

		}, queueSpeed, queueSpeed, timeUnit);

	}

	private void connectPlayers() {

		// Si il n'y a personne dans la queue
		if (queue.size() == 0)
			return;

		ServerInfo info = plugin.getProxy().getServerInfo(Config.targetServer);

		// Si on ne trouve pas le serveur on stop
		if (info == null)
			return;

		// On ping ensuite le serveur
		info.ping(new Callback<ServerPing>() {

			@Override
			public void done(ServerPing server, Throwable throwable) {

				// Si le serveur est null alors il n'est pas en ligne.
				if (server == null) {

					title(Config.downServer, 0, 5 + 20 * queueSpeed, 0);

				} else {

					// Si le serveur est en maintenance
					String motd = TextComponent.toLegacyText(server.getDescriptionComponent());
					if (motd.contains("maintenance") || motd.contains(Config.defaultMotd)) {
						title(Config.whitelistServer, 0, 5 + 20 * queueSpeed, 0);
						return;
					}

					int onlinePlayers = server.getPlayers().getOnline();
					int maxPlayer = server.getPlayers().getMax();

					// S'il y a moins de joueur que de max player, - 2 car il y
					// a 2
					// joueurs qui peuvent se connecter en même temps
					if (onlinePlayers < maxPlayer - Config.onlineBuffer) {

						Player player = queue.pollFirst();
						player.getPlayer().connect(info);
						player.setQueuePosition(0);
						player.title(Config.joinServer, 10, 30, 10);

						players.values().forEach(Player::removeOne);
						title(Config.queueMove, 0, 5 + 20 * queueSpeed, 0, queue.size());

					}

				}

			}

		});
	}

	private void title(TitleMessage message, int fadeIn, int stay, int fadeOut, Object... args) {
		players.values().forEach(player -> {
			if (player.isWaiting()) {
				TitleMessage newMessage = new TitleMessage(message.getTitle(),
						message.getSubTitle().replace("%position%", String.valueOf(player.getQueuePosition())));
				player.title(newMessage, fadeIn, stay, fadeOut, args);
			}
		});
	}

	/**
	 * 
	 * @param player
	 * @return
	 */
	public Player getPlayer(ProxiedPlayer player) {
		UUID uuid = player.getUniqueId();

		if (players.containsKey(uuid))
			return players.get(uuid);

		Player createPlayer = new Player(uuid);
		players.put(uuid, createPlayer);
		return createPlayer;

	}

	/**
	 * 
	 * @param proxiedPlayer
	 */
	public void joinQueue(ProxiedPlayer proxiedPlayer) {

		Player player = getPlayer(proxiedPlayer);

		// Si le joueur est déjà dans la liste d'attente
		if (player.isWaiting()) {

			player.action(Config.alreadyInQueue);
			String cooldown = TimerBuilder.getStringTime(player.getQueuePosition() * queueSpeed);
			player.message(Config.queueInformation, player.getQueuePosition(), queue.size(), cooldown);
			return;
		}

		boolean isAccess = proxiedPlayer.hasPermission("bypass.queue");

		// Système d'ajout dans la queue
		if (isAccess) {

			// On va ensuite décaler tous les players
			players.values().forEach(Player::addOne);

			// On ajout ensute le joueur
			queue.addFirst(player);
			player.setQueuePosition(1);

		} else {
			queue.addLast(player);
			player.setQueuePosition(queue.size());
		}

		String cooldown = TimerBuilder.getStringTime(player.getQueuePosition() * queueSpeed);
		player.message(Config.queueJoin, isAccess ? "prioritaire" : "normal", cooldown);

	}

	/**
	 * 
	 * @param player
	 */
	public void playerDisconnect(ProxiedPlayer proxiedPlayer) {

		UUID uuid = proxiedPlayer.getUniqueId();
		if (players.containsKey(uuid)) {

			Player player = getPlayer(proxiedPlayer);
			if (player.isWaiting()) {

				int position = player.getQueuePosition();
				queue.remove(player);

				queue.forEach(currentPlayer -> currentPlayer.updatePosition(position, queue.size()));

			}

		}

	}

}
