package link.mc.power;

import link.mc.math.Network;
import link.mc.math.UpdateAlgorithm;

public class ElectricalNetwork extends Network {
	
	public EnergyAlgorithm a;
	
	@Override
	public void update() {
		a.nodes = nodes;
		a.update();
	}
	
}
