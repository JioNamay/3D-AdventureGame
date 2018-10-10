package gameworld.entities;
import gameworld.GameObjectInterface;
import gameworld.Location;

/**
 * The Entity class includes any attackable thing that may optionally drop items
 * upon death
 */
public abstract class Entity implements GameObjectInterface {
	
	/** The max damage. */
	protected int maxDamage;
	
	/** The health. */
	protected int health;

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	
	/**
	 * @return the maxDamage
	 */
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	 * @param maxDamage the maxDamage to set
	 */
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	/**
	 * Decrease the object's health.
	 *
	 * @param amount the amount
	 * @return the damaged
	 */
	protected void getDamaged(int amount) {
		if((this.health - amount) < 0) {
			this.health = 0;
			this.die();
		}else {
			health -= amount;
		}
	}
	
	// ********** ABSTRACT METHODS ********** //
	
	/**
	 * Die.
	 */
	protected abstract void die();
	
	/**
	 * Gets the entity's stats.
	 *
	 * @return the stats
	 */
	abstract protected String[] getStats();

}
