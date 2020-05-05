package fr.maxlego08.bungeequeue.utils;

import java.io.File;

import com.google.gson.Gson;

import fr.maxlego08.bungeequeue.utils.enums.LogType;
import fr.maxlego08.bungeequeue.utils.storage.Saveable;

public interface Plugin {

	/**
	 * 
	 * @return gson
	 */
	Gson getGson();
	
	/**
	 * 
	 * @return plugin folder
	 */
	File getFolder();
	
	/**
	 * 
	 * @param message
	 * @param tyoe
	 */
	void log(String message, LogType tyoe);
	
	/**
	 * 
	 * @param saveable
	 */
	void addSave(Saveable saveable);
	
}
