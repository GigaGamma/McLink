package link.mc.kryan;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.bukkit.command.CommandSender;

import link.mc.util.CommandConstruct;

public class VCommand extends org.bukkit.command.Command {

	public Class<?> c;
	public Object ins;
	
	public VCommand(Class<?> c, String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
		this.c = c;
		try {
			ins = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean execute(CommandSender arg0, String arg1, String[] arg2) {
		for (Method m : c.getDeclaredMethods()) {
			if (m.isAnnotationPresent(Command.Subcommand.class) && CommandConstruct.match(arg2, m.getAnnotation(Command.Subcommand.class).args())) {
				try {
					if (m.getAnnotation(Command.Subcommand.class).permission().equals("_")) {
						m.invoke(ins, arg0, arg1, arg2);
						return true;
					} else if (arg0.hasPermission(m.getAnnotation(Command.Subcommand.class).permission())) {
						m.invoke(ins, arg0, arg1, arg2);
						return true;
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {e.printStackTrace();}
			}
		}
		
		arg0.sendMessage(CommandConstruct.getNoArgsMessage());
		
		return true;
	}

}
