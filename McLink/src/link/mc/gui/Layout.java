package link.mc.gui;

import java.util.ArrayList;
import java.util.List;

public abstract class Layout implements ILayout {
	
	private Gui gui;
	
	public Layout() {
		
	}

	public Gui getGui() {
		return this.gui;
	}

	public void setGui(Gui gui) {
		this.gui = gui;
	}
	
}
