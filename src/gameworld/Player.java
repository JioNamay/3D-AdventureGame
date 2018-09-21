package gameworld;

/**
 * The Class Player.
 * 
 * @author Deanne Alabastro 
 */
public class Player {
	
	/** The location. */
	private Location location;
	
	/** The inventory. */
	private Inventory inventory;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param location the location
	 * @param inventory the inventory
	 */
	public Player(Location location, Inventory inventory) {
		this.location = location;
		this.inventory = inventory;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Location getLocation() {
		return this.location;
	}
	
	/**
	 * Moves the player.
	 *
	 * @param location the location
	 */
	public void move(Location location) {
		// NOTE, ADD CHECKS FOR IF LOCATION IS MOVEABLE
		this.location = location;
	}
	
	/**
	 * Gets the inventory.
	 *
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return this.inventory;
	}
}
