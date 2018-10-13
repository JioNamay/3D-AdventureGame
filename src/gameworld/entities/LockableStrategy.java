package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.Location;
import gameworld.entities.Item.Action;

public abstract class LockableStrategy implements Strategy {
	protected List<String> actions = Arrays.asList(Action.EXAMINE.toString(), Action.UNLOCK.toString()); 

	protected boolean isOpen, isLocked;

	protected Location location;
	protected String description;
	protected String name;
	
	public LockableStrategy(Location location) {
		this.location = location;
	}
 
	@Override
	public List<String> getActions() {
		if(isLocked) {
			if (Player.getInstance().getInventory().hasKey())
				return actions;
			else return Arrays.asList(Action.EXAMINE.toString());
		}else {
			if(isOpen) return Arrays.asList(Action.EXAMINE.toString(), Action.CLOSE.toString());
			else return Arrays.asList(Action.EXAMINE.toString(), Action.CLOSE.toString());
		}
	}

	@Override
	public String performAction(Action action) {
		if (action.equals("Unlock")){
			// check if player has key in inventory
			// then actions = examine, open
		}
		else if (action.equals("Open")) {
			// actions = examine, close
		}
		return null;
	}

	@Override
	public String getName() {
		return name;
	}


}
