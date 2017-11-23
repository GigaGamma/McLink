package link.mc.gui;

import org.bukkit.inventory.ItemStack;

public class ItemPosition extends RawItemPosition {
	
	public int x;
	public int y;
	
	public ItemPosition(int x, int y, ItemStack item) {
		this(x, y, item, null);
	}
	
	public ItemPosition(int x, int y, ItemStack item, Component component) {
		this.x = x;
		this.y = y;
		setItem(item);
		setComponent(component);
	}
	
	@Override
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
	
}
