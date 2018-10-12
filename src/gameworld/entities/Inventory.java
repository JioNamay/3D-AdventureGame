package gameworld.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Class Inventory.
 */
public class Inventory implements Iterable<PickUpAbleStrategy> {
	/** The inventory. */
	private final List<PickUpAbleStrategy> inventory;
	private int keyCount;

	/**
	 * Instantiates a new inventory.
	 */
	public Inventory() {
		this.inventory = new ArrayList<PickUpAbleStrategy>();
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
	 * @param item
	 *            the item
	 * @return true, if successful
	 */
	public boolean add(PickUpAbleStrategy item) {
		if (this.isFull()) {
			return false;
		} else {
			inventory.add(item);
			return true;
		}
	}

	/**
	 * Removes the item from the inventory.
	 *
	 * @param item
	 *            the item
	 */
	public void remove(PickUpAbleStrategy item) {
		inventory.remove(item);
	}

	/**
	 * Checks if the item is in the player's inventory
	 *
	 * @param item
	 *            the item
	 * @return true, if successful
	 */
	public boolean contains(PickUpAbleStrategy item) {
		return inventory.contains(item);
	}

	/**
	 * @return the hasKey
	 */
	public boolean hasKey() {
		return keyCount > 0;
	}

	public void incrementKeys() {
		keyCount++;
	}

	/**
	 * @param hasKey
	 *            the hasKey to set
	 */
	public void setKeyCount(int keyCount) {
		this.keyCount = keyCount;
	}

	@Override
	public Iterator<PickUpAbleStrategy> iterator() {
		Iterator<PickUpAbleStrategy> it = new Iterator<PickUpAbleStrategy>() {

			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < inventory.size();
			}

			@Override
			public PickUpAbleStrategy next() {
				return inventory.get(currentIndex++);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return it;
	}

}
