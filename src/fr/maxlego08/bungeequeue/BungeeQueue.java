package fr.maxlego08.bungeequeue;

import fr.maxlego08.bungeequeue.access.DefaultAccess;
import fr.maxlego08.bungeequeue.access.JPremiumAccess;
import fr.maxlego08.bungeequeue.command.CommandQueue;
import fr.maxlego08.bungeequeue.listener.ServerListener;
import fr.maxlego08.bungeequeue.utils.EnumPlugin;
import fr.maxlego08.bungeequeue.utils.LogType;
import fr.maxlego08.bungeequeue.utils.ZPlugin;

public class BungeeQueue extends ZPlugin {

	private QueueManager manager;
	private QueueAccess access = new DefaultAccess();

	@Override
	public void onEnable() {

		preEnable();

		manager = new QueueManager(this);
		manager.run();

		addCommand(new CommandQueue(this));
		addSave(new Config());
		addListener(new ServerListener(this));

		if (isPlugin(EnumPlugin.JPREMIUM)) {
			access = new JPremiumAccess();
			log("JPremium detection, use of JPremium access to use the waiting list", LogType.SUCCESS);
		}

		addListener(access);
		postEnable();

	}

	@Override
	public void onDisable() {
		preDisable();

		manager.setRunning(false);

		postDisable();
	}

	public QueueManager getManager() {
		return manager;
	}

	public QueueAccess getAccess() {
		return access;
	}

}
