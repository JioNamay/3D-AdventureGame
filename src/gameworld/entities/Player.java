package gameworld.entities;

import gameworld.Location;
import gameworld.Room;

public class Player implements Damageable {

	/** The inventory. */
	private Inventory inventory;

	/** Player's health */
	private int health;

	/** The location. */
	private Location location;

	private Room currentRoom;

	private int coins = 0;

	// ********** SINGLETON PATTERN ********** //
	/** The only instance of player. */
	private static Player instance = new Player();

	/**
	 * Instantiates a new player - private to ensure that only one player is ever made.
	 */
	private Player() {
		this.health = 100;
	}

	/**
	 * Gets the single instance of Player.
	 *
	 * @return single instance of Player
	 */
	public static Player getInstance() {
		return instance;
	}

	/**
	 * @return the coins
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * @param coins the coins to set
	 */
	public void addCoins(int coins) {
		this.coins += coins;
	}

	/**
	 * Sets the inventory.
	 *
	 * @param inventory the new inventory
	 */
	public void setInventory(Inventory inventory) {
		if(this.inventory == null) this.inventory = inventory;
		// only used for test to allow the inventory to be reset: 
		this.inventory = inventory;
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
	 * Equip weapon.
	 *
	 * @param weapon the weapon
	 */
	/*public void equipWeapon(WeaponEntity weapon) {
		this.weapon = weapon;
		setAttackDamage();
	}*/

	/**
	 * Sets the attack damage.
	 */
	/*private void setAttackDamage() {
		if(this.weapon == null) {
			// player uses fist
			this.maxDamage = 5;
		}else {
			//this.maxDamage = weapon.getAttackDamage();
		}
	}*/

	// ********** INHERITED "ABSTRACT" METHODS ********** //

	/* (non-Javadoc)
	 * @see gameworld.entities.Entity#die()
	 */
	@Override
	public void die() {
		// Game end

	}

	/**
	 * Recover.
	 *
	 * @param amount the amount
	 */
	public void recover(int amount) {
		health = ((this.health + amount) > 100) ? 100 : health + amount;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void getDamaged(int amount) {
		if((this.health - amount) < 0) {
			this.health = 0;
			this.die();
		}else {
			health -= amount;
		}

	}

	/**
	 * @return the currentRoom
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * @param currentRoom the currentRoom to set
	 */
	public void setCurrentRoom(Room currentRoom) {
		if(this.currentRoom != null) this.currentRoom.setHasPlayer(false); // tell the old room that it no longer has player
		this.currentRoom = currentRoom;
		currentRoom.setHasPlayer(true);
		
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
}
