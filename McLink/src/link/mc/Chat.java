package link.mc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.TabCompleteEvent;

import link.mc.command.McLinkCommand;
import link.mc.event.ChatRunnable;
import link.mc.event.InteractRunnable;
import link.mc.util.ChatUtil;
import link.mc.util.LoopUtil;
import link.mc.util.MarkupUtil;
import link.mc.util.PlayerUtil;

public class Chat implements Listener {
	
	public static LoopUtil<ChatRunnable> run = new LoopUtil<ChatRunnable>();
	public static Map<Player, ChatMode> modes = new HashMap<Player, ChatMode>();
	
	public static enum ChatMode {
		
		ALL (true, true),
		MC (true, false),
		DISCORD (false, true);
		
		private final boolean toMc;
		private final boolean toDiscord;
		
		private ChatMode(boolean toMc, boolean toDiscord) {
			this.toMc = toMc;
			this.toDiscord = toDiscord;
		}
		
		public boolean toMc() {
			return this.toMc;
		}
		
		public boolean toDiscord() {
			return this.toDiscord;
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event) {
		event.setFormat("%s \u00bb %s");
		
		List<String> ms = ChatUtil.getMentions(event.getMessage());
		List<String> rm = new ArrayList<String>();
		
		for (String m : ms) {
			if (!rm.contains(m)) {
				rm.add(m);
				//McLink.instance.getLogger().info(m);
				//event.setMessage(event.getMessage().replace(m, MarkupUtil.markupToChat("**" + m + "**")));
			}
		}
		
		ListIterator<ChatRunnable> it = run.update();
		while (it.hasNext()) {
			ChatRunnable r = it.next();
			r.setEvent(event);
			r.run();
		}
		
		if (Chat.modes.containsKey(event.getPlayer()) && Chat.modes.get(event.getPlayer()).toDiscord() && McLink.instance.bot != null) {
			McLink.instance.bot.broadcast(event.getPlayer().getName() + " \u00bb " + event.getMessage());
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
						if (event.getPlayer() != null)
							p.sendMessage(MarkupUtil.markupToChat("&yellow [Mentioned]& [") + event.getPlayer().getDisplayName() + "] " + MarkupUtil.markupToChat(event.getMessage()));
						else
							p.sendMessage(MarkupUtil.markupToChat("&yellow [Mentioned]& ") + MarkupUtil.markupToChat(event.getMessage()));
						p.playSound(p.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 3.0F, 0.5F);
					}
				}
			}
		}
		
		event.setMessage(MarkupUtil.markupToChat(event.getMessage()));
		
		
		if (event.getPlayer() == null) {
			System.out.println("Formatting for null");
			for (Player p : event.getRecipients())
				p.sendMessage(event.getMessage());
		}
		
		//event.setFormat(MarkupUtil.markupToChat(""));
	}
	
	@EventHandler
	public void onTabList(PlayerChatTabCompleteEvent event) {
		/*for (String s : event.getTabCompletions()) {
			Player ap = PlayerUtil.getPlayer(s);
			if (ap == null)
				continue;
			if (!event.getPlayer().canSee(ap))
				event.getTabCompletions().remove(s);
		}*/
		event.getTabCompletions().add("@everyone");
	}
	
	@EventHandler
	public void onTabList(TabCompleteEvent event) {
		if (!(event.getSender() instanceof Player))
			return;
		for (String s : event.getCompletions()) {
			Player ap = PlayerUtil.getPlayer(s);
			if (ap == null)
				continue;
			if (!((Player) event.getSender()).canSee(ap))
				event.getCompletions().remove(s);
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (!event.getPlayer().canSee(p) && event.getMessage().toLowerCase().contains(p.getName().toLowerCase()))
				//event.setMessage(event.getMessage().replace(p.getName(), event.getPlayer().getName()));
				event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (McLinkCommand.CLOAKED.contains(p)) {
				event.getPlayer().hidePlayer(p);
			}
		}
	}
	
}
