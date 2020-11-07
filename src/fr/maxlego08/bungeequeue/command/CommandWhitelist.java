package fr.maxlego08.bungeequeue.command;

import fr.maxlego08.bungeequeue.BungeeQueue;
import fr.maxlego08.bungeequeue.Config;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandWhitelist extends Command {

	private final BungeeQueue plugin;

	/**
	 * @param name
	 * @param plugin
	 */
	public CommandWhitelist(BungeeQueue plugin) {
		super("gwhitelist");
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if (sender instanceof ProxiedPlayer) {
			sender.sendMessage(new TextComponent("§cVous n'avez pas la permission."));
			return;
		}

		if (args.length != 2) {
			sender.sendMessage(new TextComponent("§b/gwhitelist add <player> §7Ajouter un joueur à la whitelist"));
			sender.sendMessage(new TextComponent("§b/gwhitelist remove <player> §7Retirer un joueur à la whitelist"));
		} else if (args.length == 2 && args[0].equalsIgnoreCase("add")) {

			String name = args[1];
			if (Config.whitelistUsers.contains(name)) {
				sender.sendMessage(new TextComponent("§eVous avez déjà ajouter §6" + name + " §edans la whitelist."));
				return;
			}
			Config.whitelistUsers.add(name);
			plugin.save();
			sender.sendMessage(new TextComponent("§eVous venez d'ajouter §6" + name + " §edans la whitelist."));

		} else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {

			String name = args[1];
			Config.whitelistUsers.remove(name);
			plugin.save();
			sender.sendMessage(new TextComponent("§eVous venez de retirer §6" + name + " §edans la whitelist."));

		} else {
			sender.sendMessage(new TextComponent("§b/gwhitelist add <player> §7Ajouter un joueur à la whitelist"));
			sender.sendMessage(new TextComponent("§b/gwhitelist remove <player> §7Retirer un joueur à la whitelist"));
		}

	}

}
