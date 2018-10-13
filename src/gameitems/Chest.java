package gameitems;

import gameworld.Location;
import gameworld.entities.Player;

/**
 * Chest is an immovable item that contains other items.
 *
 * @author yangcarr
 */
public class Chest extends Item {
	private boolean isLocked, isClosed;
	private int lockCode;
	//List<Item> itemsInChest;
	Key key;

	public Chest(Location location) {			// public Chest(Location location, List<Item> itemsInChest){
		super(location);
		isLocked = true;
		isClosed = true;
		lockCode = 0;			// generate lock code for chest
		// itemsInChest = new ArrayList<Item>();
	}

	/**
	 * @return String description of the chest and what it contains.
	 * eg. "locked chest containing coins"
	 */
	@Override
	public String toString() {
		return null;		// string description here
	}

	/**
	 * Method sets the Key item for this Chest.
	 * @param k Key item to check
	 */
	public void applyKey(Player p, Key k) {
		if (k == null)
			throw new IllegalArgumentException("Argument cannot be null.");

		if (!(k instanceof Key))
			throw new IllegalArgumentException("Item passed in argument must be a Key!");

		// if k is in not in player's inventory, throw exception ("player doesn't have the correct key")

		key = k;		// sets the key for the chest
	}

	/**
	 * This method is used successfully only if the key has already been applied.
	 */
	@Override
	public void action() {
		if (key == null) {			// no key applied for the chest
			// print message ?
			return;
		}

		// if key code != lockCode, return (let user know??)

		// if isclosed, but not locked, then lock it
		// if is closed but is locked, then unlock and open
		// if open, then close and lock

	}

}
