package link.mc.kryan;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import link.mc.util.MarkupUtil;

public class Container {
	
	private Inventory inventory;

	public Container(String name, InventoryType type) {
		this.inventory = Bukkit.createInventory(null, type, name);
	}
	
	public Container(String name, int size) {
		this.inventory = Bukkit.createInventory(null, size, name);
	}
	
	public boolean is(Inventory i) {
		return this.inventory.getName().equalsIgnoreCase(this.inventory.getName()) || this.inventory.getType().equals(i.getType());
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
}
