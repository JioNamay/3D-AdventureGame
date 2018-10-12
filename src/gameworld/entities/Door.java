package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.Location;
import gameworld.Room;
import gameworld.entities.Item.Action;
import gameworld.entities.LockableStrategy;


public class Door extends LockableStrategy {

	Room goesTo;


	public Door(Location location, Room connectingRoom) {
		super(location);
		this.goesTo = connectingRoom;
		this.description = "A door that leads to the " + connectingRoom.getName();
		this.name = "Door";
	}

	/**
	 * @return the goesTo
	 */
	public Room getGoesTo() {
		return goesTo;
	}

	/**
	 * @param goesTo the goesTo to set
	 */
	public void setGoesTo(Room goesTo) {
		this.goesTo = goesTo;
	}
	
	@Override
	public String getDescription() {
		if(isLocked) return "A locked " + name;
		if(!isLocked && isOpen) return "An open " + name;
		return description;
	}
	

	@Override
	protected String performAction(Action action, Player player) {
		switch(action) {
		case EXAMINE:
			return getDescription();
		case UNLOCK:
			//return unlock();
		case OPEN:
			//return drop(player, this);
		case CLOSE:
			//return use();
		default:
			throw new IllegalArgumentException("Unknown action: " + action);
		}
	}
}
