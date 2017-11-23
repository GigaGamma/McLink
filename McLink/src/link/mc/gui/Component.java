package link.mc.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface Component {
	
	public void onLoad(Gui gui, RawItemPosition item);
	public void onClick(Gui gui, InventoryClickEvent event);
	
}
