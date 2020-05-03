package fr.maxlego08.bungeequeue.spigot;

import fr.maxlego08.bungeequeue.spigot.listener.ServerListener;
import fr.maxlego08.bungeequeue.spigot.utils.ZPlugin;

public class SpigotQueue extends ZPlugin{

	@Override
	public void onEnable() {

		preEnable();

		addListener(new ServerListener());
		
		postEnable();

	}

	@Override
	public void onDisable() {
		preDisable();

		postDisable();
	}
	
}
