package link.mc.command;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import link.mc.external.discord.BotCommand;
import link.mc.reflection.BukkitAdapter;

public class Commands {
	
	private static ArrayList<BotCommand> botCommands = new ArrayList<BotCommand>();
	
	public static CommandMap getCommandMap() {
		try {
			Field f = BukkitAdapter.getBukkitClass("CraftServer").getDeclaredField("commandMap");
			f.setAccessible(true);
			return (CommandMap) f.get(Bukkit.getServer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Command register(Command command) {
		Commands.getCommandMap().register("", command);
		return command;
	}
	
	public static ArrayList<BotCommand> getBotCommands() {
		return botCommands;
	}
	
	public static BotCommand registerBotCommand(BotCommand command) {
		Commands.getBotCommands().add(command);
		return command;
	}
	
}
