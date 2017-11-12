package link.mc.math;

import java.util.ArrayList;
import java.util.List;

public interface UpdateAlgorithm {
	
	public List<Node> getNodes();
	public default List<Node> getConnectedNodes(Node node) {
		List<Node> ns = new ArrayList<Node>();
		
		for (Node n : getNodes()) {
			if (!n.equals(node) && n.getLocation().distance(node.getLocation()) < 1) {
				ns.add(n);
			}
		}
		
		return ns;
	}
	public void update();
	
}
