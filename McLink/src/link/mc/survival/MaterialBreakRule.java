package link.mc.survival;

import org.bukkit.Material;

public class MaterialBreakRule {

	private Material material;
	private ToolClass toolClass;
	private ToolType toolType;
	private boolean canBreak;
	
	public MaterialBreakRule(Material material, ToolClass toolClass, ToolType toolType, boolean canBreak) {
		this.material = material;
		this.toolClass = toolClass;
		this.toolType = toolType;
		this.canBreak = canBreak;
	}
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public ToolClass getToolClass() {
		return toolClass;
	}

	public void setToolClass(ToolClass toolClass) {
		this.toolClass = toolClass;
	}

	public ToolType getToolType() {
		return toolType;
	}

	public void setToolType(ToolType toolType) {
		this.toolType = toolType;
	}

	public boolean isCanBreak() {
		return canBreak;
	}

	public void setCanBreak(boolean canBreak) {
		this.canBreak = canBreak;
	}
	
}
