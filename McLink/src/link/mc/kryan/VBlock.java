package link.mc.kryan;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.bukkit.Location;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.LazyMetadataValue;
import org.bukkit.metadata.LazyMetadataValue.CacheStrategy;

import link.mc.McLink;
import link.mc.event.ActionBind;
import link.mc.event.BlockPlaceRunnable;
import link.mc.event.InteractRunnable;
import link.mc.math.ItemId;

public class VBlock {

	public Class<?> c;
	public Object ins;
	
	public VBlock(Class<?> c) {
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
					System.out.println("VBlock interact");
					if (!getEvent().getPlayer().isSneaking() && !getActions(c).isEmpty()) {
						for (Method m : getActions(c).values()) {
							System.out.println("Event triggered");
							m.invoke(ins, getEvent());
						}
					}
					/*else if (ItemId.same(getEvent().getItem(), ItemId.attach((ItemStack) VItem.getMainState(c).invoke(ins), c.getAnnotation(Item.class).id())) && getEvent().hasBlock() && (getEvent().getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
						placeBlock(ins, c, getEvent().getClickedBlock().getLocation());
						getEvent().setCancelled(true);
						System.out.println("A");
					}*/
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		ActionBind.placerun.add(new BlockPlaceRunnable() {
			
			@Override
			public void run() {
				try {
					if (ItemId.same(getEvent().getItemInHand(), ItemId.attach((ItemStack) VItem.getMainState(c).invoke(ins), c.getAnnotation(Item.class).id()))) {
						getEvent().setCancelled(true);
						placeBlock(ins, c, getEvent().getBlock().getLocation());
						System.out.println(getEvent().getBlock().getLocation());
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}
	
	public static Map<Block.ActionType, Method> getActions(Class<?> c) {
		Map<Block.ActionType, Method> a = new HashMap<Block.ActionType, Method>();
		
		for (Method m : c.getMethods()) {
			if (m.isAnnotationPresent(Block.Action.class)) {
				a.put(m.getAnnotation(Block.Action.class).type(), m);
			}
		}
		
		return a;
	}
	
	public static Method getUse(Class<?> c) {
		Map<Block.ActionType, Method> m = getActions(c);
		
		if (m.containsKey(Block.ActionType.USE)) {
			return m.get(Block.ActionType.USE);
		}
		
		return null;
	}
	
	public static Map<String, Method> getStates(Class<?> c) {
		Map<String, Method> a = new HashMap<String, Method>();
		
		for (Method m : c.getMethods()) {
			if (m.isAnnotationPresent(Block.State.class)) {
				a.put(m.getAnnotation(Block.State.class).name(), m);
			}
		}
		
		return a;
	}
	
	public static Method getMainState(Class<?> c) {
		return getStates(c).get("main");
	}
	
	@SuppressWarnings("deprecation")
	public static void placeBlock(Object ins, Class<?> c, Location l) {
		try {
			ItemStack s = (ItemStack) getMainState(c).invoke(ins, l);
			l.getWorld().getBlockAt(l).setType(s.getType());
			l.getWorld().getBlockAt(l).setData((byte) s.getDurability());
			
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
