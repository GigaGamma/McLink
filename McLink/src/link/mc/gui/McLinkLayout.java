package link.mc.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import link.mc.util.item.ItemConstruct;

public class McLinkLayout implements Layout {

	@Override
	public int getSize() {
		return 45;
	}

	@Override
	public List<ItemPosition> getItems() {
		List<ItemPosition> i = new ArrayList<ItemPosition>();
		
		i.add(new ItemPosition(1, 1, new ItemConstruct(Material.WOOD).getItemStack()));
		
		return i;
	}

}
