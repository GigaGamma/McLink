package link.mc.gui;

import org.bukkit.inventory.ItemStack;

public class ItemPosition {
	
	public int x;
	public int y;
	
	public ItemStack item;
	public Component component;
	
	public ItemPosition(int x, int y, ItemStack item) {
		this(x, y, item, null);
	}
	
	public ItemPosition(int x, int y, ItemStack item, Component component) {
		this.x = x;
		this.y = y;
		
		this.item = item;
		this.component = component;
	}
	
	public int calculatePosition() {
		return (this.y * 9) + this.x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}
	
}
