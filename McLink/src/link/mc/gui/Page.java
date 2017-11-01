package link.mc.gui;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class Page implements Gui {
	
	private String name;
	private Layout layout;
	
	public Page(String name, Layout layout) {
		this.name = name;
		this.layout = layout;
	}
	
	public Inventory compileItems() {
		Inventory i = Bukkit.getServer().createInventory(null, this.layout.getSize(), this.name);
		for (ItemPosition p : this.layout.getItems()) {
			i.setItem(p.calculatePosition(), p.getItem());
		}
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

}
