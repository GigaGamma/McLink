package link.mc.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import link.mc.util.PlayerUtil;
import link.mc.util.ServerUtil;

public class Tabby implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> auto = new ArrayList<String>();
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (sender instanceof Player && ((Player) sender).getPlayer().canSee(p))
				auto.add(p.getName());
		}
		
		return auto;
	}

}
