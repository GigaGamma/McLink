package link.mc.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import link.mc.McLink;

public class Page implements Gui, Listener {
	
	private String name;
	private Layout layout;
	public List<Inventory> compiles = new ArrayList<Inventory>();
	
	public Page(String name, Layout layout) {
		this.name = name;
		layout.setGui(this);
		this.layout = layout;
		this.layout.init();
	}
	
	public void registerEvents() {
		Bukkit.getPluginManager().registerEvents(this, McLink.instance);
	}
	
	public Inventory compileItems() {
		Inventory i = Bukkit.getServer().createInventory(null, this.layout.getSize(), this.name);
		for (RawItemPosition p : this.layout.getItems()) {
			if (p.getComponent() != null) {
				p.getComponent().onLoad(this, p);
			}
			i.setItem(p.calculatePosition(), p.getItem());
		}
		compiles.add(i);
		return i;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Layout getLayout() {
		return layout;
	}
	
	public List<Inventory> getCompiles() {
		return this.compiles;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		for (RawItemPosition p : this.layout.getItems()) {
			if (event.getRawSlot() == p.calculatePosition() && this.compiles.contains(event.getInventory()) && p.getComponent() != null) {
				p.getComponent().onClick(this, event);
			}
		}
	}

}
