package gameworld.items;

import gameworld.Location;
import gameworld.entities.Player;

public interface Container {

	public String getItem(Player player);
	
	public String placeItem(PickupableItem item);
	
	private void addItem(PickupableItem item);
	
	private void addToInventory(Player player);
	
	private void removeItem();
}
