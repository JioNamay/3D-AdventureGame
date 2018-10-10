package gameworld.items;

import gameworld.Location;
import gameworld.entities.Player;

public abstract class PickeupableItem extends DamageableItem{

	public PickeupableItem(Location location) {
		super(location);
	}

	protected String pickUp(Player player, Item item) {
		if(player.getInventory().isFull()) return "Cannot pick up " + this.name + ". Inventory is Full";
		item.setLocation(player.getLocation());
		player.getInventory().add(item);
		item.setInPlayerInventory(true);
		return "Player picked up " + this.name;
	}
	
	protected String drop(Player player, Item item) {
		if(player.getInventory().contains(item)) return "How are you going to drop an item you aren't even holding?";
		player.getInventory().remove(item);
		item.setLocation(new Location(player.getLocation().getRow(), player.getLocation().getCol()));
		item.setInPlayerInventory(false);
		return "Player dropped " + this.name;
	}

}
