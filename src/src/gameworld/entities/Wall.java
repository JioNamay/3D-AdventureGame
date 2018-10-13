package gameworld.entities;

import gameworld.Location;

/**
 * The wall object represents a wall and marks a location as solid so that a player cannot walk past it.
 */
public class Wall{
	
	/** The location. */
	private Location location;

	/**
	 * Instantiates a new wall.
	 *
	 * @param location the location
	 */
	public Wall(Location location) {
		this.setLocation(location);
		location.setSolid(true);
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
}
