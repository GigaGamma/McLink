package link.mc.secure;

public enum Cheats {
	
	FLIGHT (ActionType.SURVEY),
	KILLAURA (ActionType.SURVEY),
	REACH (ActionType.SURVEY),
	BHOP (ActionType.SURVEY),
	SCAFFOLD (ActionType.SURVEY),
	SPEED_HACKING (ActionType.SURVEY);
	
	private ActionType action;
	
	Cheats (ActionType action) {
		this.action = action;
	}
	
	public ActionType action() {
		return this.action;
	}
	
}
