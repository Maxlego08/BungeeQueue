package fr.maxlego08.bungeequeue;

import fr.maxlego08.bungeequeue.utils.ZPlugin;

public class BungeeQueue extends ZPlugin {

	@Override
	public void onEnable() {

		preEnable();

		postEnable();

	}

	@Override
	public void onDisable() {
		preDisable();

		postDisable();
	}

}
