package gameworld.entities;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import gameitems.Item;
import gameworld.Location;

public class Monster {

	private int health;
	private int minDamage;
	private int maxDamage;
	private Location location;
	private ArrayList<Item> itemDrops; // the list of possible items the monster can drop after being defeated

	/**
	 * Construct a new monster, which will battle the player if the player
	 * encounters the monster.
	 * 
	 * @param health
	 *            the maximum (and starting) health of the monster
	 * @param minDamage
	 *            the minimum amount of damage points a monster can inflict on the
	 *            player
	 * @param maxDamage
	 *            the maximum amount of damage points a monster can inflict on the
	 *            player
	 * @param location
	 *            the location of the monster
	 * @param itemDrops
	 *            the list of possible items the monster can drop after being
	 *            defeated
	 */
	public Monster(int health, int minDamage, int maxDamage, Location location, ArrayList<Item> itemDrops) {

		this.health = health;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.location = location;
		this.itemDrops = itemDrops;

	}

	/**
	 * Make the monster attack the player, i.e. decreases the health of the player.
	 * 
	 * @param opponent
	 *            the player battling the monster
	 */
	public void attack(Player opponent) {

		// generates a random number of damage points within the range [minDamage,
		// maxDamage]
		int damage = ThreadLocalRandom.current().nextInt(minDamage, maxDamage + 1);

		opponent.decreaseHealth(damage);

	}

	// ********** GETTERS AND SETTERS ********** //

	/**
	 * Get the health of the player.
	 * 
	 * @return health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Decrease the health of the player by the specified number of points.
	 * 
	 * @param healthToDecrease
	 *            the number of health points to be deducted
	 */
	public void decreaseHealth(int healthToDecrease) {

		if (health - healthToDecrease < 0) {
			health = 0; // minimum health is 0
			// gameLost();
		} else {
			health -= healthToDecrease;
		}

	}

	/**
	 * Get the player location.
	 * 
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Set the player location.
	 * 
	 * @param location
	 *            the location at which the player is to be placed
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Get the list of possible items the monster can drop after being defeated.
	 * 
	 * @return the itemDrops
	 */
	public ArrayList<Item> getItemDrops() {
		return itemDrops;
	}

	/**
	 * Set the list of possible items the monster can drop after being defeated.
	 * 
	 * @param itemDrops
	 *            the list of possible items the monster can drop after being
	 *            defeated
	 */
	public void setItemDrops(ArrayList<Item> itemDrops) {
		this.itemDrops = itemDrops;
	}

}
