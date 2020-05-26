package fr.maxlego08.bungeequeue.spigot.api;

import java.io.ByteArrayOutputStream;

import org.bukkit.entity.Player;

import fr.maxlego08.bungeequeue.spigot.Config;
import fr.maxlego08.bungeequeue.spigot.utils.ZPlugin;

public class QueueApi {

	public static void join(Player player){
		
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		player.getPlayer().sendPluginMessage(ZPlugin.getPlugin(), Config.channelName, b.toByteArray());
		player.getPlayer().closeInventory();
		
	}

}
