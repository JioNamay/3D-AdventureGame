package gameitems;

import gameworld.Location;

/**
 * A Door is an Item that cannot be picked up and moved around.
 * @author yangcarr
 *
 */
public class Door extends Item {
	private boolean isClosed;

	public Door(Location location) {
		super(location);
		isClosed = true;			// every door is closed at start
	}

	@Override
	public String toString() {
		return null;		// string description of door
	}

	/**
	 * The only actions on a door is to open and close it.
	 * Player can only enter through a door if it is open.
	 */
	@Override
	public void action() {
		isClosed = !isClosed;
	}

}
