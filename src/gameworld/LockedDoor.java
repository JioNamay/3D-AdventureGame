package gameworld;

/**
 * A LockedDoor is a Door that can be locked/unlocked only if the player
 * uses a key.
 *
 * @author yangcarr
 */
public class LockedDoor extends Door {
	private boolean isLocked;
	private int lockCode;

	/**
	 * A LockedDoor is assigned a lock code, and is locked at the start.
	 * @param location
	 */
	public LockedDoor(Location location) {
		super(location);
		isLocked = true;
		lockCode = 0;		// change this... get the generated lcok
	}

	/**
	 * @return whether the door is locked or not.
	 */
	public boolean isLocked() {
		return isLocked;
	}

	/**
	 * @return the lock code of the door
	 */
	public int getLockCode() {
		return lockCode;
	}

	/**
	 * LockedDoor overloads the Door's action() method.
	 * @param i the item to be used on the LockedDoor (should be a Key)
	 */
	public void action(Player p) {
		if (p == null)
			throw new IllegalArgumentException("Argument cannot be null.");

		// get player's inventory and check that the player indeed has the item
		// if item is instanceof kay, cast to key
		// do action: isLocked = !isLocked
		// if is not locked, close the door and lock it
		// if is locked, unlock it and open the door
	}
}
