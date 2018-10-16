package gameworld.entities;

import javax.xml.bind.annotation.XmlTransient;

/**
 * The Interface for all entities that are Damageable.
 * @author yangcarr 300368805
 */
@XmlTransient 
public interface Damageable {
	
	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	public int getHealth();		// get current lifetime of the item
	
	/**
	 * Die.
	 */
	public void die();			// what the item does when its lifetime is over
	
	/**
	 * Gets the damaged.
	 *
	 * @param amount the amount
	 * @return the damaged
	 */
	public void getDamaged(int amount);
}
