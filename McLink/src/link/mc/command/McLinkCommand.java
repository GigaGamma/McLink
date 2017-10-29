package link.mc.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import link.mc.secure.ActionFactory;
import link.mc.secure.Cheats;

public class McLinkCommand extends Command {
	
	/*
	 * String name, String description, String usageMessage, List<String> aliases
	 */
	public McLinkCommand() {
		super("mclink", "The McLink Command", "/mclink ...", new ArrayList<String>());
	}

	@Override
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
		
		return true;
	}

}
