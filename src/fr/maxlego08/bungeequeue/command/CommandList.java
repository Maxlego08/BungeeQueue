package fr.maxlego08.bungeequeue.command;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import fr.maxlego08.bungeequeue.Config;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Command;

public class CommandList extends Command {

	private Map<CommandSender, Long> cooldown = new HashMap<CommandSender, Long>();

	public CommandList() {
		super("list", null, "elist", "glist", "bungeecordlist", "essentials:elist", "lists");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {

		if (cooldown.getOrDefault(sender, 0l) > System.currentTimeMillis()) {
			double time = (double) (cooldown.getOrDefault(sender, 0l) - System.currentTimeMillis()) / 1000;
			String timer = new DecimalFormat("#.##").format(time);
			sender.sendMessage(
					new TextComponent(Config.prefix + " " + String.format(Config.listCommandCooldown, timer)));
			return;
		}

		cooldown.put(sender, System.currentTimeMillis() + 1000 * 5);

		Collection<ServerInfo> servers = ProxyServer.getInstance().getServers().values();

		String s = "§7§m-";
		for (int a = 0; a != 25; a++)
			s += "§7§m-";

		sender.sendMessage(new TextComponent(s));
		sender.sendMessage(new TextComponent(""));

		String c = String.format(Config.networkListMessage, ProxyServer.getInstance().getOnlineCount());
		sender.sendMessage(new TextComponent(c));
		sender.sendMessage(new TextComponent(" "));

		final String s2 = s;
		AtomicInteger a = new AtomicInteger(1);
		servers.forEach(serv -> {
			serv.ping(new Callback<ServerPing>() {
				@Override
				public void done(ServerPing ping, Throwable var2) {
					int max = ping.getPlayers().getMax();
					int online = ping.getPlayers().getOnline();
					String o = (online >= max ? "§c" : "§a") + online;
					String c = Config.serverListMessage.replace("%name%", name(serv.getName())).replace("%online%", o);
					sender.sendMessage(new TextComponent(c));
					sender.sendMessage(new TextComponent(" "));
					int newA = a.getAndIncrement();
					if (newA == servers.size()) {
						sender.sendMessage(new TextComponent(s2));
					}
				}
			});
		});
	}

	public String name(String m) {
		String name = m.replace("_", " ").toLowerCase();
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

}
