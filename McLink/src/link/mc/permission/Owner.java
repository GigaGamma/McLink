package link.mc.permission;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;

public class Owner implements Rank {

	@Override
	public String getName() {
		return "Owner";
	}

	@Override
	public String getId() {
		return "admin.owner";
	}

	@Override
	public ChatColor getColor() {
		return ChatColor.GOLD;
	}

	@Override
	public boolean isOpped() {
		return true;
	}

	@Override
	public List<Permission> getPerms() {
		List<Permission> p = new ArrayList<Permission>();
		
		p.add(new Permission("minecraft.command.stop"));
		
		return p;
	}

}
