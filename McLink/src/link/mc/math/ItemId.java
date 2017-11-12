package link.mc.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemId {
	
	public static String generate() {
		return UUID.randomUUID().toString();
	}
	
	public static ItemStack attach(ItemStack i, String id) {
		ItemMeta m = i.getItemMeta();
		
		List<String> lore = new ArrayList<String>();
		
		lore.addAll(Arrays.asList(new String[] {ChatColor.RESET + "" + ChatColor.DARK_GRAY + id}));
		if (m.getLore() != null && !m.getLore().isEmpty()) {
			lore.add("");
			lore.addAll(m.getLore());
		}
		
		m.setLore(lore);
		i.setItemMeta(m);
		
		return i;
	}
	
	public static ItemStack attach(ItemStack i) {
		return ItemId.attach(i, ItemId.generate());
	}
	
	public static String extract(ItemStack i) {
		if (i == null || i.getType().equals(Material.AIR) || i.getItemMeta() == null || i.getItemMeta().getLore() == null || i.getItemMeta().getLore().isEmpty())
			return "";
		return i.getItemMeta().getLore().get(0);
	}
	
	public static boolean same(ItemStack i, ItemStack i2) {
		return extract(i).equals(extract(i2));
	}
	
}
