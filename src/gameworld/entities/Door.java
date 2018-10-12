package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.Location;
import gameworld.Room;
import gameworld.entities.LockableStrategy;
import gameworld.entities.Player;

public class Door extends LockableStrategy {

	Room goesTo;


//	public Door(Location location, Room connectingRoom) {
//		super(location);
//		this.goesTo = connectingRoom;
//		this.description = "A door that leads to the " + connectingRoom.getName();
//	}

	
	/*public Door(Location location) {
		super(location);
	}*/

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
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	// ==============================================================

	/*
	 * 	@Override
	protected String performAction(Action action, Player player) {
		switch(action) {
		case EXAMINE:
			return examine();
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

	@Override
	protected List<String> getActions() {
		if(isLocked) {
			if (Player.getInstance().getInventory().hasKey())
				return Arrays.asList(Action.EXAMINE.toString(), Action.UNLOCK.toString());
			else return Arrays.asList(Action.EXAMINE.toString());
		}else {
			return Arrays.asList(Action.EXAMINE.toString(), Action.OPEN.toString(), Action.CLOSE.toString());
		}
	}
	 */

}
