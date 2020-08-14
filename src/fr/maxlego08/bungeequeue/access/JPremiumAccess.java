package fr.maxlego08.bungeequeue.access;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.jakub.premium.event.AsyncUserPostLoginEvent;
import com.jakub.premium.event.AsyncUserPostRegisterEvent;
import fr.maxlego08.bungeequeue.QueueAccess;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.event.EventHandler;

public class JPremiumAccess implements QueueAccess {

	private List<UUID> accessPlayers = new ArrayList<UUID>();
	
	@Override
	public boolean canJoinQueue(ProxiedPlayer player) {
		return accessPlayers.contains(player.getUniqueId());
	}
	
	@EventHandler
	public void onLogin(AsyncUserPostLoginEvent event) {
		UUID uuid = event.getUser().getUniqueId();
		if (!accessPlayers.contains(uuid))
			accessPlayers.add(uuid);
	}

	@EventHandler
	public void onRegister(AsyncUserPostRegisterEvent event) {
		UUID uuid = event.getUser().getUniqueId();
		if (!accessPlayers.contains(uuid))
			accessPlayers.add(uuid);
	}
	
	@EventHandler
	public void onQuit(PlayerDisconnectEvent event) {
		UUID uuid = event.getPlayer().getUniqueId();
		if (accessPlayers.contains(uuid))
			accessPlayers.remove(uuid);
	}

}
