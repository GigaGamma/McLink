package link.mc.util;

import org.bukkit.Bukkit;

public class Placeholder {
	
	public static String _replace(String pn, String pr, String t) {
		return t.replace("%" + pn + "%", pr);
	}
	
	public static String replace(String t) {
		return _replace("server", Bukkit.getServerName(), t);
	}
	
}
