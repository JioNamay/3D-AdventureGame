/**
 * 
 */
package gameworld;

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
		// check if player's inventory is full
		if(player.getInventory().isFull()) {
			return false;
		}else {
			setLocation(player.getLocation()); // set item's loc to player's
			player.getInventory().add(this); // add item to inventory
			return true;
		}
	}
	
	/**
	 * Drop.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean drop(Player player) {
		// EDIT THIS WHEN MORE CODE HAS BEEN COMPLETED
		return true;
	}

}
