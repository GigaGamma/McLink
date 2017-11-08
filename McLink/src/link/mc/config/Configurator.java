package link.mc.config;

import java.lang.reflect.Field;
import java.util.logging.Level;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;

import link.mc.McLink;
import link.mc.external.discord.BotConfig;

public class Configurator {
	
	public static Object read(Object obj, FileConfiguration config) {
		return read(obj, config.getRoot());
	}
	
	public static Object read(Object obj, ConfigurationSection config) {
		for (Field field : obj.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(RequiredField.class)) {
				if (config.get(field.getName()) != null) {
					if (field.isAnnotationPresent(DetailedField.class)) {
						try {
							field.set(obj, read(field.get(obj), config.getConfigurationSection(field.getName())));
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
					} else {
						try {
							field.set(obj, config.get(field.getName()));
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				} else {
					McLink.instance.getLogger().log(Level.SEVERE, "The " + config.getCurrentPath() + "." + field.getName() + " field is required!");
				}
			} else if (field.isAnnotationPresent(OptionalField.class)) {
				if (config.get(field.getName()) != null) {
					if (field.isAnnotationPresent(DetailedField.class)) {
						try {
							field.set(obj, read(field.get(obj), config.getConfigurationSection(field.getName())));
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
					} else {
						try {
							field.set(obj, config.get(field.getName()));
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		return obj;
	}
	
}
