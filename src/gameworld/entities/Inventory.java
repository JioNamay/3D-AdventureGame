package gameworld.entities;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

/**
 * The Class Inventory.
 */
public class Inventory {
	
	/** The inventory. */
	private final List<Pickupable> inventory;
	
	/**
	 * Instantiates a new inventory.
	 */
	public Inventory(Controller controller) {
		this.inventory = new ArrayList<Pickupable>();
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
	public boolean add(Pickupable item) {
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
	public void remove(Pickupable item) {
		inventory.remove(item);
	}
}
