package gameworld;

public class Player {
	
	private Location location;
	private Inventory inventory;
	
	public Player(Location location, Inventory inventory) {
		this.location = location;
		this.inventory = inventory;
	}

	public Location getLocation() {
		return this.location;
	}
	
	public void move(Location location) {
		this.location = location;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	
	
	

}
