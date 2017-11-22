package link.mc.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import link.mc.util.PlayerUtil;
import link.mc.util.ServerUtil;

public class Tabby implements TabCompleter {
	
	public static List<Tabby> tabbys = new ArrayList<Tabby>();
	
	private Command command;
	private Map<Permission, String[]> options;
	
	public Tabby(Command command, Map<Permission, String[]> options) {
		this.setCommand(command);
		this.setOptions(options);
		
		tabbys.add(this);
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> auto = new ArrayList<String>();
		
		if (command != this.command)
			return auto;
		
		for (Permission p : options.keySet()) {
			if (p == null || sender.hasPermission(p)) {
				//auto.add(String.join(" ", options.get(p)));
				for (int i = 0; i < options.get(p).length; i++) {
					String s = options.get(p)[i];
					/*if (((args.length == 0 && i == 0) || (args.length - 1 == i && s.startsWith(args[i]))) && !auto.contains(options.get(p)[0])) {
						//if (options.get(p).length > i + 1 && !auto.contains(options.get(p)[i + 1])) {
							auto.add(options.get(p)[i]);
						//}
					}/* else if (args.length == 0 && !auto.contains(options.get(p)[0])) {
						auto.add(options.get(p)[0]);
					}*/
					if (args.length != 0 && args.length == i && !auto.contains(options.get(p)[0]) && options.get(p)[i - 1].equalsIgnoreCase(args[i - 1])) {
						auto.add(options.get(p)[i]);
					} else if (args.length == 0 && i == 0 && !auto.contains(options.get(p)[0])) {
						auto.add(options.get(p)[i]);
					}
				}
			}
		}
		
		return auto;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public Map<Permission, String[]> getOptions() {
		return options;
	}

	public void setOptions(Map<Permission, String[]> options) {
		this.options = options;
	}

}
