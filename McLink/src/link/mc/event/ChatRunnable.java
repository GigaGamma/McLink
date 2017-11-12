package link.mc.event;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public abstract class ChatRunnable implements Runnable {
	
	private AsyncPlayerChatEvent event;
	
	public AsyncPlayerChatEvent getEvent() {
		return event;
	}

	public void setEvent(AsyncPlayerChatEvent event) {
		this.event = event;
	}
	
}
