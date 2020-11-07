package fr.maxlego08.bungeequeue.command;

import fr.maxlego08.bungeequeue.BungeeQueue;
import fr.maxlego08.bungeequeue.Config;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandQueue extends Command {

	private final BungeeQueue plugin;

	public CommandQueue(BungeeQueue plugin) {
		super("queue");
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if (!(sender instanceof ProxiedPlayer)) {

			if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
				plugin.load();
				sender.sendMessage(new TextComponent("§aReload !"));
				return;
			} else if (args.length == 2 && args[0].equalsIgnoreCase("speed")) {
				try {
					long integer = Long.valueOf(args[1]);
					Config.queueSpeed = integer;
					plugin.save();
					plugin.getManager().restart();
					sender.sendMessage(
							new TextComponent(Config.queueSpeedChange.replace("%speed%", String.valueOf(integer))));
				} catch (Exception e) {
					sender.sendMessage(new TextComponent("§f/queue speed <vitesse>"));
				}
				return;
			}

			sender.sendMessage(new TextComponent(Config.onliPlayerCanUse));
			return;
		}

		if (!Config.useQueueCommand) {
			sender.sendMessage(new TextComponent(Config.queueCommandDisable));
			return;
		}

		ProxiedPlayer player = (ProxiedPlayer) sender;

		if (!plugin.getAccess().canJoinQueue(player)) {
			sender.sendMessage(new TextComponent(Config.mustBeLogin));
			return;
		}

		boolean isServer = Config.defaultsSevers.stream()
				.filter(serv -> player.getServer().getInfo().getName().equals(serv)).findAny().isPresent();
		if (!isServer) {
			sender.sendMessage(new TextComponent(Config.wrongServer));
			return;
		}

		plugin.getManager().joinQueue(player);
	}

}
