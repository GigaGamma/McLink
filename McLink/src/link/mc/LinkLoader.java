package link.mc;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class LinkLoader {
	
	private Plugin plugin;
	private YamlConfiguration config;
	
	public LinkLoader(Plugin plugin) {
		setPlugin(plugin);
		File[] files = plugin.getDataFolder().listFiles();
		for (File file : files) {
			if (file.getName().equalsIgnoreCase("mclink.yml"))
				setConfig(YamlConfiguration.loadConfiguration(file));
		}
	}

	public Plugin getPlugin() {
		return plugin;
	}

	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}

	public YamlConfiguration getConfig() {
		return config;
	}

	public void setConfig(YamlConfiguration config) {
		this.config = config;
	}
	
}
