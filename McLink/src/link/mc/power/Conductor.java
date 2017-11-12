package link.mc.power;

import org.bukkit.Location;

import link.mc.math.Node;

public class Conductor implements Node {

	public float powerLevel = 0;
	public Location location;
	
	public Conductor(Location location) {
		this.location = location;
	}
	
	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public Object getValue() {
		return powerLevel;
	}

	@Override
	public void setValue(Object powerLevel) {
		this.powerLevel = (float) powerLevel;
	}

}
