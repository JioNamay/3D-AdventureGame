package gameworld.items;

import java.util.List;

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
		CLOSE,
		UNLOCK,
		LOCK
	}
	
	public enum Direction{
		NORTH,
		SOUTH,
		EAST,
		WEST
	}
	protected String description;
	protected String name;
	protected Location location;
	protected boolean isInPlayerInventory = false;
	
	public Item(Location location) {
		this.location = location;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
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
	abstract protected List<String> getActions();
	
}
