package link.mc.kryan;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import link.mc.kryan.Item.ActionType;
import link.mc.util.LoopUtil;

@Retention(RUNTIME)
@Target(TYPE)
public @interface Block {

	public String id();
	public String name();
	
	@Retention(RUNTIME)
	@Target(METHOD)
	public @interface Action {
		
		public ActionType type();
		
	}
	
	@Retention(RUNTIME)
	@Target(METHOD)
	public @interface State {
		
		public String name() default "main";
		
	}
	
	public static enum ActionType {
		USE
	}
	
	public static class Registry {
		
		public static LoopUtil<VBlock> blocks = new LoopUtil<VBlock>();
		
		public static VBlock block(Class<?> c) {
			VBlock v = new VBlock(c);
			blocks.add(v);
			return v;
		}
		
	}
	
}
