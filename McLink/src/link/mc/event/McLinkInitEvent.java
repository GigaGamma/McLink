package link.mc.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import link.mc.util.MarkupUtil;

public class McLinkInitEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	public McLinkInitEvent() {
		
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
