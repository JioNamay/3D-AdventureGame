package gameworld.entities;

/**
 * Entity is the context class where the various items are used.
 * An instance of entity is made in the gameworld, where certain items
 * are placed in certain locations.
 *
 * @author yangcarr 300368805
 * @author alabasdean 300346210
 */
public class Item {
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
	}

	protected Strategy item;

	/**
	 * An instance of an Entity is made on the specified location.
	 * It takes in an instance of Strategy, which will allow the item
	 * to perform its respective behaviours.
	 */
	public Item(Strategy item) {
		this.item = item;
	}

	/**
	 * Returns the string description of the item, based on whatever
	 * instance of Strategy it is.
	 */
	public String getDescription() {
		return item.getDescription();
	}
	
	public String getName() {
		return item.getName();
	}
	
	public boolean isSolid() {
		return item.isSolid();
	}
	
	public boolean isDoor() {
		return item.isDoor();
	}

	public Strategy getItem() {
		return item;
	}
}
