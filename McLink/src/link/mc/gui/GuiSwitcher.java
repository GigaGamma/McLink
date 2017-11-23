package link.mc.gui;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;

import link.mc.McLink;

public class GuiSwitcher implements Component {
	
	private Gui gui;
	
	public GuiSwitcher(Gui gui) {
		this.gui = gui;
	}
	
	@Override
	public void onLoad(Gui gui, RawItemPosition item) {
		
	}

	@Override
	public void onClick(Gui gui, InventoryClickEvent event) {
		event.getWhoClicked().closeInventory();
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(McLink.instance, new Runnable() {
			
			@Override
			public void run() {
				event.getWhoClicked().openInventory(gui.compileItems());
			}
			
		}, 1L);
	}

}
