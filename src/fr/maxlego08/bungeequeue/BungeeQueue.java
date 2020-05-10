package fr.maxlego08.bungeequeue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import fr.maxlego08.bungeequeue.access.DefaultAccess;
import fr.maxlego08.bungeequeue.access.JPremiumAccess;
import fr.maxlego08.bungeequeue.command.CommandQueue;
import fr.maxlego08.bungeequeue.listener.ServerListener;
import fr.maxlego08.bungeequeue.utils.ZPlugin;
import fr.maxlego08.bungeequeue.utils.enums.EnumPlugin;
import fr.maxlego08.bungeequeue.utils.enums.LogType;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;

public class BungeeQueue extends ZPlugin {

	private QueueManager manager;
	private QueueAccess access = new DefaultAccess();
	private boolean isRunning = false;
	private boolean isMaintenance = false;

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

		if (Config.useMotd)
			this.motdTask();

	}

	@Override
	public void onDisable() {
		preDisable();

		this.manager.setRunning(false);
		this.isRunning = false;

		postDisable();
	}

	public QueueManager getManager() {
		return this.manager;
	}

	/***
	 * 
	 * @return
	 */
	public QueueAccess getAccess() {
		return this.access;
	}

	/**
	 * @return the isMaintenance
	 */
	public boolean isMaintenance() {
		return isMaintenance;
	}

	public void motdTask() {

		if (this.isRunning)
			return;

		this.isRunning = true;

		ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
		ses.scheduleAtFixedRate(() -> {

			if (!isRunning || !Config.useMotd) {
				ses.shutdown();
				isMaintenance = false;
				return;
			}

			ServerInfo info = getProxy().getServerInfo(Config.maintenanceServer);

			if (info == null)
				return;

			info.ping(new Callback<ServerPing>() {

				@Override
				public void done(ServerPing server, Throwable thor) {

					if (server == null)
						return;

					String motd = TextComponent.toLegacyText(server.getDescriptionComponent());
					isMaintenance = motd.contains("maintenance") || motd.contains(Config.defaultMotd);

				}
			});

		}, Config.motdSpeedMaintenanceTask, Config.motdSpeedMaintenanceTask, TimeUnit.MILLISECONDS);

	}

}
