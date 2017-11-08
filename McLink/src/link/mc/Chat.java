package link.mc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import link.mc.util.ChatUtil;
import link.mc.util.MarkupUtil;

public class Chat implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event) {
		event.setFormat("[%s] %s");
		
		List<String> ms = ChatUtil.getMentions(event.getMessage());
		List<String> rm = new ArrayList<String>();
		
		for (String m : ms) {
			if (!rm.contains(m)) {
				rm.add(m);
				//McLink.instance.getLogger().info(m);
				//event.setMessage(event.getMessage().replace(m, MarkupUtil.markupToChat("**" + m + "**")));
			}
		}
		
		for (String m : rm) {
			event.setMessage(event.getMessage().replace(m, MarkupUtil.markupToChat("&aqua **" + m + "**&")));
			if (event.getRecipients() != null) {
				Iterator<Player> i = event.getRecipients().iterator();
				/*for (Player p : event.getRecipients()) {
					if (m.replace("@", "").equalsIgnoreCase(p.getName()) || (m.replace("@", "").equalsIgnoreCase("everyone") && event.getPlayer().hasPermission("mclink.mention.everyone"))) {
						event.getRecipients().remove(p);
						p.sendMessage(MarkupUtil.markupToChat("&yellow [Mentioned]& [") + event.getPlayer().getDisplayName() + "] " + event.getMessage());
						p.playSound(p.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 3.0F, 0.5F);
					}
				}*/
				while (i.hasNext()) {
					Player p = i.next();
					
					if (m.replace("@", "").equalsIgnoreCase(p.getName()) || (m.replace("@", "").equalsIgnoreCase("everyone") && event.getPlayer().hasPermission("mclink.mention.everyone"))) {
						i.remove();
						p.sendMessage(MarkupUtil.markupToChat("&yellow [Mentioned]& [") + event.getPlayer().getDisplayName() + "] " + event.getMessage());
						p.playSound(p.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 3.0F, 0.5F);
					}
				}
			}
		}
		
		event.setMessage(MarkupUtil.markupToChat(event.getMessage()));
		
		//event.setFormat(MarkupUtil.markupToChat(""));
	}
	
}
