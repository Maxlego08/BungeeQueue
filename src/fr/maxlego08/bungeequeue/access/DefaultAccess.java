package fr.maxlego08.bungeequeue.access;

import fr.maxlego08.bungeequeue.QueueAccess;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class DefaultAccess implements QueueAccess {

	@Override
	public boolean canJoinQueue(ProxiedPlayer player) {
		// TODO Auto-generated method stub
		return true;
	}

}
