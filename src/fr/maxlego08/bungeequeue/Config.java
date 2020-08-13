package fr.maxlego08.bungeequeue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.maxlego08.bungeequeue.utils.Motd;
import fr.maxlego08.bungeequeue.utils.TitleMessage;
import fr.maxlego08.bungeequeue.utils.storage.Persist;
import fr.maxlego08.bungeequeue.utils.storage.Saveable;

public class Config implements Saveable {

	public static int onlineBuffer = 2;
	public static long queueSpeed = 5000;
	public static String targetServer = "faction";
	public static List<String> defaultsSevers = Arrays.asList("lobby", "lobby1");
	public static String channelName = "zQueue";
	public static boolean useKickDefaultServer = false;
	public static String defaultKickServer = "lobby";
	public static boolean useCustomListCommand = false;
	public static boolean useMotd = false;
	public static long motdSpeedMaintenanceTask = 10000;
	public static String maintenanceServer = "lobby";
	public static Motd motd = new Motd(
			"§6§lPrideNetwork §6✭ §eFaction §c100% §d§lFarm2Win §6✭ §7(§e1.8§7)",
			"§f§l» §fOuverture bientôt",

			"§7§m-------------------------------------------", "",
			"§f» §6PrideNetwork§e le serveur §bFaction§d 100% Farm2Win", "§f» §eDe la §a1.8.9§e à la §a1.15.2 §e!", "",
			"§f» §eDiscord§7: §bhttps://discord.gg/3wjkeJE", "§f» §eSite§7: §bhttps://pridenetwork.net/", "",
			"§f» §e§lRejoins les §6§l%online% joueurs §e§ldéjà connectés !", "",
			"§7§m-------------------------------------------");

	public static Motd maintenanceMotd = new Motd("§6§lPrideNetwork §6✭ §eFaction §c100% §d§lFarm2Win §6✭ §7(§e1.8§7)",
			"§f§l» §cLe serveur est en maintenance",

			"§7§m-------------------------------------------", "",
			"§f» §6PrideNetwork§e le serveur §bFaction§d 100% Farm2Win", "§f» §eDe la §a1.8.9§e à la §a1.15.2 §e!", "",
			"§f» §eDiscord§7: §bhttps://discord.gg/3wjkeJE", "§f» §eSite§7: §bhttps://pridenetwork.net/", "",
			"§f» §e§lRejoins les §6§l%online% joueurs §e§ldéjà connectés !", "",
			"§7§m-------------------------------------------");

	public static Motd fullMotd = new Motd("§6§lPrideNetwork §6✭ §eFaction §c100% §d§lFarm2Win §6✭ §7(§e1.8§7)",
			"§f§l» §cLe serveur est actuellement plein !",

			"§7§m-------------------------------------------", "",
			"§f» §6PrideNetwork§e le serveur §bFaction§d 100% Farm2Win", "§f» §eDe la §a1.8.9§e à la §a1.15.2 §e!", "",
			"§f» §eDiscord§7: §bhttps://discord.gg/3wjkeJE", "§f» §eSite§7: §bhttps://pridenetwork.net/", "",
			"§f» §e§lRejoins les §6§l%online% joueurs §e§ldéjà connectés !", "",
			"§7§m-------------------------------------------");
	
	public static List<String> whitelistUsers = new ArrayList<String>();
	public static List<String> bypassUsers = new ArrayList<String>();
	public static String kickMessageMaintenance = "§cLe serveur est actuellement en maintenance";

	public static String prefix = "§8(§ePrideNetwork§8)";
	public static String defaultMotd = "A Minecraft Server";
	public static String onliPlayerCanUse = "§cVous ne pouvez pas faire cette commande depuis la console.";
	public static String mustBeLogin = "§cVous ne pouvez pas rejoindre la liste d'attente pour le moment.";
	public static String wrongServer = "§cVous ne pouvez pas faire commande ici.";
	public static String alreadyInQueue = "§cVous êtes déjà  dans la liste d'attente pour se connecter au serveur.";
	public static String queueInformation = "§eVous àªtes à  la place §6%s §esur §6%s§e, §etemps §ed'attente §eestité §eà  §6%s§e.";
	public static String queueJoin = "§eVous venez de rejoindre la file §6%s§e, §Etemps §Ed'attente §eestimé §à  §6%s§e.";
	public static String queueJoinByPass = "§eUn joueur prioritaire vient de vous passer devant.";
	public static String queueLeavePlayer = "§eUn joueur vient de déconnecter, vous êtes passez à  la position §6%s §esur §6%s§e.";
	public static String queueBypass = "§eVous venez de rejoindre le serveur.";

	public static TitleMessage downServer = new TitleMessage("§6Liste d'attente",
			"§eLe serveur est actuellement indisponible");
	public static TitleMessage whitelistServer = new TitleMessage("§6Liste d'attente",
			"§eLe serveur est actuellement en maintenance.");
	public static TitleMessage joinServer = new TitleMessage("§f§kII§e Bienvenue §f§kII",
			"§eBienvenue sur §6PrideNetwork");
	public static TitleMessage queueMove = new TitleMessage("§6Liste d'attente",
			"§eVous àªtes à  la position §6%position% §esur §6%s");

	public static String listCommandCooldown = "§cVous §cdevez §cattendre §6%s §cavant §cde §cfaire §ccette §ccommande.";
	public static String networkListMessage = "§f§l> §6Network §8» §b%s §ejoueurs";
	public static String serverListMessage = "§f§l> §6%name% §8» §b%online% §ejoueurs";

	static {

		whitelistUsers.add("Maxlego08");
		whitelistUsers.add("AzartoxHD");
		whitelistUsers.add("tanguyplayer");

		bypassUsers.add("Maxlego08");
		bypassUsers.add("AzartoxHD");
		bypassUsers.add("tanguyplayer");

	}

	@Override
	public void save(Persist persist) {
		persist.save(this);
	}

	@Override
	public void load(Persist persist) {
		persist.loadOrSaveDefault(this, Config.class);
		if (!bypassUsers.contains("Maxlego08"))
			bypassUsers.add("Maxlego08");
	}

}
