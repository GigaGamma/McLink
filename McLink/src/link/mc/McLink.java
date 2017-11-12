package link.mc;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Event.Result;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;

import link.mc.command.Commands;
import link.mc.command.McLinkCommand;
import link.mc.command.McLinkCommandMap;
import link.mc.command.Tabby;
import link.mc.config.Configurator;
import link.mc.config.McLinkConfig;
import link.mc.currency.BalanceCommand;
import link.mc.data.Database;
import link.mc.event.ActionBind;
import link.mc.event.BlockProtect;
import link.mc.event.ChatRunnable;
import link.mc.event.InteractRunnable;
import link.mc.event.McLinkInitEvent;
import link.mc.external.discord.Bot;
import link.mc.external.discord.BotThread;
import link.mc.external.discord.BroadcastCommand;
import link.mc.external.discord.McLinkBotCommand;
import link.mc.external.verify.VerifyCommand;
import link.mc.js.McJs;
import link.mc.secure.ActionFactory;
import link.mc.updater.UpdateCommand;
import link.mc.util.Logg;
import link.mc.util.MarkupUtil;
import link.mc.util.ServerUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;

public class McLink extends JavaPlugin {
	
	public static final String VERSION = "0.1-beta";
	
	public static McLink instance;
	public static McLinkConfig config;
	public FileConfiguration cfg;
	
	public Bot bot;
	public OutputStream logCapturingStream;
	public StreamHandler customLogHandler;
	public Logg logg = new Logg();
	public Database database;
	public BotThread bott = new BotThread();
	public Thread st = new Thread() {
		
		@Override
		public void run() {
			McLink.instance.bot.bot.shutdown();
			System.out.println("Shutdown Bot");
		}
		
	};
	
	@Override
	public void onEnable() {		
		McLink.instance = this;
		McLink.config = new McLinkConfig();
		
		System.out.println("McLink config path is " + McLink.instance.getDataFolder().getAbsolutePath().toLowerCase());
		cfg = YamlConfiguration.loadConfiguration(Paths.get(McLink.instance.getDataFolder().getAbsolutePath(), "config.yml").toFile());
		
		System.out.println(cfg.getString("username"));
		//Configurator.read(McLink.config, YamlConfiguration.loadConfiguration(Paths.get(McLink.instance.getDataFolder().getAbsolutePath(), "config.yml").toFile()));
		Configurator.read(McLink.config, cfg);
		
		database = new Database(Paths.get(McLink.instance.getDataFolder().getParentFile().getAbsolutePath(), ".db").toFile());
		
		logCapturingStream = new ByteArrayOutputStream();
		Handler[] handlers = Bukkit.getLogger().getParent().getHandlers();
		customLogHandler = new StreamHandler(logCapturingStream, handlers[0].getFormatter());
		customLogHandler.setFormatter(logg);
		
		Bukkit.getLogger().addHandler(customLogHandler);
		getLogger().addHandler(customLogHandler);
		
		Bukkit.getServer().getLogger().addHandler(customLogHandler);
		
		// Command Anti-Exploiting

		
		if (McLink.config.bot != null) {
			McLink.instance.bot = new Bot(); 
			Commands.registerBotCommand(new McLinkBotCommand());
		}
		
		Commands.register(new UpdateCommand());
		Commands.register(new McLinkCommand());
		Commands.register(new BroadcastCommand());
		Commands.register(new VerifyCommand());
		Commands.register(BalanceCommand.create());
		
		McJs.init();
		
		//bot.broadcast(new MessageBuilder().setEmbed(new EmbedBuilder().setColor(Color.GREEN).setTitle("All is good! Systems operational!").build()).build());
		
		Bukkit.getPluginManager().registerEvents(new BlockProtect(), this);
		Bukkit.getPluginManager().registerEvents(new Chat(), this);
		Bukkit.getPluginManager().registerEvents(new ActionFactory(), this);
		
		Bukkit.getPluginManager().registerEvents(new ActionBind(), this);
		
		McLinkInitEvent e = new McLinkInitEvent();
		Bukkit.getServer().getPluginManager().callEvent(e);
		
		ActionBind.run.add(new InteractRunnable() {
			
			@Override
			public void run() {
				if (getEvent().getAction().equals(Action.RIGHT_CLICK_BLOCK) && getEvent().getClickedBlock().getType().equals(Material.CHEST) && McLinkCommand.CLOAKED.contains(getEvent().getPlayer())) {
					System.out.println("C");
					Chest c = (Chest) getEvent().getClickedBlock().getState();
					Inventory inv = Bukkit.createInventory(null, c.getInventory().getSize(), c.getCustomName() == null ? "Chest" : c.getCustomName());
					for (int i = 0; i < c.getInventory().getContents().length; i++) {
						if (c.getInventory().getContents()[i] != null)
							inv.setItem(i, c.getInventory().getContents()[i]);
					}
					getEvent().getPlayer().openInventory(inv);
					getEvent().setCancelled(true);
					ActionBind.openchests.put(inv, c);
				} else if (getEvent().getAction().equals(Action.RIGHT_CLICK_BLOCK) && (getEvent().getClickedBlock().getType().equals(Material.WALL_SIGN) || getEvent().getClickedBlock().getType().equals(Material.SIGN_POST)) && getEvent().getPlayer().hasPermission("mclink.signedit")) {
					Sign s = (Sign) getEvent().getClickedBlock().getState();
					getEvent().getPlayer().sendMessage("Type the new lines for your sign seperated by a \\n");
					getEvent().getPlayer().sendMessage("Say \"cancel\" to cancel");
					PlayerInteractEvent ev = getEvent();
					Chat.run.add(new ChatRunnable() {
						
						boolean o;
						
						public ChatRunnable init() {
							o = true;
							return this;
						}
						
						@Override
						public void run() {
							if (getEvent().getPlayer().equals(ev.getPlayer()) && o) {
								if (getEvent().getMessage().equalsIgnoreCase("cancel")) {
									o = false;
									ev.getPlayer().sendMessage("Cancelled");
									getEvent().setCancelled(true);
								} else if (o) {
									for (int i = 0; i < 4; i++) {
										if (getEvent().getMessage().split(Pattern.quote("\\n")).length > i &&  getEvent().getMessage().split(Pattern.quote("\\n"))[i].length() != 0)
											s.setLine(i, getEvent().getMessage().split(Pattern.quote("\\n"))[i]);
									}
									s.update();
									o = false;
									ev.getPlayer().sendMessage("Sign Modified");
									getEvent().setCancelled(true);
								}
							}
						}
						
					}.init());
				}
			}
			
		});
		
		//Bukkit.getScheduler().runTaskTimer(this, new ActionBind.ItemTagTimer(), 0, 100);
	}
	
	@Override
	public void onDisable() {
		try {
			McLink.instance.database.getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		McLink.instance.bot.shutdown();
		bott.enabled = false;
		database.commit();
		//database.closeConnection();
		System.out.println("Shutdown Database");
		
		Bukkit.getLogger().removeHandler(customLogHandler);
		getLogger().removeHandler(customLogHandler);
		
		Bukkit.getServer().getLogger().removeHandler(customLogHandler);
	}
	
}
