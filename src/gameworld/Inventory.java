package gameworld;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Inventory.
 * @author Deanne Alabastro
 */
public class Inventory {
	
	/** The inventory. */
	private final List<MoveableItem> inventory;
	
	/**
	 * Instantiates a new inventory.
	 */
	public Inventory() {
		this.inventory = new ArrayList<MoveableItem>();
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
	public boolean add(MoveableItem item) {
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
	public void remove(MoveableItem item) {
		inventory.remove(item);
	}
}
