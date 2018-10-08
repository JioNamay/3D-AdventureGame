package gameworld.entities;

import java.util.ArrayList;
import java.util.List;

import gameworld.items.Item;

/**
 * The Class Inventory.
 */
public class Inventory {
	
	/** The inventory. */
	private final List<Item> inventory;
	
	/**
	 * Instantiates a new inventory.
	 */
	public Inventory() {
		this.inventory = new ArrayList<Item>();
	}
	
	/**
	 * Checks if the inventory is full.
	 *
	 * @return true, if is full
	 */
	public boolean isFull() {
		return inventory.size() == 10;
	}
	
	/**
	 * Adds the item to the inventory if there is space.
	 *
	 * @param item the item
	 * @return true, if successful
	 */
	public boolean add(Item item) {
		if(this.isFull()) {
			return false;
		}else {
			inventory.add(item);
			return true;
		}
	}
	
	/**
	 * Removes the item from the inventory.
	 *
	 * @param item the item
	 */
	public void remove(Item item) {
		inventory.remove(item);
	}
	
	/**
	 * Checks if the item is in the player's inventory
	 *
	 * @param item the item
	 * @return true, if successful
	 */
	public boolean contains(Item item) {
		return inventory.contains(item);
	}
}
