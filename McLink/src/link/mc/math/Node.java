package link.mc.math;

import org.bukkit.Location;

public interface Node {
	
	public Location getLocation();
	public Object getValue();
	public void setValue(Object object);
	
}
