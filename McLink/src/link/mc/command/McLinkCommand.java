package link.mc.command;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vex;
import org.bukkit.entity.Wither;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import link.mc.event.ActionBind;
import link.mc.event.InteractRunnable;
import link.mc.kryan.Command;
import link.mc.math.ItemId;
import link.mc.secure.ActionFactory;
import link.mc.util.MarkupUtil;
import link.mc.util.PlayerUtil;
import link.mc.util.ServerUtil;
import link.mc.util.item.ItemConstruct;

@Command(name = "mclink", description = "McLink's command")
public class McLinkCommand {
	
	private static final String ADMIN = "admin";
	private static final String PUNISH = "punish";
	private static final String CHAT = "chat";
	
	public static final String[] KICK = new String[] {ADMIN, "kick", "player", "string"};
	public static final String[] BAN = new String[] {ADMIN, "ban", "player", "string"};
	public static final String[] UNBAN = new String[] {ADMIN, "unban", "offplayer"};
	public static final String[] CLOAK = new String[] {ADMIN, "cloak"};
	public static final String[] UNCLOAK = new String[] {ADMIN, "uncloak"};
	public static final String[] INVSEE = new String[] {ADMIN, "invsee", "player"};
	public static final String[] STICK = new String[] {ADMIN, "stick"};
	public static final String[] SWAT_COMMAND = new String[] {ADMIN, "swat"};
	public static final String[] CHATMODE = new String[] {CHAT, "mode", "all|mc|discord"};
	public static final String[] CLEAR = new String[] {CHAT, "clear", "wnumber"};
	public static final String[] PUNISH_FREEZE = new String[] {ADMIN, PUNISH, "freeze", "player"};
	public static final String[] FRIDGE_COMMAND = new String[] {ADMIN, "fridge"};
	public static final String[] WITHER_COMMAND = new String[] {ADMIN, "wither"};
	public static final String[] SKIN = new String[] {ADMIN, "skin", "string"};
	public static final String[] TRANSLATE = new String[] {ADMIN, "translate", "string"};
	
	public static final List<Player> CLOAKED = new ArrayList<Player>();
	public static final List<Player> FREEZED = new ArrayList<Player>();
	public static final ItemStack STICKODOOM = ItemId.attach(new ItemConstruct(Material.BLAZE_ROD).getMeta().setName("**Stick o' Doom**").getItem(), "mclink:stickofdoom");
	public static final ItemStack SWAT = ItemId.attach(new ItemConstruct(Material.BLAZE_ROD).getMeta().setName("**The Swatter**").getItem(), "mclink:swat");
	public static final ItemStack WITHER = ItemId.attach(new ItemConstruct(Material.SKULL_ITEM).getMeta().setName("**The Wither**").getItem(), "mclink:wither");
	public static final ItemStack FRIDGE = ItemId.attach(new ItemConstruct(Material.ICE).getMeta().setName("**The Refrigerator**").getItem(), "mclink:fridge");
	
	/*
	 * String name, String description, String usageMessage, List<String> aliases
	 */
	public McLinkCommand() {
		ActionBind.run.add(new InteractRunnable() {
			
			@Override
			public void run() {
				if (ItemId.same(getEvent().getItem(), McLinkCommand.STICKODOOM)) {
					if (getEvent().getAction().equals(Action.RIGHT_CLICK_AIR) || getEvent().getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
						getEvent().getPlayer().getWorld().strikeLightning(getEvent().getPlayer().getTargetBlock(null, 100).getLocation());
					} else {
						for (Entity e : getEvent().getPlayer().getWorld().getEntities()) {
							Block targetBlock = getEvent().getPlayer().getTargetBlock(null, 100);
							if (targetBlock.getLocation().distance(e.getLocation()) < 10 && e != getEvent().getPlayer()) {
								getEvent().getPlayer().getWorld().strikeLightning(e.getLocation());
							}
						}
					}
					getEvent().setCancelled(true);
				} else if (ItemId.same(getEvent().getItem(), McLinkCommand.SWAT)) {
					for (Entity e : getEvent().getPlayer().getWorld().getEntities()) {
						if (getEvent().getAction().equals(Action.RIGHT_CLICK_AIR) || getEvent().getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
						Block targetBlock = getEvent().getPlayer().getTargetBlock(null, 100);
							if (targetBlock.getLocation().distance(e.getLocation()) < 10) {
								if (e instanceof LivingEntity && e != getEvent().getPlayer()) {
									((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 10000, 255, false, false));
									Vex p = (Vex) getEvent().getPlayer().getWorld().spawn(getEvent().getPlayer().getLocation(), Vex.class);
									p.setCustomName("Swat");
									p.setCustomNameVisible(false);
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, 10, false, false));
									p.setTarget(((LivingEntity) e));
									
									for (Vex e2 : getEvent().getPlayer().getWorld().getEntitiesByClass(Vex.class)) {
										if (e2.getCustomName().equals("Swat")) {
											e2.setTarget(((LivingEntity) e));
										}
									}
									return;
								}
							}
						} else {
							if (e.getCustomName() != null && e.getCustomName().equals("Swat")) {
								e.remove();
							}
						}
					}
					getEvent().setCancelled(true);
				} else if (ItemId.same(getEvent().getItem(), McLinkCommand.WITHER)) {
					for (Entity e : getEvent().getPlayer().getWorld().getEntities()) {
						if (getEvent().getAction().equals(Action.RIGHT_CLICK_AIR) || getEvent().getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
						Block targetBlock = getEvent().getPlayer().getTargetBlock(null, 100);
							if (targetBlock.getLocation().distance(e.getLocation()) < 10) {
								if (e instanceof LivingEntity && e != getEvent().getPlayer()) {
									//((LivingEntity) e).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 10000, 255, false, false));
									Wither p = (Wither) getEvent().getPlayer().getWorld().spawn(getEvent().getPlayer().getLocation(), Wither.class);
									p.setCustomName("Wither Of Death");
									p.setCustomNameVisible(false);
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000, 10, false, false));
									p.setTarget(((LivingEntity) e));
									
									for (Wither e2 : getEvent().getPlayer().getWorld().getEntitiesByClass(Wither.class)) {
										if (e2.getCustomName().equals("Wither Of Death")) {
											e2.setTarget(((LivingEntity) e));
										}
									}
									return;
								}
							}
						} else {
							if (e.getCustomName() != null && e.getCustomName().equals("Wither Of Death")) {
								e.remove();
							}
						}
					}
					getEvent().setCancelled(true);
				} else if (ItemId.same(getEvent().getItem(), McLinkCommand.FRIDGE)) {
					for (Player e : Bukkit.getOnlinePlayers()) {
						Block targetBlock = getEvent().getPlayer().getTargetBlock(null, 100);
						if (targetBlock.getLocation().distance(e.getLocation()) < 10 && e != getEvent().getPlayer()) {
							if (FREEZED.contains(e)) {
								FREEZED.remove(e);
							} else {
								FREEZED.add(e);
							}
						}
					}
					getEvent().setCancelled(true);
				}
			}
			
		});
		
		
	}
	
	@Command.Subcommand(args = {ADMIN, "kick", "player", "string"}, permission = "mclink.kick")
	public void kick(CommandSender sender, String label, String[] args) {
		Player p = PlayerUtil.getPlayer(args[2]);
		p.kickPlayer(MarkupUtil.markupToChat("**McLink**\nYou were kicked by " + sender.getName() + " for " + MarkupUtil.markupToChat(args[3].replace("|", " "))));
		sender.sendMessage(p.getName() + " kicked for " + MarkupUtil.markupToChat(args[3].replace("|", " ")));
		ActionFactory.log("**" + p.getName() + " kicked** by " + sender.getName() +  " for `" + args[3].replace("|", " ") + "`", p, "Kick");
	}
	
	@Command.Subcommand(args = {ADMIN, "cloak"}, permission = "mclink.cloak")
	public void cloak(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			for (Player pl : Bukkit.getOnlinePlayers()) {
				pl.hidePlayer(p);
			}
			if (!CLOAKED.contains(p))
				CLOAKED.add(p);
			ServerUtil.broadcast("&yellow " + p.getName() + " left the game&");
		}
	}
	
	@Command.Subcommand(args = {ADMIN, "uncloak"}, permission = "mclink.cloak")
	public void uncloak(CommandSender sender, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			for (Player pl : Bukkit.getOnlinePlayers()) {
				pl.showPlayer(p);
			}
			if (CLOAKED.contains(p))
				CLOAKED.remove(p);
			ServerUtil.broadcast("&yellow " + p.getName() + " joined the game&");
		}
	}
	
	@Command.Subcommand(args = {ADMIN, "rank", "player", "string"}, permission = "mclink.rank")
	public void rank(CommandSender sender, String label, String[] args) {
		Player p = PlayerUtil.getPlayer(args[2]);
		
		PlayerUtil.setRankId(p, args[3]);
	}
	
	@Command.Subcommand(args = {"fly"}, permission = "mclink.fly")
	public void fly(CommandSender sender, String label, String[] args) {
		((Player) sender).setAllowFlight(!((Player) sender).getAllowFlight());
	}

	/*@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (args.length == 3) {
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
		}
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
		} else if (CommandConstruct.match(args, CLOAK) && sender.hasPermission("mclink.cloak")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.hidePlayer(p);
				}
				if (!CLOAKED.contains(p))
					CLOAKED.add(p);
				ServerUtil.broadcast("&yellow " + p.getName() + " left the game&");
			}
		} else if (CommandConstruct.match(args, UNCLOAK) && sender.hasPermission("mclink.cloak")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.showPlayer(p);
				}
				if (CLOAKED.contains(p))
					CLOAKED.remove(p);
				ServerUtil.broadcast("&yellow " + p.getName() + " joined the game&");
			}
		} else if (CommandConstruct.match(args, STICK) && sender.hasPermission("mclink.stick")) { 
			if (sender instanceof Player) {
				Player p = (Player) sender;
				p.getInventory().addItem(STICKODOOM);
			}
		} else if (CommandConstruct.match(args, SWAT_COMMAND) && sender.hasPermission("mclink.swat")) { 
			if (sender instanceof Player) {
				Player p = (Player) sender;
				p.getInventory().addItem(SWAT);
			}
		} else if (CommandConstruct.match(args, INVSEE) && sender.hasPermission("mclink.invsee")) { 
			if (sender instanceof Player) {
				Player p = (Player) sender;
				p.openInventory(PlayerUtil.getPlayer(args[2]).getInventory());
			}
		} else if (CommandConstruct.match(args, CHATMODE)) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args[2].equals("all")) {
					Chat.modes.put(p, Chat.ChatMode.ALL);
					p.sendMessage("Chat Mode set to ALL");
				} else {
					p.sendMessage("Chat Mode has to be all/discord/mc");
				}
			}
		} else if (CommandConstruct.match(args, CLEAR) && sender.hasPermission("mclink.clear")) {
			if (McLink.instance.bot != null) {
				for (Guild g : McLink.instance.bot.bot.getGuilds()) {
					List<Message> ms = g.getTextChannelsByName("general", false).get(0).getHistory().retrievePast(Integer.valueOf(args[2])).complete();
					System.out.println(ms.size());
					for (Message m : ms) {
						if (m.getAuthor().equals(McLink.instance.bot.bot.getSelfUser())) {
							m.delete().queue();
							System.out.println("Cleared");
						}
						System.out.println("Not Cleared");
					}
				}
			}
		} else if (CommandConstruct.match(args, PUNISH_FREEZE) && sender.hasPermission("mclink.freeze")) {
			Player p = PlayerUtil.getPlayer(args[3]);
			
			if (FREEZED.contains(p)) {
				FREEZED.remove(p);
			} else {
				FREEZED.add(p);
			}
		} else if (CommandConstruct.match(args, FRIDGE_COMMAND) && sender.hasPermission("mclink.freeze")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				p.getInventory().addItem(FRIDGE);
			}
		} else if (CommandConstruct.match(args, WITHER_COMMAND) && sender.hasPermission("mclink.wither")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				p.getInventory().addItem(WITHER);
			}
		} else if (CommandConstruct.match(args, TRANSLATE)) {
			sender.sendMessage(Translator.translate("en_US", args[2]));
		} else {
			sender.sendMessage(CommandConstruct.getNoArgsMessage());
		}
		
		return true;
	}*/

}
