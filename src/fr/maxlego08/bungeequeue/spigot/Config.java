package fr.maxlego08.bungeequeue.spigot;

import fr.maxlego08.bungeequeue.utils.storage.Persist;
import fr.maxlego08.bungeequeue.utils.storage.Saveable;

public class Config implements Saveable {

	public static String channelName = "zQueue";
	
	@Override
	public void save(Persist persist) {
		persist.save(this);
	}

	@Override
	public void load(Persist persist) {
		persist.loadOrSaveDefault(this, Config.class);
	}

}
