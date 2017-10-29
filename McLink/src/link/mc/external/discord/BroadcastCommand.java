package link.mc.external.discord;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import link.mc.McLink;

public class BroadcastCommand extends Command {
	
	/*
	 * String name, String description, String usageMessage, List<String> aliases
	 */
	public BroadcastCommand() {
		super("broadcast", "The McLink Command", "/mclink ...", new ArrayList<String>());
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (McLink.instance.bot != null) {
			McLink.instance.bot.broadcast(String.join(" ", args));
		}
		
		return true;
	}

}
