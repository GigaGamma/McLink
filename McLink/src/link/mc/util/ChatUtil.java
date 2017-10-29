package link.mc.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;

public class ChatUtil {
	
	public static List<String> getMentions(String msg) {
		List<String> mentions = new ArrayList<String>();
		String pattern = "(?<=^|(?<=[^a-zA-Z0-9-_\\\\.]))@([A-Za-z][A-Za-z0-9_]+)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(msg);
		if (m.find()){
			mentions.add(m.group()); 
		}
		
		return mentions;
	}
	
}
