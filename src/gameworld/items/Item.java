package gameworld.items;

import gameworld.GameObjectInterface;
import gameworld.Location;
import gameworld.entities.Player;

public abstract class Item implements GameObjectInterface{
	public enum Action{
		EXAMINE,
		PICKUP, 
		DROP,
		USE,
		PLACE,
		TAKE,
		OPEN,
		CLOSE
	}
	protected String description;
	protected String name;
	protected Location location;
	protected boolean isInPlayerInventory = false;
	
	public Item(Location location) {
		this.location = location;
	}
	
	/**
	 * @return true if in player's inventory
	 */
	protected boolean isInPlayerInventory() {
		return isInPlayerInventory;
	}

	/**
	 * @param whether the item is in the player's inventory
	 */
	protected void setInPlayerInventory(boolean isInPlayerInventory) {
		this.isInPlayerInventory = isInPlayerInventory;
	}

	// ********** ABSTRACT METHODS ********** //
	abstract protected String examine();
	abstract protected String performAction(Action action,Player player);
	abstract protected String[] getActions();
	
}
