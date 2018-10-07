package gameworld.entities;

import controller.Controller;
import gameworld.Location;

// TODO: Auto-generated Javadoc
/**
 * The Class FighterEntity.
 */
public abstract class FighterEntity extends DamageableEntity {
	
	/** The min damage. */
	protected int minDamage;
	
	/** The max damage. */
	protected int maxDamage;

	/**
	 * Instantiates a new fighter entity.
	 *
	 * @param controller the controller
	 * @param location the location
	 */
	public FighterEntity(Controller controller, Location location) {
		super(controller, location);
	}
	
	/**
	 * Attack.
	 *
	 * @param opponent the opponent
	 */
	abstract protected void attack(DamageableEntity opponent);
	
}
