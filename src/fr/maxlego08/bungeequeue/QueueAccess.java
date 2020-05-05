package fr.maxlego08.bungeequeue;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;

public interface QueueAccess extends Listener {

	/**
	 * Determines if a player can connect to the waiting queue
	 * @param player
	 * @return true if player can join
	 */
	boolean canJoinQueue(ProxiedPlayer player);
	
}
