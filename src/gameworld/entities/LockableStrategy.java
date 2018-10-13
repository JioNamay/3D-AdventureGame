package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Item.Action;

public abstract class LockableStrategy implements Strategy {
	protected List<String> actions = Arrays.asList(Action.EXAMINE.toString(), Action.UNLOCK.toString()); 

	protected boolean isOpen, isLocked;
	
	protected String description;
	protected String name;
 
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
		switch(action) {
		case UNLOCK:
			return unlock();
		case OPEN:
			return open();
		case CLOSE:
			return close();
		default:
			throw new IllegalArgumentException("Unknown action: " + action.toString() + " for object: "+ this.name);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	private String unlock() {
		if (Player.getInstance().getInventory().hasKey()) {
			PickUpAbleStrategy key = Player.getInstance().getInventory().getAKey();
			key.performAction(Action.USE);
			isLocked = false;
			return "Player unlocked " + this.name;
		}
		return "You cannot unlock something without a key";
	}
	
	private String open() {
		if(isLocked) return "You cannot open a " + this.name + " while it is locked!";
		isOpen = true;
		return "You opened a " + this.name;
	}
	
	private String close() {
		if(isLocked || !isOpen) return "You cannot close a door that's locked or already closed!";
		isOpen = false;
		return "You closed a " + this.name;
	}

}
