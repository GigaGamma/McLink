package link.mc.command;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import link.mc.McLink;
import link.mc.gui.Page;
import link.mc.secure.ActionFactory;
import link.mc.secure.AdminLayout;
import link.mc.secure.Cheats;
import link.mc.util.CommandConstruct;
import link.mc.util.MarkupUtil;
import link.mc.util.PlayerUtil;

public class McLinkCommand extends Command {
	
	private static final String[] ADMIN = new String[] {"admin"};
	private static final String[] KICK = new String[] {"admin", "kick", "player", "string"};
	private static final String[] BAN = new String[] {"admin", "ban", "player", "string"};
	private static final String[] UNBAN = new String[] {"admin", "unban", "offplayer"};
	
	/*
	 * String name, String description, String usageMessage, List<String> aliases
	 */
	public McLinkCommand() {
		super("mclink", "The McLink Command", "/mclink ...", new ArrayList<String>());
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		/*if (args.length == 3) {
			if (args[0].equalsIgnoreCase("ac") && sender.hasPermission("mclink.testing")) {
				if (args[1].equalsIgnoreCase("speed")) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.getName().equalsIgnoreCase(args[2])) {
							sender.sendMessage("Sent");
							ActionFactory.log(Cheats.SPEED_HACKING, p);
						}
					}
				}
			}
		}*/
		if (args.length == 0) {
			Page p = new Page("McLink Administration", new AdminLayout());
			p.registerEvents();
			((Player) sender).openInventory(p.compileItems());
		}
		else if (CommandConstruct.match(args, KICK) && (sender.hasPermission("mclink.kick") || sender instanceof ConsoleCommandSender)) {
			Player p = PlayerUtil.getPlayer(args[2]);
			p.kickPlayer(MarkupUtil.markupToChat("**McLink**\nYou were kicked by " + sender.getName() + " for " + MarkupUtil.markupToChat(args[3].replace("|", " "))));
			sender.sendMessage(p.getName() + " kicked for " + MarkupUtil.markupToChat(args[3].replace("|", " ")));
			ActionFactory.log("**" + p.getName() + " kicked** by " + sender.getName() +  " for `" + args[3].replace("|", " ") + "`", p, "Kick");
		} else if (CommandConstruct.match(args, BAN) && (sender.hasPermission("mclink.ban") || sender instanceof ConsoleCommandSender)) {
			Player p = PlayerUtil.getPlayer(args[2]);
			
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			
			Date inputDate = null;
			
			try {
				inputDate = dateFormat.parse("1-1-2020");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Bukkit.getBanList(Type.NAME).addBan(p.getName(),  MarkupUtil.markupToChat(args[3].replace("|", " ")), inputDate, "");
			p.kickPlayer(MarkupUtil.markupToChat("**McLink**\nYou have been **BANNED** by " + sender.getName() + " for " + MarkupUtil.markupToChat(args[3].replace("|", " "))));
			sender.sendMessage(p.getName() + " banned for " + MarkupUtil.markupToChat(args[3].replace("|", " ")));
			ActionFactory.log("**" + p.getName() + " banned** by " + sender.getName() + " for `" + args[3].replace("|", " ") + "`", p, "Ban");
		} else if (CommandConstruct.match(args, UNBAN) && (sender.hasPermission("mclink.unban") || sender instanceof ConsoleCommandSender)) {
			OfflinePlayer p = PlayerUtil.getOfflinePlayer(args[2]);
			Bukkit.getBanList(Type.NAME).pardon(p.getName());
			sender.sendMessage(p.getName() + " unbanned");
			ActionFactory.log("**" + p.getName() + " unbanned** by " + sender.getName(), p, "Ban");
		} else {
			sender.sendMessage(CommandConstruct.getNoArgsMessage());
		}
		
		return true;
	}

}
