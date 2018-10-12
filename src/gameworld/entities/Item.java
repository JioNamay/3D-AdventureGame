package gameworld.entities;

import gameworld.Location;

/**
 * Entity is the context class where the various items are used.
 * An instance of entity is made in the gameworld, where certain items
 * are placed in certain locations.
 *
 * @author yangcarr
 */
public abstract class Entity {
	protected Location location;
	protected Strategy item;

	/**
	 * An instance of an Entity is made on the specified location.
	 * It takes in an instance of Strategy, which will allow the item
	 * to perform its respective behaviours.
	 */
	public Entity(Location location, Strategy item) {
		this.location = location;
		this.item = item;
	}

	/**
	 * Returns the string description of the item, based on whatever
	 * instance of Strategy it is.
	 */
	public String getDescription() {
		return item.getDescription();
	}

	public Location getLocation() {
		return location;
	}

	public Strategy getItem() {
		return item;
	}
}
