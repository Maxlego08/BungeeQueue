package fr.maxlego08.bungeequeue.listener;

import java.util.List;
import java.util.stream.Collectors;

import fr.maxlego08.bungeequeue.BungeeQueue;
import fr.maxlego08.bungeequeue.Config;
import fr.maxlego08.bungeequeue.utils.Motd;
import net.md_5.bungee.api.AbstractReconnectHandler;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.ServerPing.PlayerInfo;
import net.md_5.bungee.api.ServerPing.Players;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerListener implements Listener {

	private final BungeeQueue plugin;

	public ServerListener(BungeeQueue plugin) {
		super();
		this.plugin = plugin;
	}

	@EventHandler
	public void onQuit(PlayerDisconnectEvent event) {

		plugin.getManager().playerDisconnect(event.getPlayer());

	}

	@EventHandler(priority = 64)
	public void onServerListPing(ProxyPingEvent event) {

		if (!Config.useMotd)
			return;

		ServerPing ping = event.getResponse();

		int online = ping.getPlayers().getOnline();
		int max = ping.getPlayers().getMax();

		Motd motd = online >= max ? Config.fullMotd : plugin.isMaintenance() ? Config.maintenanceMotd : Config.motd;

		// Show player
		Players players = ping.getPlayers();
		List<String> messages = motd.getMessages().stream().map(str -> {
			return replace(str, online, max);
		}).collect(Collectors.toList());

		PlayerInfo[] sample = new PlayerInfo[messages.size()];
		for (int i = 0; i < sample.length; i++) {
			sample[i] = new PlayerInfo(messages.get(i), "");
		}

		// Motd
		String strMotd = replace(motd.getUpMessage(), online, max) + "\n" + replace(motd.getDownMessage(), online, max);
		ping.setDescriptionComponent(new TextComponent(strMotd));

		players.setSample(sample);
		ping.setPlayers(players);
		event.setResponse(ping);

	}

	/**
	 * 
	 * @param str
	 * @param online
	 * @param max
	 * @return
	 */
	private String replace(String str, int online, int max) {
		str = str.replace("%max%", String.valueOf(max));
		return str.replace("%online%", String.valueOf(online));
	}

	@EventHandler
	public void onServerKickEvent(PreLoginEvent event) {

		if (!Config.useMotd)
			return;

		if (Config.whitelistUsers.contains(event.getConnection().getName()))
			return;

		if (plugin.isMaintenance()) {

			event.setCancelled(true);
			event.setCancelReason(new TextComponent(Config.kickMessageMaintenance));

		}
	}

	@EventHandler
	public void onServer(PluginMessageEvent event) {

		String channel = event.getTag();
		if (channel.equals(Config.channelName)) {
			ProxiedPlayer player = plugin.getProxy().getPlayer(event.getReceiver().toString());
			plugin.getManager().joinQueue(player);
		}

	}

	@EventHandler
	public void onServerKickEvent(ServerKickEvent ev) {

		if (!Config.useKickDefaultServer)
			return;

		ServerInfo kickedFrom = null;
		if (ev.getPlayer().getServer() != null)
			kickedFrom = ev.getPlayer().getServer().getInfo();
		else if (this.plugin.getProxy().getReconnectHandler() != null)
			kickedFrom = this.plugin.getProxy().getReconnectHandler().getServer(ev.getPlayer());
		else
			kickedFrom = AbstractReconnectHandler.getForcedHost(ev.getPlayer().getPendingConnection());
		ServerInfo kickTo = ProxyServer.getInstance().getServerInfo(Config.defaultKickServer);
		if (kickedFrom == null || (!kickedFrom.equals(kickTo))) {
			ev.setCancelled(true);
			ev.setCancelServer(kickTo);
		}

	}

}
