package gameworld.entities;

import controller.Controller;
import gameworld.Location;

/**
 * The Entity class includes any attackable thing that may optionally drop items upon death
 */
public abstract class Entity {
	
	/** The controller. */
	protected Controller controller;
	
	/** The location. */
	private Location location;
	
	/** The health. */
	protected int health;
	
	/** The active. */
	protected boolean active = true;
	
	/**
	 * Instantiates a new entity.
	 *
	 * @param controller the controller
	 * @param location the location
	 */
	public Entity(Controller controller, Location location) {
		this.controller = controller;
		this.location = location;
	}
	
	/**
	 * Decrease health.
	 *
	 * @param amount the amount
	 */
	public void decreaseHealth(int amount) {
		if (health - amount <= 0) {
			health = 0; // minimum health is 0
			active = false;
			die();
		} else {
			health -= amount;
		}
	}

	/**
	 * When the health is 0, entity dies. This is where item drop is done. 
	 */
	abstract protected void die();

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

	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets the health.
	 *
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Checks if is active.
	 *
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets if active.
	 *
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
}
