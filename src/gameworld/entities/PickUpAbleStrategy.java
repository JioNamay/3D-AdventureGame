package gameworld.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class PickUpAbleStrategy implements Strategy {
	// all pickup-able items are, by default, not in the player's inventory at the start
	protected List<String> actions = Arrays.asList("Examine", "Pick-Up");

	// **************** ABSTRACT METHODS ****************
	public abstract String description();

	/**
	 * A pickup-able item can be:	examined, picked up, dropped
	 * depending on player's uses of it throughout the game.
	 * For example, if the player already picked up the item, then the
	 * only possible actions are: examine, drop
	 */
	@Override
	public List<String> getActions() {
		return actions;
	}

	/**
	 * Depending on the user's choice, will perform the required action
	 * on the item
	 */
	@Override
	public void performAction(String action) {
		if (action.equals("PickUp")){
			// add item to player's inventory
			// set board's location where the item was, to null
		}
		else if (action.equals("Drop")) {
			// remove item from player's inventory
			// set it to mew instance of player's location (otherwise it will reference the
			// player and 'follow' him around
		}
	}

}
