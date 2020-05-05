package fr.maxlego08.bungeequeue;

import fr.maxlego08.bungeequeue.utils.TitleMessage;
import fr.maxlego08.bungeequeue.utils.storage.Persist;
import fr.maxlego08.bungeequeue.utils.storage.Saveable;

public class Config implements Saveable {

	public static int onlineBuffer = 2;
	public static int queueSpeed = 5;
	public static String targetServer = "faction";
	public static String defaultServer = "lobby";
	
	public static String prefix = "§8(§ePrideNetwork§8)";
	public static String defaultMotd = "A Minecraft Server";
	public static String onliPlayerCanUse = "§cVous ne pouvez pas faire cette commande depuis la console.";
	public static String mustBeLogin = "§cVous ne pouvez pas rejoindre la liste d'attente pour le moment.";
	public static String wrongServer = "§cVous ne pouvez pas faire commande ici.";
	public static String alreadyInQueue = "§cVous êtes déjà dans la liste d'attente pour se connecter au serveur.";
	public static String queueInformation = "§eVous êtes à la place §6%s §esur §6%s§e, §etemps §ed'attente §eestité §eà §6%s§e.";
	public static String queueJoin = "§eVous venez de rejoindre la file §6%s§e, §Etemps §Ed'attente §Eestimé §eà §6%s§e.";
	public static String queueJoinByPass = "§eUn joueur prioritaire vient de vous passer devant.";
	public static String queueLeavePlayer = "§eUn joueur vient de déconnecter, vous êtes passez à la position §6%s §esur §6%s§e.";
	
	public static TitleMessage downServer = new TitleMessage("§6Liste d'attente", "§eLe serveur est actuellement indisponible");
	public static TitleMessage whitelistServer = new TitleMessage("§6Liste d'attente", "§eLe serveur est actuellement en maintenance.");
	public static TitleMessage joinServer = new TitleMessage("§f§kII§e Bienvenue §f§kII", "§eBienvenue sur §6PrideNetwork");
	public static TitleMessage queueMove = new TitleMessage("§6Liste d'attente", "§eVous êtes à la position §6%position% §esur §6%s");
	
	@Override
	public void save(Persist persist) {
		persist.save(this);
	}

	@Override
	public void load(Persist persist) {
		persist.loadOrSaveDefault(this, Config.class);
	}

}
