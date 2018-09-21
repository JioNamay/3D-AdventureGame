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

	private int health;

	/**
	 * Instantiates a new player.
	 *
	 * @param location
	 *            the location
	 * @param inventory
	 *            the inventory
	 */
	public Player(Location location, Inventory inventory, int health) {
		this.location = location;
		this.inventory = inventory;
		this.health = health;
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
	 * @param location
	 *            the location
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

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Increases the health of the player by the specified number of points.
	 * 
	 * @param healthToAdd
	 *            the number of health points to be added
	 */
	public void increaseHealth(int healthToAdd) {

		if (health + healthToAdd > 100) {
			health = 100; // maximum health is 100
		} else {
			health += healthToAdd;
		}

	}

	/**
	 * Decreases the health of the monster by the specified number of points.
	 * 
	 * @param healthToDecrease
	 *            the number of health points to be deducted
	 */
	public void decreaseHealth(int healthToDecrease) {

		if (health - healthToDecrease < 0) {
			health = 0; // minimum health is 0
			// gameLost(); // player loses if out of health
		} else {
			health -= healthToDecrease;
		}

	}

}
