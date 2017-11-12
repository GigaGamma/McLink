package link.mc.math;

import java.util.ArrayList;
import java.util.List;

public class Network {
	
	public List<Node> nodes = new ArrayList<Node>();
	
	public void update(UpdateAlgorithm a) {
		a.update();
	}
	
}
