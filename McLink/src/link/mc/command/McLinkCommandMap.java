package link.mc.command;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import link.mc.util.PlayerUtil;

public class McLinkCommandMap implements CommandMap {
	
	private CommandMap cmap;
	
	public McLinkCommandMap(CommandMap cmap) {
		this.cmap = cmap;
	}
	
	@Override
	public void clearCommands() {
		this.cmap.clearCommands();
	}

	@Override
	public boolean dispatch(CommandSender arg0, String arg1) throws CommandException {
		return cmap.dispatch(arg0, arg1);
	}

	@Override
	public Command getCommand(String arg0) {
		return cmap.getCommand(arg0);
	}

	@Override
	public boolean register(String arg0, Command arg1) {
		return this.cmap.register(arg0, arg1);
	}

	@Override
	public boolean register(String arg0, String arg1, Command arg2) {
		return this.cmap.register(arg0, arg1, arg2);
	}

	@Override
	public void registerAll(String arg0, List<Command> arg1) {
		this.cmap.registerAll(arg0, arg1);
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String arg1) throws IllegalArgumentException {
		List<String> l = this.cmap.tabComplete(sender, arg1);
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (sender instanceof Player && ((Player) sender).getPlayer().canSee(p))
				l.add(p.getName());
		}
		
		return l;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String arg1, Location arg2) throws IllegalArgumentException {
		List<String> l = this.cmap.tabComplete(sender, arg1, arg2);
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (sender instanceof Player && ((Player) sender).getPlayer().canSee(p))
				l.add(p.getName());
		}
		
		return l;
	}

}
