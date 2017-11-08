package link.mc.secure;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import link.mc.gui.ItemPosition;
import link.mc.gui.Layout;
import link.mc.gui.Page;
import link.mc.gui.GuiSwitcher;
import link.mc.gui.component.ToggleButton;
import link.mc.util.item.ItemConstruct;

public class AdminLayout extends Layout {

	List<ItemPosition> i = new ArrayList<ItemPosition>();
	
	public AdminLayout() {
		//i.add(new ItemPosition(1, 1, new ItemConstruct(Material.WOOL).getMeta().setName("").getItem(), new ToggleButton()));
	}
	
	@Override
	public int getSize() {
		return 45;
	}

	@Override
	public List<ItemPosition> getItems() {
		return i;
	}

	@Override
	public void init() {
		i.add(new ItemPosition(1, 1, new ItemConstruct(Material.WOOL).getMeta().setName("$nstate Radio Chatter").getItem(), new ToggleButton()));
	}

}
