package gameworld.entities;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import gameworld.items.Item;
import gameworld.Location;

public class Monster extends AttackingEntity{

	private ArrayList<Item> itemDrops; // the list of possible items the monster can drop after being defeated

	/**
	 * Construct a new monster, which will battle the player if the player
	 * encounters the monster.
	 */
	public Monster(ArrayList<Item> itemDrops) {
		this.itemDrops = itemDrops;
	}

	// ********** GETTERS AND SETTERS ********** //

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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String[] getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocation(Location location) {
		// TODO Auto-generated method stub
		
	}

}
