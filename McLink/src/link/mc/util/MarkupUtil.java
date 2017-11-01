package link.mc.util;

import org.bukkit.ChatColor;

public class MarkupUtil {
	
	public static String markupToChat(String markdown) {
		//markdown = markdown.replaceAll("(\\*\\*|__)(?=\\S)(.*?\\S[*_]*)\\1", "<b>$2</b>");
		
		markdown = markdown.replaceAll("(" + Surrounders.BOLD_1.getChars() + ")(?=\\S)(.*?\\S[*_]*)\\1", Surrounders.BOLD_1.getReplacements());
		markdown = markdown.replaceAll("(" + Surrounders.BOLD_2.getChars() + ")(?=\\S)(.*?\\S[*_]*)\\1", Surrounders.BOLD_2.getReplacements());
		markdown = markdown.replaceAll("(" + Surrounders.ITALIC_1.getChars() + ")(?=\\S)(.*?\\S[*_]*)\\1", Surrounders.ITALIC_1.getReplacements());
		markdown = markdown.replaceAll("(" + Surrounders.ITALIC_2.getChars() + ")(?=\\S)(.*?\\S[*_]*)\\1", Surrounders.ITALIC_2.getReplacements());
		markdown = markdown.replaceAll("(" + Surrounders.STRIKETHROUGH.getChars() + ")(?=\\S)(.*?\\S[*_]*)\\1", Surrounders.STRIKETHROUGH.getReplacements());
		markdown = markdown.replaceAll("(" + Surrounders.OBFUSCATED.getChars() + ")(?=\\S)(.*?\\S[*_]*)\\1", Surrounders.OBFUSCATED.getReplacements());
		
		for (ChatColor color : ChatColor.values()) {
			//markdown = markdown.replace("&" + color.name().toLowerCase() + " ", ChatColor.translateAlternateColorCodes('&', "&" + color.getChar()));
			//markdown = markdown.replace("&" + color.name().toLowerCase(), "" + ChatColor.translateAlternateColorCodes('&', "&" + color.getChar()));
			markdown = markdown.replaceAll("(" + "&" + color.name().toLowerCase() + ")(?=\\S)(.*?\\S[*_]*)&", ChatColor.translateAlternateColorCodes('&', "&" + color.getChar()) + "$2" + ChatColor.RESET);
			markdown = markdown.replaceAll("(" + "&" + color.name().toLowerCase() + " )(?=\\S)(.*?\\S[*_]*)&", ChatColor.translateAlternateColorCodes('&', "&" + color.getChar()) + "$2" + ChatColor.RESET);
		}
		
		//System.out.println(markdown);
		
		return markdown;
	}
	
	public enum Surrounders {
			
		BOLD_1 ("\\*\\*", ChatColor.translateAlternateColorCodes('&', "&l$2&r")),
		BOLD_2 ("__", ChatColor.translateAlternateColorCodes('&', "&n$2&r")),
		ITALIC_1 ("\\*", ChatColor.translateAlternateColorCodes('&', "&o$2&r")),
		ITALIC_2 ("_", ChatColor.translateAlternateColorCodes('&', "&o$2&r")),
		STRIKETHROUGH ("~~", ChatColor.translateAlternateColorCodes('&', "&m$2&r")),
		OBFUSCATED ("_u_", ChatColor.translateAlternateColorCodes('&', "&k$2&r"));
		
		private String chars;
		private String replacements;
		
		Surrounders(String chars, String replacements) {
			this.chars = chars;
			this.replacements = replacements;
		}
		
		public String getChars() {
			return this.chars;
		}
		
		public String getReplacements() {
			return this.replacements;
		}
		
	}
	
}
