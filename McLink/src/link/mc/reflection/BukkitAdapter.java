package link.mc.reflection;

import org.bukkit.Bukkit;

public class BukkitAdapter {
	
	public static String getServerVersion() {
		return Bukkit.getServer().getClass().getPackage().getName().replace('.', ',').split(",")[3];
	}
	
	public static Class<?> getNMSClass(String name) {
		return ReflectionUtils.getClass("net.minecraft.server." + getServerVersion() + "." + name);
	}
	
	public static Class<?> getBukkitClass(String name) {
		return ReflectionUtils.getClass("org.bukkit.craftbukkit." + getServerVersion() + "." + name);
	}
	
}
