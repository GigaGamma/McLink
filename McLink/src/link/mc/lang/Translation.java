package link.mc.lang;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Translation {
	
	public static List<Translation> translations = new ArrayList<Translation>();
	
	private String name;
	private String id;
	private FileConfiguration config;
	
	public Translation(File f) {
		translations.add(this);
		this.config = YamlConfiguration.loadConfiguration(f);
		this.name = config.getString("info.language.name");
		this.id = config.getString("info.language.id");
	}
	

	public Translation(BufferedReader r) {
		translations.add(this);
		this.config = YamlConfiguration.loadConfiguration(r);
		this.name = config.getString("info.language.name");
		this.id = config.getString("info.language.id");
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public FileConfiguration getConfig() {
		return config;
	}

	public void setConfig(FileConfiguration config) {
		this.config = config;
	}

}
