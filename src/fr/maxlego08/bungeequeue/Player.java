package fr.maxlego08.bungeequeue;

import java.util.UUID;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Player {

	private final UUID uuid;
	private int queuePosition;

	public Player(UUID uuid) {
		this.uuid = uuid;
	}

	public ProxiedPlayer getPlayer() {
		return ProxyServer.getInstance().getPlayer(uuid);
	}

	/**
	 * @return the isWaiting
	 */
	public boolean isWaiting() {
		return queuePosition > 0;
	}

	/**
	 * Permet d'envoyer un message au joueur
	 * 
	 * @param message
	 */
	public void message(String message, Object... args) {

		if (getPlayer() == null)
			return;

		getPlayer().sendMessage(new TextComponent(Config.prefix + " " + String.format(message, args)));

	}

	/**
	 * Permet d'envoyer un message au joueur
	 * 
	 * @param message
	 */
	public void action(String message, Object... args) {

		if (getPlayer() == null)
			return;

		getPlayer().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(String.format(message, args)));

	}

	/**
	 * Permet d'envoyer un message au joueur
	 * 
	 * @param message
	 */
	public void title(String message, String subMessage, int fadeIn, int stay, int fadeOut, Object... args) {

		if (getPlayer() == null)
			return;

		Title title = ProxyServer.getInstance().createTitle();
		title.title(new TextComponent(message));
		title.subTitle(new TextComponent(String.format(subMessage, args)));
		title.fadeIn(fadeIn);
		title.fadeOut(fadeOut);
		title.stay(stay);
		title.send(getPlayer());

	}

	public int getQueuePosition() {
		return queuePosition;
	}

	public void setQueuePosition(int queuePosition) {
		this.queuePosition = queuePosition;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void addOne() {
		if (isWaiting()) {
			queuePosition++;
			message("§eUn joueur prioritaire vient de vous passer devant.");
		}
	}

	public void removeOne() {
		if (isWaiting())
			queuePosition--;
	}

	/**
	 * 
	 * @param position
	 */
	public void updatePosition(int position, int size) {
		
		if (queuePosition > position){
			queuePosition--;
			action("§eUn joueur vient de déconnecter, vous êtes passez à la position §6%s §esur §6%s§e.", queuePosition, size);
		}
		
	}

}
