package link.mc.util;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerUtil {
	
	public static void broadcast(String message) {
		ServerUtil.send(message, Bukkit.getOnlinePlayers());
	}
	
	public static void send(String message, Collection<? extends Player> people) {
		for (Player player : people) {
			player.sendMessage(message);
		}
	}
	
}
