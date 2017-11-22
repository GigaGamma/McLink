package link.mc.kryan;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.bukkit.event.inventory.InventoryType;

import link.mc.event.ActionBind;
import link.mc.event.InventoryRunnable;
import link.mc.util.LoopUtil;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Crafting {
	
	public String state() default "main";
	public Type type() default Type.IN_OUT;
	
	public static enum Type {
		IN_OUT
	}
	
	public static class Registry {
			
		public static InventoryRunnable recipe(InventoryRunnable r) {
			ActionBind.invrun.add(r);
			return r;
		}
		
	}

	
}
