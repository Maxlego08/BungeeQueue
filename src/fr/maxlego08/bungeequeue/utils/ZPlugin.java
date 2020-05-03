package fr.maxlego08.bungeequeue.utils;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.maxlego08.bungeequeue.utils.storage.Persist;
import fr.maxlego08.bungeequeue.utils.storage.Saveable;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class ZPlugin extends Plugin implements fr.maxlego08.bungeequeue.utils.Plugin {

	private Gson gson;
	private Persist persist;
	private Logger logger = new Logger(this.getDescription().getName() + "-V" + this.getDescription().getVersion());
	private List<Saveable> saveables = new ArrayList<Saveable>();
	private long enableTime;

	protected void preEnable() {
		enableTime = System.currentTimeMillis();

		gson = getGsonBuilder().create();

		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}

		persist = new Persist(this);

		logger.log("=== ENABLE START ===");
		logger.log("Plugin Version V<&>c" + getDescription().getVersion(), LogType.INFO);
	}

	protected void postEnable() {

		saveables.forEach(save -> save.load(persist));

		logger.log("=== ENABLE DONE <&>7(<&>6" + Math.abs(enableTime - System.currentTimeMillis()) + "ms<&>7) <&>e===");

	}

	protected void preDisable() {

		enableTime = System.currentTimeMillis();
		logger.log("=== DISABLE START ===");

	}

	protected void postDisable() {

		saveables.forEach(save -> save.save(persist));

		logger.log(
				"=== DISABLE DONE <&>7(<&>6" + Math.abs(enableTime - System.currentTimeMillis()) + "ms<&>7) <&>e===");

	}
	
	public void load(){
		saveables.forEach(save -> save.load(persist));
	}

	@Override
	public Gson getGson() {
		return gson;
	}

	@Override
	public File getFolder() {
		return super.getDataFolder();
	}

	@Override
	public void log(String message, LogType type) {
		logger.log(message, type);
	}

	@Override
	public void addSave(Saveable saveable) {
		saveables.add(saveable);
	}

	/**
	 * 
	 * @param command
	 */
	public void addCommand(Command command) {
		getProxy().getPluginManager().registerCommand(this, command);
	}
	
	/**
	 * 
	 * @param listener
	 */
	public void addListener(Listener listener) {
		this.getProxy().getPluginManager().registerListener(this, listener);
	}
	
	/**
	 * 
	 * @return
	 */
	public GsonBuilder getGsonBuilder() {
		return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().serializeNulls()
				.excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.VOLATILE);
	}

	/*
	 * 
	 */
	public Persist getPersist() {
		return persist;
	}

}
