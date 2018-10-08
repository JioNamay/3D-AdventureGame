/**
 * 
 */
package gameworld.items;

import gameworld.Location;
import gameworld.entities.Player;
import gameworld.items.Item.Action;
import gameworld.items.Item.ItemActionStrategy;

/**
 * @author deanrae
 *
 */
public class PickupableStrategy implements ItemActionStrategy {

	@Override
	public String performAction(Action action, Item item, Player player) {
		if (action == Action.PICKUP) {
			return pickUp(player, item);		
		} else if(action == Action.DROP){
			return drop(player, item);			
		} else {
			throw new IllegalArgumentException("Unknown action: " + action);
		}
	}
	
	private String pickUp(Player player, Item item) {
		if(player.getInventory().isFull()) return "Cannot pick up item. Inventory is Full";
		item.setLocation(player.getLocation());
		player.getInventory().add(item);
		item.setInPlayerInventory(true);
		return "Player picked up " + this.toString();
	}
	
	private String drop(Player player, Item item) {
		if(player.getInventory().contains(item)) return "How are you going to drop an item you aren't even holding?";
		player.getInventory().remove(item);
		item.setLocation(new Location(player.getLocation().getRow(), player.getLocation().getCol()));
		item.setInPlayerInventory(false);
		return "Player dropped " + this.toString();
	}
}
