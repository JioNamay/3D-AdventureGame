package gameworld.entities;

import gameworld.Room;
import gameworld.entities.Item.Action;
import gameworld.entities.LockableStrategy;


public class Door extends LockableStrategy {

	Room goesTo;
	Room from;

	public Door(Room connectingRoom1, Room connectingRoom2) {
		this.from = connectingRoom1;
		this.goesTo = connectingRoom2;
		this.description = "door between " + connectingRoom1.getName() + " and " + connectingRoom2.getName();
		this.name = "Door";
	}

	/**
	 * @return the from
	 */
	public Room getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(Room from) {
		this.from = from;
	}

	/**
	 * @return the goesTo
	 */
	public Room getGoesTo() {
		return goesTo;
	}

	/**
	 * @param goesTo
	 *            the goesTo to set
	 */
	public void setGoesTo(Room goesTo) {
		this.goesTo = goesTo;
	}

	@Override
	public String getDescription() {
		if (isLocked)
			return "A locked " + description;
		if (!isLocked && isOpen)
			return "An open " + description;
		return "A closed " + description;
	}

	@Override
	public String performAction(Action action) {
		switch (action) {
		case EXAMINE:
			return getDescription();
		default:
			return super.performAction(action);
		}
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public boolean isDoor() {
		return true;
	}
}
