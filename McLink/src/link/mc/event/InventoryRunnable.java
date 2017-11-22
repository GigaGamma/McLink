package link.mc.event;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public abstract class InventoryRunnable implements Runnable {
	
	private InventoryClickEvent event;
	
	public InventoryClickEvent getEvent() {
		return event;
	}

	public void setEvent(InventoryClickEvent event) {
		this.event = event;
	}
	
}
