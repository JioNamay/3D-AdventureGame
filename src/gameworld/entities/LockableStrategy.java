package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Item.Action;

public abstract class LockableStrategy implements Strategy {
	protected List<String> actions = Arrays.asList("Examine", "Unlock", "Open"); // REDO

	protected boolean isOpen, isLocked;

	// **************** ABSTRACT METHODS ****************

	@Override
	public List<String> getActions() {
		return actions;
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

}
