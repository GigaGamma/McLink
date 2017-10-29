package link.mc.external.verify;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import link.mc.McLink;
import net.dv8tion.jda.core.entities.User;

public class VerifyCommand extends Command {

	public VerifyCommand() {
		super("verify", "The verify command allows you to link social accounts", "/verify [network] [user]", new ArrayList<String>());
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (!(sender instanceof Player)) return true;
		
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("discord")) {
				if (McLink.instance.bot == null) {sender.sendMessage("This server does not have Discord integration configured"); return true;}
				
				List<User> u = McLink.instance.bot.bot.getUsersByName(args[1].split("#")[0], false);
				if (u.size() > 0) {
					for (User us : u) {
						//System.out.println(us.getDiscriminator());
						if (us.getDiscriminator().equals(args[1].split("#")[1])) {
							us.openPrivateChannel().complete().sendMessage("Hey " + ((Player) sender).getName()).queue();
							return true;
						}
					}
				}
				
				sender.sendMessage("We couldn't find a Discord user by that name. Make sure that the user is formatted like so: user#id\nBefore using the verify command, you must join this server's Discord server!");
			}
		}
		
		return true;
	}

}
