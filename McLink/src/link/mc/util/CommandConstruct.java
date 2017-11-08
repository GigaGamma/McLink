package link.mc.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandConstruct {
	
	public static boolean match(String[] args, String[] pattern) {
		if (args.length == pattern.length) {
			for (int i = 0; i < args.length; i++) {
				if (pattern[i].equalsIgnoreCase("string")) {
					/*try {
						Float.valueOf(args[i]);
						return false;
					}
					catch (Exception e) {
						System.out.println(e);
					}*/
				} else if (pattern[i].equalsIgnoreCase("number")) {
					try {
						Float.valueOf(args[i]);
					}
					catch (Exception e) {
						return false;
					}
				}
				
				else if (pattern[i].equalsIgnoreCase("player") && PlayerUtil.getPlayer(args[i]) == null) {
					return false;
				}
				
				else if (pattern[i].equalsIgnoreCase("player") && PlayerUtil.getPlayer(args[i]) != null) {
					
				}
				
				else if (pattern[i].equalsIgnoreCase("offplayer") && PlayerUtil.getOfflinePlayer(args[i]) == null) {
					return false;
				}
				
				else if (pattern[i].equalsIgnoreCase("offplayer") && PlayerUtil.getOfflinePlayer(args[i]) != null) {
					
				}
				
				else if (args[i].equalsIgnoreCase(pattern[i])) {
					
				}
				
				else {
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public static String getNoArgsMessage() {
		return ChatColor.RED + "Please, get some /help";
	}
	
}
