package gameworld.entities;

/**
 * Entity is the context class where the various items are used.
 * An instance of entity is made in the gameworld, where certain items
 * are placed in certain locations.
 * 
 * Uses the strategy pattern 
 *
 * @author yangcarr 300368805
 * @author alabasdean 300346210
 */
public class Item {
	
	/**
	 * The Enum Action - used for performing actions on items.
	 */
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
		EQUIP, 
		UNEQUIP, 
		ATTACK, 
		READ, 
		THROWCOINS
	}

	/** The item. */
	protected Strategy item;

	/**
	 * An instance of an Entity is made on the specified location.
	 * It takes in an instance of Strategy, which will allow the item
	 * to perform its respective behaviours.
	 *
	 * @param item the item
	 */
	public Item(Strategy item) {
		this.item = item;
	}

	/**
	 * Returns the string description of the item, based on whatever
	 * instance of Strategy it is.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return item.getDescription();
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return item.getName();
	}
	
	/**
	 * Checks if is solid.
	 *
	 * @return true, if is solid
	 */
	public boolean isSolid() {
		return item.isSolid();
	}
	
	/**
	 * Checks if is door.
	 *
	 * @return true, if is door
	 */
	public boolean isDoor() {
		return item.isDoor();
	}

	/**
	 * Gets the item.
	 *
	 * @return the item
	 */
	public Strategy getItem() {
		return item;
	}
}
