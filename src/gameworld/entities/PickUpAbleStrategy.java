package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Item.Action;

/**
 * Contains the pick up behaviors for items the player can pick up.
 * 
 * @author alabasdean 300346210
 * @author yangcarr 300368805
 */
public abstract class PickUpAbleStrategy extends CoinBank {

	/** The actions. */
	// all pickup-able items are, by default, not in the player's inventory at the start
	protected List<String> actions = Arrays.asList(Action.EXAMINE.toString(), Action.PICKUP.toString());
	 
	@Override
	public String performAction(Action action) {
		switch(action) {
		case PICKUP:
			return pickUp();
		case DROP:
			return drop();
		default:
			throw new IllegalArgumentException("Unknown action: " + action.toString() + " for object: "+ this.name);		}
	}
	
	/**
	 * Pick up item from current room.
	 *
	 * @return string result of pickup 
	 */
	protected String pickUp() {
		Player player = Player.getInstance();
		// check if pickup is valid.
		if(player.getInventory().isFull()) return "Cannot pickup " + this.getName() + ". Inventory is full.";
		
		// add item to player inventory and remove from room.
		player.getCurrentRoom().removeGameItem(this);
		player.getInventory().add(this);
		
		return "You picked up " + this.getName();
	}
	
	/**
	 * Drop the item from player inventory.
	 *
	 * @return string result of drop
	 */
	protected String drop() {
		Player player = Player.getInstance();
		
		// check if drop valid.
		if(!player.getInventory().contains(this)) return "How are you going to drop an item you aren't even holding?";
		
		// delegate drop to room - drops if location is available, else does nothing (but returns string with result)
		return player.getCurrentRoom().playerDropGameItem(this);

	}

}
