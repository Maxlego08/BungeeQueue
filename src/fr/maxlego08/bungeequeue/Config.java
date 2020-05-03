package fr.maxlego08.bungeequeue;

import fr.maxlego08.bungeequeue.utils.storage.Persist;
import fr.maxlego08.bungeequeue.utils.storage.Saveable;

public class Config implements Saveable {

	public static String prefix = "§8(§ePrideNetwork§8)";
	public static String targetServer = "faction";
	public static String defaultServer = "lobby";
	
	@Override
	public void save(Persist persist) {
		persist.save(this);
	}

	@Override
	public void load(Persist persist) {
		persist.loadOrSaveDefault(this, Config.class);
	}

}
