package gameworld.entities;

import gameworld.Location.Direction;
import gameworld.Room;
import gameworld.entities.Item.Action;
import gameworld.entities.LockableStrategy;

/**
 * The Class Door.
 */
public class Door extends LockableStrategy {

	/** The first room. */
	Room firstRoom;
	
	/** The second room. */
	Room secondRoom;
	
	/** The first room direction. */
	private Direction firstRoomDirection;
	
	/** The second room direction. */
	private Direction secondRoomDirection;

	/**
	 * Instantiates a new door.
	 */
	public Door() {
		this.name = "Door"; 
	}

	/**
	 * Gets the first room.
	 *
	 * @return the first room
	 */
	public Room getFirstRoom() {
		return firstRoom;
	}

	/**
	 * Sets the first room.
	 *
	 * @param firstRoom the new first room
	 */
	public void setFirstRoom(Room firstRoom) {
		this.firstRoom = firstRoom;
	}

	/**
	 * Gets the second room.
	 *
	 * @return the second room
	 */
	public Room getSecondRoom() {
		return secondRoom;
	}

	/**
	 * Sets the second room.
	 *
	 * @param secondRoom the new second room
	 */
	public void setSecondRoom(Room secondRoom) {
		this.secondRoom = secondRoom;
		this.description = "door between " + firstRoom.getName() + " and " + secondRoom.getName();
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.CoinBank#getDescription()
	 */
	@Override
	public String getDescription() {
		if (isLocked)
			return "A locked " + description;
		if (!isLocked && isOpen)
			return "An open " + description;
		return "A closed " + description;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.LockableStrategy#performAction(gameworld.entities.Item.Action)
	 */
	@Override
	public String performAction(Action action) {
		switch (action) {
		case EXAMINE:
			return getDescription();
		default:
			return super.performAction(action);
		}
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#isSolid()
	 */
	@Override
	public boolean isSolid() {
		return false;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#isDoor()
	 */
	@Override
	public boolean isDoor() {
		return true;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.CoinBank#getDirection()
	 */
	@Override
	public Direction getDirection() {
		if(Player.getInstance().getCurrentRoom().equals(firstRoom)) return firstRoomDirection;
		return secondRoomDirection;
	}

	/**
	 * Gets the first room direction.
	 *
	 * @return the first room direction
	 */
	public Direction getFirstRoomDirection() {
		return firstRoomDirection;
	}
	
	/**
	 * Gets the second room direction.
	 *
	 * @return the second room direction
	 */
	public Direction getSecondRoomDirection() {
		return secondRoomDirection;
	}
	
	/**
	 * Sets the first room direction.
	 *
	 * @param dir the new first room direction
	 */
	public void setFirstRoomDirection(Direction dir) {
		 this.firstRoomDirection = dir;
	}
	
	/**
	 * Sets the second room direction.
	 *
	 * @param dir the new second room direction
	 */
	public void setSecondRoomDirection(Direction dir) {
		this.secondRoomDirection = dir;
	}
	
	/* (non-Javadoc)
	 * @see gameworld.entities.CoinBank#setDirection(gameworld.Location.Direction)
	 */
	@Override
	public void setDirection(Direction direction) {
		if(Player.getInstance().getCurrentRoom().equals(firstRoom)) setFirstRoomDirection(direction);
		else setSecondRoomDirection(direction);
	}
}
