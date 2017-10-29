package link.mc.reflection;

import java.lang.reflect.Method;

import org.bukkit.Bukkit;

public class ReflectionUtils {
	
	public static Class<?> getClass(String n) {
		try {
			return Class.forName(n);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Method getMethod(Class<?> c, String n) {
		try {
			return c.getDeclaredMethod(n);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Method getMethod(Class<?> c, String n, Class<?>[] p) {
		try {
			return c.getDeclaredMethod(n, p);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
