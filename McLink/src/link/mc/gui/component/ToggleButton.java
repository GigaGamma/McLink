package link.mc.gui.component;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import link.mc.gui.Component;
import link.mc.gui.Gui;
import link.mc.gui.ItemPosition;
import link.mc.gui.Page;
import link.mc.util.item.ItemConstruct;

public class ToggleButton implements Component {
	
	private String oname;
	private boolean enabled = false;
	
	public enum DataStates {
		
		TRUE ((short) 5),
		FALSE ((short) 14);
		
		private final short data;
		
		DataStates(short data) {
			this.data = data;
		}
		
		public short getData() {
			return this.data;
		}
		
	}
	
	public static DataStates getDataFromBoolean(boolean v) {
		return v ? DataStates.TRUE : DataStates.FALSE;
	}
	
	@Override
	public void onLoad(Gui gui, ItemPosition item) {
		item.item.setDurability(getDataFromBoolean(this.enabled).getData());
		
		ItemMeta m = item.item.getItemMeta();
		this.oname = m.getDisplayName();
		m.setDisplayName(this.oname.replace("$nstate", enabled ? "Disable" : "Enable"));
		item.item.setItemMeta(m);
	}
	
	@Override
	public void onClick(Gui gui, InventoryClickEvent event) {
		this.enabled = !this.enabled;
		event.getCurrentItem().setDurability(getDataFromBoolean(this.enabled).getData());
		
		ItemMeta m = event.getCurrentItem().getItemMeta();
		m.setDisplayName(this.oname.replace("$nstate", enabled ? "Disable" : "Enable"));
		event.getCurrentItem().setItemMeta(m);
		
		event.setCancelled(true);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
