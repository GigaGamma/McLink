package link.mc.event;

import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class BlockPlaceRunnable implements Runnable {
	
	private BlockPlaceEvent event;
	
	public BlockPlaceEvent getEvent() {
		return event;
	}

	public void setEvent(BlockPlaceEvent event) {
		this.event = event;
	}
	
}
