package gameitems;

import gameworld.Location;
import gameworld.entities.Player;

/**
 * The Class Container.
 * 
 * @author Deanne Alabastro 
 */
public abstract class Container extends MoveableItem {
	
	/** Flag for if the container is open (true) or closed (false). */
	protected boolean isOpen = false;
	
	/** Flag for if container contains item. */
	protected boolean containsItem;
	
	/** The item the container holds. */
	protected MoveableItem item;
	
	/**
	 * Instantiates a new container.
	 *
	 * @param location the location
	 * @param item the item
	 */
	public Container(Location location, MoveableItem item) {
		super(location);
		if(item != null) {
			containsItem = true;
			this.item = item;
		}else {
			containsItem = false;
		}
	}
	
	/**
	 * Gets the item.
	 *
	 * @param player the player
	 * @return the item
	 */
	public boolean getItem(Player player) {
		// check if the container is open & player inventory not full 
		if(isOpen && containsItem && player.getInventory().add(this)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Place item.
	 *
	 * @param item the item
	 * @return true, if successful
	 */
	public boolean placeItem(MoveableItem item) {
		// check if container is open and is empty
		if(isOpen && !containsItem) {
			this.item = item;
			containsItem = true;
			item.setLocation(this.location);
			return true;
		}else {
			return false;
		}
	}
	
}
