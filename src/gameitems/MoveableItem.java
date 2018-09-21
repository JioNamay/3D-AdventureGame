/**
 * 
 */
package gameitems;

import gameworld.Location;
import gameworld.Player;

/**
 * The Class MoveableItem.
 *
 * @author Deanne Alabastro
 */
public abstract class MoveableItem extends Item{

	/**
	 * Instantiates a new moveable item.
	 *
	 * @param location the location
	 */
	public MoveableItem(Location location) {
		super(location);
	}
	
	/**
	 * Picks up the item and adds it to the inventory if it is not full.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean pickUp(Player player) {
		// add item to inventory if not full
		if(player.getInventory().add(this)) {
			setLocation(player.getLocation()); // set item's loc to player's
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Drops the item.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean drop(Player player) {
		// EDIT THIS WHEN MORE CODE HAS BEEN COMPLETED
		return true;
	}

}
