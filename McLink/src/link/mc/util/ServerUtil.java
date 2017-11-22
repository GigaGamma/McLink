package link.mc.util;

import java.util.Collection;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import link.mc.McLink;

public class ServerUtil {
	
	public static void broadcast(String message) {
		ServerUtil.send(message, Bukkit.getOnlinePlayers());
	}
	
	public static void send(String message, Collection<? extends Player> people) {
		Bukkit.getScheduler().runTaskAsynchronously(McLink.instance, new Runnable() {
			
			@Override
			public void run() {
				System.out.println("A");
				Bukkit.getPluginManager().callEvent(new AsyncPlayerChatEvent(true, null, message, new HashSet<Player>(people)));
			}
			
		});
	}
	
	public static String getLanguage() {
		return McLink.config.lang;
	}
	
}
