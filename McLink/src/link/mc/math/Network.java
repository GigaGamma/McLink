package link.mc.math;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;

public class Network {
	
	public List<Node> nodes = new ArrayList<Node>();
	
	public void update() {
		
	}
	
	public Node getNodeFromBlock(Block block) {
		for (Node n : nodes) {
			if (n.getLocation().equals(block.getLocation())) {
				return n;
			}
		}
		return null;
	}
	
}
