package fr.maxlego08.bungeequeue;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.config.ServerInfo;

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
	
	public void run(){
		
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
		
		//Si il n'y a personne dans la queue
		if (queue.size() == 0)
			return;
		
		ServerInfo info = plugin.getProxy().getServerInfo(Config.targetServer);
		
		//Si on ne trouve pas le serveur on stop
		if (info == null)
			return;
		
		//On ping ensuite le serveur
		info.ping(new Callback<ServerPing>() {
			
			@Override
			public void done(ServerPing server, Throwable throwable) {
				
			}
			
		});
	}
	
}
