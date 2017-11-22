package link.mc.kryan;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface Command {
	
	@Retention(RUNTIME)
	@Target(METHOD)
	public @interface Subcommand {
		public String[] args();
		public String permission() default "_";
	}
	
	public String name();
	public String description();
	public String usage() default "Nothing to see here...";
	
}
