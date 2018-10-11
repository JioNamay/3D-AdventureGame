package gameworld.items;

import java.util.Arrays;
import java.util.List;

import gameworld.Location;
import gameworld.Room;
import gameworld.entities.Player;

public class Door extends LockableItem {
	
	Room goesTo;

	public Door(Location location) {
		super(location);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String examine() {
		return description;
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

}
