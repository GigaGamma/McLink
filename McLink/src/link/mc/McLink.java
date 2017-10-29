package link.mc;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import link.mc.command.Commands;
import link.mc.command.McLinkCommand;
import link.mc.data.Database;
import link.mc.external.discord.Bot;
import link.mc.external.discord.BotThread;
import link.mc.external.discord.BroadcastCommand;
import link.mc.external.discord.McLinkBotCommand;
import link.mc.external.verify.VerifyCommand;
import link.mc.secure.ActionFactory;
import link.mc.util.Logg;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;

public class McLink extends JavaPlugin {
	
	public static McLink instance;
	
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
		
		database = new Database(new File("plugins/.db"));
		
		logCapturingStream = new ByteArrayOutputStream();
		Handler[] handlers = Bukkit.getLogger().getParent().getHandlers();
		customLogHandler = new StreamHandler(logCapturingStream, handlers[0].getFormatter());
		customLogHandler.setFormatter(logg);
		
		Bukkit.getLogger().addHandler(customLogHandler);
		getLogger().addHandler(customLogHandler);
		
		Bukkit.getServer().getLogger().addHandler(customLogHandler);
		
		//bott.start();
		
		McLink.instance.bot = new Bot(); 
		Commands.registerBotCommand(new McLinkBotCommand());
		
		Commands.register(new McLinkCommand());
		Commands.register(new BroadcastCommand());
		Commands.register(new VerifyCommand());
		
		//bot.broadcast(new MessageBuilder().setEmbed(new EmbedBuilder().setColor(Color.GREEN).setTitle("All is good! Systems operational!").build()).build());
		
		Bukkit.getPluginManager().registerEvents(new Chat(), this);
		Bukkit.getPluginManager().registerEvents(new ActionFactory(), this);
	}
	
	@Override
	public void onDisable() {
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
