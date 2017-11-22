package link.mc.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import link.mc.command.McLinkCommand;
import link.mc.math.ItemId;
import link.mc.util.LoopUtil;

public class ActionBind implements Listener {
	
	public static LoopUtil<InteractRunnable> run = new LoopUtil<InteractRunnable>();
	public static LoopUtil<InventoryRunnable> invrun = new LoopUtil<InventoryRunnable>();
	public static Map<Inventory, Chest> openchests = new HashMap<Inventory, Chest>();
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		// (event.getItem().getItemMeta().getDisplayName() != null && event.getItem().getItemMeta().getDisplayName().equals(McLinkCommand.STICKODOOM.getItemMeta().getDisplayName()))
		
		/*if (event.getItem() == McLinkCommand.STICKODOOM) {
			event.getPlayer().sendMessage("A");
		}*/
		
		/*if (event.getItem() == null)
			return;
		
		if (ItemId.same(event.getItem(), McLinkCommand.STICKODOOM)) {
			event.getPlayer().getWorld().strikeLightning(event.getPlayer().getTargetBlock(null, 100).getLocation());
		}*/
		/*if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.CHEST) && McLinkCommand.CLOAKED.contains(event.getPlayer())) {
			System.out.println("C");
			Chest c = (Chest) event.getClickedBlock().getState();
			event.getPlayer().openInventory(c.getInventory());
			event.setCancelled(true);
		}*/
		ListIterator<InteractRunnable> i = run.update();
		while (i.hasNext()) {
			InteractRunnable r = i.next();
			r.setEvent(event);
			r.run();
		}
	}
	
	@EventHandler
	public void click(InventoryClickEvent event) {
		ListIterator<InventoryRunnable> in = invrun.update();
		while (in.hasNext()) {
			InventoryRunnable r = in.next();
			r.setEvent(event);
			r.run();
		}
	}
	
	@EventHandler
	public void onInventoryMoveItem(InventoryInteractEvent event) {
		//System.out.println("E");
		
		if (openchests.containsKey(event.getInventory())) {
			System.out.println("move");
			openchests.get(event.getInventory()).getInventory().setContents(event.getInventory().getContents());
			openchests.remove(event.getInventory());
		} else {
			for (int i = 0; i < openchests.size(); i++) {
				Chest c = ((List<Chest>) openchests.values()).get(i);
				if (c.getInventory().equals(event.getInventory())) {
					((List<Inventory>) openchests.keySet()).get(i).setContents(event.getInventory().getContents());
				}
			}
		}
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		if (openchests.containsKey(event.getInventory())) {
			System.out.println("close");
			openchests.get(event.getInventory()).getInventory().setContents(event.getInventory().getContents());
			openchests.remove(event.getInventory());
		}
		/*if (openchests.containsKey(event.getInventory())) {
			for (int i = 0; i < openchests.get(event.getInventory()).getInventory().getSize(); i++) {
				openchests.get(event.getInventory()).getInventory().setItem(i, event.getInventory().getContents()[i]);
			}
			openchests.remove(event.getInventory());
		}*/
	}
	
	@EventHandler
	public void onNewItem(PlayerItemHeldEvent event) {
		for (int s = 0; s < 103; s++) {
			ItemStack i = event.getPlayer().getInventory().getItem(s);
			if (i != null && i.getItemMeta() != null && !i.getType().equals(Material.AIR) && ItemId.extract(i).equals("")) {
				event.getPlayer().getInventory().setItem(s, ItemId.attach(i, "minecraft:" + i.getType().name().toLowerCase()));
			}
		}
	}
	
	public static class ItemTagTimer implements Runnable {

		@Override
		public void run() {
			for (Player p : Bukkit.getOnlinePlayers()) {
				for (int s = 0; s < 103; s++) {
					ItemStack i = p.getInventory().getItem(s);
					if (i != null && i.getItemMeta() != null && !i.getType().equals(Material.AIR) && ItemId.extract(i).equals("")) {
						p.getInventory().setItem(s, ItemId.attach(i, "minecraft:" + i.getType().name().toLowerCase()));
					}
				}
			}
		}
		
	}
	
}
