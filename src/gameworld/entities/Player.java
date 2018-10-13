package gameworld.entities;

import gameworld.Location;
import gameworld.Room;

public class Player extends AttackingEntity {

	/** The inventory. */
	private Inventory inventory;

	/** The location. */
	private Location location;

	private Room currentRoom;
	
	private PickUpAbleStrategy selectedItem;
	
	private EquipableStrategy equipedWeapon;

	private int coins = 0;

	// ********** SINGLETON PATTERN ********** //
	/** The only instance of player. */
	private static Player instance = new Player();

	/**
	 * Instantiates a new player - private to ensure that only one player is ever made.
	 */
	private Player() {
		this.health = 100;
		this.maxDamage = 5;
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
	} 
	
	/**
	 * Reset inventory for test. Only used for testing.
	 */
	public void resetInventoryForTest() {
		this.inventory = new Inventory();
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
	 * Sets the attack damage.
	 */
	private void setAttackDamage() {
		if(equipedWeapon == null) {
			// player uses fist
			this.maxDamage = 5;
		}else {
			this.maxDamage = equipedWeapon.getAttackDamage();
		}
	}

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

	/**
	 * @return the selectedItem
	 */
	public PickUpAbleStrategy getSelectedItem() {
		return selectedItem;
	}

	/**
	 * @param selectedItem the selectedItem to set
	 */
	public void setSelectedItem(PickUpAbleStrategy selectedItem) {
		this.selectedItem = selectedItem;
	}

	/**
	 * @return the equipedWeapon
	 */
	public EquipableStrategy getEquipedWeapon() {
		return equipedWeapon;
	}

	/**
	 * @param equipedWeapon the equipedWeapon to set
	 */
	public void setEquipedWeapon(EquipableStrategy equipedWeapon) {
		this.equipedWeapon = equipedWeapon;
		setAttackDamage(); // update player's attack damage
	}
}
