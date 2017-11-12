package link.mc.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockProtect implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (!event.getPlayer().hasPermission("mclink.build"))
			event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (!event.getPlayer().hasPermission("mclink.build"))
			event.setCancelled(true);
	}
	
}
