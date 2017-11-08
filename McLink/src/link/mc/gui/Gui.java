package link.mc.gui;

import org.bukkit.inventory.Inventory;

public interface Gui {
	
	public String getName();
	public Layout getLayout();
	public Inventory compileItems();
	
}
