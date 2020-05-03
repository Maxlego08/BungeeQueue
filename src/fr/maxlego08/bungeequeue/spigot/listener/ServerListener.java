package fr.maxlego08.bungeequeue.spigot.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListener implements Listener {

	@EventHandler
	public void onPing(ServerListPingEvent event) {

		if (Bukkit.getServer().hasWhitelist())
			event.setMotd("maintenance");
		else
			event.setMotd("ouvert");

	}

}
