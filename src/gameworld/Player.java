package gameworld;

public class Player {
	
	private Location location;
	private MoveableItem[] inventory = new MoveableItem[10];
	
	public Player(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return this.location;
	}
	
	public void move(Location location) {
		this.location = location;
	}
	
	public MoveableItem[] getInventory() {
		return null;
	}
	
	

}
