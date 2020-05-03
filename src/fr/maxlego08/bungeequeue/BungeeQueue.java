package fr.maxlego08.bungeequeue;

import fr.maxlego08.bungeequeue.command.CommandQueue;
import fr.maxlego08.bungeequeue.utils.ZPlugin;

public class BungeeQueue extends ZPlugin {

	private QueueManager manager;
	
	@Override
	public void onEnable() {

		preEnable();

		manager = new QueueManager(this);
		manager.run();
		
		addCommand(new CommandQueue(this));
		addSave(new Config());
		
		postEnable();

	}

	@Override
	public void onDisable() {
		preDisable();

		manager.setRunning(false);
		
		postDisable();
	}

}
