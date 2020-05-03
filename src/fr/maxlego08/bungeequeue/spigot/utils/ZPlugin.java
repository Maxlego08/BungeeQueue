package fr.maxlego08.bungeequeue.spigot.utils;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.maxlego08.bungeequeue.utils.LogType;
import fr.maxlego08.bungeequeue.utils.Plugin;
import fr.maxlego08.bungeequeue.utils.storage.Persist;
import fr.maxlego08.bungeequeue.utils.storage.Saveable;

public class ZPlugin extends JavaPlugin implements Plugin {

	private Gson gson;
	private Persist persist;
	private Logger logger = new Logger(getDescription().getFullName());
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

		logger.log("=== ENABLE DONE <&>7(<&>6" + Math.abs(enableTime - System.currentTimeMillis()) + "ms<&>7) <&>e===");

	}

	protected void preDisable() {

		enableTime = System.currentTimeMillis();
		logger.log("=== DISABLE START ===");

	}

	protected void postDisable() {

		logger.log(
				"=== DISABLE DONE <&>7(<&>6" + Math.abs(enableTime - System.currentTimeMillis()) + "ms<&>7) <&>e===");

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
	 * @param listener
	 */
	protected void addListener(Listener listener) {
		getServer().getPluginManager().registerEvents(listener, this);
	}

	public GsonBuilder getGsonBuilder() {
		return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().serializeNulls()
				.excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.VOLATILE);
	}

	public Persist getPersist() {
		return persist;
	}

}
