package gameworld;

/**
 * @author Deanne Alabastro
 *
 */
public abstract class Item {

	private Location location;

	/**
	 *
	 */
	public Item(Location location) {
		this.location = location;
	}

	public String examine() {
		return toString();
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public abstract String toString();

	public abstract void action();			// String argument?

}