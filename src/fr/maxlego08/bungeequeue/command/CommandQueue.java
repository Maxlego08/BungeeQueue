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
			}

			sender.sendMessage(new TextComponent("§cVous ne pouvez pas faire cette commande depuis la console."));
			return;
		}

		ProxiedPlayer player = (ProxiedPlayer) sender;
		
		if (!plugin.getAccess().canJoinQueue(player)){
			sender.sendMessage(new TextComponent("§cVous ne pouvez pas rejoindre la liste d'attente pour le moment."));
			return;
		}
		
		
		if (!player.getServer().getInfo().getName().equalsIgnoreCase(Config.defaultServer)) {
			sender.sendMessage(new TextComponent("§cVous ne pouvez pas faire cette commande ici."));
			return;
		}

		plugin.getManager().joinQueue(player);
	}

}
