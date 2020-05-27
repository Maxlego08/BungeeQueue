package fr.maxlego08.bungeequeue.spigot;

import org.bukkit.Bukkit;

import fr.maxlego08.bungeequeue.spigot.listener.ServerListener;
import fr.maxlego08.bungeequeue.spigot.utils.ZPlugin;

public class SpigotQueue extends ZPlugin {

	private ServerListener listener;

	@Override
	public void onEnable() {

		preEnable();

		addListener(listener = new ServerListener());
		addSave(new Config());

		// On attend 10 secondes pour être sur que le serveur soit activé
		getServer().getScheduler().runTaskLater(this, () -> {
			listener.setEnable(true);
		}, 20 * 10);

		getSaveables().forEach(s -> s.load(getPersist()));
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, Config.channelName);
		
		postEnable();
	}

	@Override
	public void onDisable() {
		preDisable();

		getSaveables().forEach(s -> s.save(getPersist()));

		postDisable();
	}

}
