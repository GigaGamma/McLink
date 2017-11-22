package link.mc.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import link.mc.kryan.Item;
import link.mc.kryan.VItem;
import link.mc.math.ItemId;

public class GiveCommand extends Command {
	
	public GiveCommand() {
		super("give", "Give", "/give ...", new ArrayList<String>());
	}
	
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		sender.sendMessage("The give command is WIP");
		
		if (sender instanceof Player && args.length == 1 && sender.hasPermission("mclink.give")) {
			Player p = (Player) sender;
			if (args[0].startsWith("minecraft:") || args[0].startsWith("mc:"))
				p.getInventory().addItem(new ItemStack(Material.getMaterial(args[0].split(Pattern.quote(":"))[1].toUpperCase())));
			else {
				ListIterator<VItem> vs = Item.Registry.items.update();
				
				while (vs.hasNext()) {
					VItem v = vs.next();
					if (args[0].equalsIgnoreCase(v.c.getAnnotation(Item.class).id())) {
						try {
							p.getInventory().addItem(ItemId.attach((ItemStack) VItem.getMainState(v.c).invoke(v.ins), v.c.getAnnotation(Item.class).id()));
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		return true;
	}
	
}
