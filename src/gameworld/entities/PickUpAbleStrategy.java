package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.Location;

/**
 * Contains the pick up behaviors for items the player can pick up.
 * 
 * @author alabasdean 300346210
 * @author yangcarr 300368805
 */
public abstract class PickUpAbleStrategy extends CoinBank {
	
	/**
	 * Instantiates a new pick up able strategy.
	 *
	 * @param location the location
	 */
	public PickUpAbleStrategy(Location location) {
		super(location);
	}

	/** The actions. */
	// all pickup-able items are, by default, not in the player's inventory at the start
	protected List<String> actions = Arrays.asList("Examine", "Pick-Up");

	// **************** ABSTRACT METHODS ****************
	
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
		player.getCurrentRoom().removeGameObject(this.getLocation());
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
		if(player.getInventory().contains(this)) return "How are you going to drop an item you aren't even holding?";
		
		// drop item by removing from player inventory and adding it to current room objects.
		player.getInventory().remove(this);
		
		Location dropLoc = new Location(player.getLocation().getRow(), player.getLocation().getCol());
		Item droppedItem = new Item(dropLoc, this); 
		player.getCurrentRoom().addGameObject(droppedItem);

		return "Player dropped " + this.name;

	}

}
