package gameworld.entities;

import controller.Controller;
import gameworld.Location;

public abstract class FighterEntity extends Entity {
	
	protected int minDamage;
	protected int maxDamage;
	

	/**
	 * @param controller
	 * @param location
	 */
	public FighterEntity(Controller controller, Location location) {
		super(controller, location);
	}
	
	abstract protected void attack(Entity opponent);
	
}
