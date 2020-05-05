# BungeeQueue

Waiting list system for your bungeecord server.

# Features

* Create a waiting list for connection to your server
* If the server is not online or if the server is in whitelist then the waiting list will not work
* Bypass system for administrators

# Installation

1. Stop your bungeecord server and the server or players should go
2. Start your bungeecord and spigot server
3. Modify the configuration file on the bungeecord side
4. Make /queue reload in the console of your bungeecord to reload the configuration
5. Here you can use the plugin !

# Add your QueueAccess

To give or not access to the queue you can create your own QueueAccess. Here is an example with JPremium
```java
public class JPremiumAccess implements QueueAccess {

	private List<UUID> accessPlayers = new ArrayList<UUID>();
	
	@Override
	public boolean canJoinQueue(ProxiedPlayer player) {
		return accessPlayers.contains(player.getUniqueId());
	}
	
	@EventHandler
	public void onLogin(UserLoginEvent event) {
		UUID uuid = event.getUser().getPlayer().getUniqueId();
		if (!accessPlayers.contains(uuid))
			accessPlayers.add(uuid);
	}

	@EventHandler
	public void onLogin(UserRegisterEvent event) {
		UUID uuid = event.getUser().getPlayer().getUniqueId();
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
```
You just have to define it in the BungeeQueue class.
