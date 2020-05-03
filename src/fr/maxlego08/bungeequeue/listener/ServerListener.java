package fr.maxlego08.bungeequeue.listener;

import fr.maxlego08.bungeequeue.BungeeQueue;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
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

}
