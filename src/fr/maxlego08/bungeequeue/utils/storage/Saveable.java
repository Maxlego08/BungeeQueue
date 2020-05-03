package fr.maxlego08.bungeequeue.utils.storage;

public interface Saveable {

	/**
	 * 
	 * @param persist
	 */
	void save(Persist persist);

	/**
	 * 
	 * @param persist
	 */
	void load(Persist persist);
}
