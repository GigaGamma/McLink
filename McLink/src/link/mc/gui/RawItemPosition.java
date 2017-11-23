package link.mc.gui;

import org.bukkit.inventory.ItemStack;

public class RawItemPosition {
	
	private int p;
	private Component component;
	private ItemStack item;
	
	public RawItemPosition() {
		
	}
	
	public RawItemPosition(int p, Component component, ItemStack item) {
		this.p = p;
		this.component = component;
		this.item = item;
	}
	
	public int calculatePosition() {
		return p;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}
	
}
