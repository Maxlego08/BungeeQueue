package fr.maxlego08.bungeequeue.spigot;

import fr.maxlego08.bungeequeue.spigot.listener.ServerListener;
import fr.maxlego08.bungeequeue.spigot.utils.ZPlugin;

public class SpigotQueue extends ZPlugin {

	private ServerListener listener;

	@Override
	public void onEnable() {

		preEnable();

		addListener(listener = new ServerListener());

		postEnable();

		//On attend 10 secondes pour être sur que le serveur soit activé
		getServer().getScheduler().runTaskLater(this, () -> {
			listener.setEnable(true);
		}, 20 * 10);

	}

	@Override
	public void onDisable() {
		preDisable();

		postDisable();
	}

}
