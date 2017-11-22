package link.mc.kryan;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import link.mc.McLink;
import link.mc.event.ActionBind;
import link.mc.event.InteractRunnable;
import link.mc.event.InventoryRunnable;
import link.mc.math.ItemId;

public class VItem {
	
	public Class<?> c;
	public Object ins;
	
	public VItem(Class<?> c) {
		this.c = c;
		try {
			ins = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ActionBind.run.add(new InteractRunnable() {
			
			@Override
			public void run() {
				try {
					if (ItemId.same(getEvent().getItem(), ItemId.attach((ItemStack) getMainState(c).invoke(ins), c.getAnnotation(Item.class).id())) && (getEvent().getAction().equals(Action.RIGHT_CLICK_AIR) || getEvent().getAction().equals(Action.RIGHT_CLICK_BLOCK)) && getUse(c) != null) {
						getUse(c).invoke(ins, getEvent());
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		for (String s : getCrafts(c).keySet()) {
			Method cr = getCrafts(c).get(s);
			ActionBind.invrun.add(new InventoryRunnable() {
				
				@Override
				public void run() {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(McLink.instance, new Runnable() {
							
						public void run() {
							try {
								boolean r = (boolean) cr.invoke(ins, getEvent().getInventory());
								if (cr.getAnnotation(Crafting.class).type().equals(Crafting.Type.IN_OUT) && r) {
									getEvent().getInventory().clear();
									getEvent().getInventory().setItem(Math.round(getEvent().getInventory().getSize() / 2), ItemId.attach((ItemStack) getStates(c).get(cr.getAnnotation(Crafting.class).state()).invoke(ins), c.getAnnotation(Item.class).id()));
								}
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
							
					}, 2);
				}
				
		});
		}
	}
	
	public static Map<Item.ActionType, Method> getActions(Class<?> c) {
		Map<Item.ActionType, Method> a = new HashMap<Item.ActionType, Method>();
		
		for (Method m : c.getMethods()) {
			if (m.isAnnotationPresent(Item.Action.class)) {
				a.put(m.getAnnotation(Item.Action.class).type(), m);
			}
		}
		
		return a;
	}
	
	public static Method getUse(Class<?> c) {
		Map<Item.ActionType, Method> m = getActions(c);
		
		if (m.containsKey(Item.ActionType.USE)) {
			return m.get(Item.ActionType.USE);
		}
		
		return null;
	}
	
	public static Map<String, Method> getStates(Class<?> c) {
		Map<String, Method> a = new HashMap<String, Method>();
		
		for (Method m : c.getMethods()) {
			if (m.isAnnotationPresent(Item.State.class)) {
				a.put(m.getAnnotation(Item.State.class).name(), m);
			}
		}
		
		return a;
	}
	
	public static Method getMainState(Class<?> c) {
		return getStates(c).get("main");
	}
	
	public static Map<String, Method> getCrafts(Class<?> c) {
		Map<String, Method> a = new HashMap<String, Method>();
		
		for (Method m : c.getMethods()) {
			if (m.isAnnotationPresent(Crafting.class)) {
				a.put(m.getAnnotation(Crafting.class).state(), m);
			}
		}
		
		return a;
	}
	
}
