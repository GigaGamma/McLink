package link.mc.external.discord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import link.mc.Chat;
import link.mc.McLink;
import link.mc.command.Commands;
import link.mc.permission.Rank;
import link.mc.permission.Ranks;
import link.mc.util.LoopUtil;
import link.mc.util.PlayerUtil;
import link.mc.util.ServerUtil;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {
	
	public Bot bot;
	private LoopUtil<ResponseListener> rls = new LoopUtil<ResponseListener>();
	
	//private List<ResponseListener> res = new ArrayList<ResponseListener>();
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (bot.shutdown) {bot.bot.removeEventListener(this); bot.bot.shutdownNow(); System.out.println("Shutdown"); return;}
		//event.getChannel().sendMessage(new MessageBuilder().append("Hi").build()).queue();
		if (event.getAuthor() == McLink.instance.bot.bot.getSelfUser()) return;
		
		ListIterator<ResponseListener> l = rls.update();
		
		while (l.hasNext()) {
			ResponseListener r = l.next();
			
			if (!r.used) {
				r.event = event;
				r.run();
			} else {
				rls.remove(r);
			}
		}
		
		Set<Player> ps = new HashSet<Player>();
		if (!event.getAuthor().isBot() && !event.getAuthor().isFake()) {
			for (Player p : Chat.modes.keySet()) {
				if (Chat.modes.get(p).toDiscord()) {
					ps.add(p);
				}
			}
		}
		String s = event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator();
		Player ds = PlayerUtil.getPlayerFromDiscord(s);
		if (ds != null) {
			String ra = "";
			for (Player p : ps) {
				if (ds != null && PlayerUtil.getRankId(ds) != null) {
					Rank ras = Ranks.getRankById(PlayerUtil.getRankId(ds));
					ra = ras.getColor() + "[" + ras.getName() + "]";
				}
				p.sendMessage(ra + " " + ds.getName() + " " + ChatColor.RESET + "\u00bb " + event.getMessage().getContent());
			}
		} else {
			for (Player p : ps) {
				p.sendMessage(s + " " + ChatColor.RESET + "\u00bb " + event.getMessage().getContent());
			}
		}
		
		for (BotCommand command : Commands.getBotCommands()) {
			if (event.getMessage().getContent().split(" ")[0].equalsIgnoreCase("!" + command.getName())) {
				String[] a = event.getMessage().getContent().substring(1).split(" ");
				command.onCall(event.getAuthor(), event.getChannel(), Arrays.copyOfRange(a, 1, a.length));
			}
		}
	}
	
}
