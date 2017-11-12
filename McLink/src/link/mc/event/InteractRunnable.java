package link.mc.event;

import org.bukkit.event.player.PlayerInteractEvent;

public abstract class InteractRunnable implements Runnable {
	
	private PlayerInteractEvent event;
	
	public PlayerInteractEvent getEvent() {
		return event;
	}

	public void setEvent(PlayerInteractEvent event) {
		this.event = event;
	}
	
}
