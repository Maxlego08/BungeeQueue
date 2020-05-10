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

# Video

[![video](https://img.youtube.com/vi/dRAf7EXnPCg/0.jpg)](https://www.youtube.com/watch?v=dRAf7EXnPCg)

# Config
```json
{
  "onlineBuffer": 2,
  "queueSpeed": 5,
  "targetServer": "faction",
  "defaultsSevers": [
    "lobby",
    "lobby1"
  ],
  "useMotd": false,
  "motdSpeedMaintenanceTask": 10000,
  "maintenanceServer": "lobby",
  "motd": {
    "upMessage": "§6§lPrideNetwork §6✭ §eFaction §c100% §d§lFarm2Win §6✭ §7(§e1.8§7)",
    "downMessage": "§f§l» §fOuverture bientôt",
    "messages": [
      "§7§m-------------------------------------------",
      "",
      "§f» §6PrideNetwork§e le serveur §bFaction§d 100% Farm2Win",
      "§f» §eDe la §a1.8.9§e à la §a1.15.2 §e!",
      "",
      "§f» §eDiscord§7: §bhttps://discord.gg/3wjkeJE",
      "§f» §eSite§7: §bhttps://pridenetwork.net/",
      "",
      "§f» §e§lRejoins les §6§l%online% joueurs §e§ldéjà connectés !",
      "",
      "§7§m-------------------------------------------"
    ]
  },
  "maintenanceMotd": {
    "upMessage": "§6§lPrideNetwork §6✭ §eFaction §c100% §d§lFarm2Win §6✭ §7(§e1.8§7)",
    "downMessage": "§f§l» §cLe serveur est en maintenance",
    "messages": [
      "§7§m-------------------------------------------",
      "",
      "§f» §6PrideNetwork§e le serveur §bFaction§d 100% Farm2Win",
      "§f» §eDe la §a1.8.9§e à la §a1.15.2 §e!",
      "",
      "§f» §eDiscord§7: §bhttps://discord.gg/3wjkeJE",
      "§f» §eSite§7: §bhttps://pridenetwork.net/",
      "",
      "§f» §e§lRejoins les §6§l%online% joueurs §e§ldéjà connectés !",
      "",
      "§7§m-------------------------------------------"
    ]
  },
  "fullMotd": {
    "upMessage": "§6§lPrideNetwork §6✭ §eFaction §c100% §d§lFarm2Win §6✭ §7(§e1.8§7)",
    "downMessage": "§f§l» §cLe serveur est actuellement plein !",
    "messages": [
      "§7§m-------------------------------------------",
      "",
      "§f» §6PrideNetwork§e le serveur §bFaction§d 100% Farm2Win",
      "§f» §eDe la §a1.8.9§e à la §a1.15.2 §e!",
      "",
      "§f» §eDiscord§7: §bhttps://discord.gg/3wjkeJE",
      "§f» §eSite§7: §bhttps://pridenetwork.net/",
      "",
      "§f» §e§lRejoins les §6§l%online% joueurs §e§ldéjà connectés !",
      "",
      "§7§m-------------------------------------------"
    ]
  },
  "whitelistUsers": [
    "Maxlego08",
    "AzartoxHD",
    "tanguyplayer"
  ],
  "kickMessageMaintenance": "§cLe serveur est actuellement en maintenance",
  "prefix": "§8(§ePrideNetwork§8)",
  "defaultMotd": "A Minecraft Server",
  "onliPlayerCanUse": "§cVous ne pouvez pas faire cette commande depuis la console.",
  "mustBeLogin": "§cVous ne pouvez pas rejoindre la liste d'attente pour le moment.",
  "wrongServer": "§cVous ne pouvez pas faire commande ici.",
  "alreadyInQueue": "§cVous êtes déjà dans la liste d'attente pour se connecter au serveur.",
  "queueInformation": "§eVous êtes à la place §6%s §esur §6%s§e, §etemps §ed'attente §eestité §eà §6%s§e.",
  "queueJoin": "§eVous venez de rejoindre la file §6%s§e, §Etemps §Ed'attente §Eestimé §eà §6%s§e.",
  "queueJoinByPass": "§eUn joueur prioritaire vient de vous passer devant.",
  "queueLeavePlayer": "§eUn joueur vient de déconnecter, vous êtes passez à la position §6%s §esur §6%s§e.",
  "downServer": {
    "title": "§6Liste d'attente",
    "subTitle": "§eLe serveur est actuellement indisponible"
  },
  "whitelistServer": {
    "title": "§6Liste d'attente",
    "subTitle": "§eLe serveur est actuellement en maintenance."
  },
  "joinServer": {
    "title": "§f§kII§e Bienvenue §f§kII",
    "subTitle": "§eBienvenue sur §6PrideNetwork"
  },
  "queueMove": {
    "title": "§6Liste d'attente",
    "subTitle": "§eVous êtes à la position §6%position% §esur §6%s"
  }
}
```

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
