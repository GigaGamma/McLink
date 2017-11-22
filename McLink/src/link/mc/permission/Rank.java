package link.mc.permission;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;

public interface Rank {
	
	public String getName();
	public String getId();
	public ChatColor getColor();
	public boolean isOpped();
	public List<Permission> getPerms();
	
}
