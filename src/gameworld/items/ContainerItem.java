package gameworld.items;

import gameworld.Location;
import gameworld.entities.Player;

public abstract class ContainerItem extends LockableItem{

	protected PickupableItem item;
	protected boolean containsItem;
	
	public ContainerItem(Location location, PickupableItem item) {
		super(location);
		this.item = item;
		if(item != null) containsItem = true;
	}
	
	/**
	 * Gets the item.
	 *
	 * @param player the player
	 * @return the item
	 */
	public String getItem(Player player) {
		// check if the container is open & player inventory not full 
		if(isOpen && containsItem && !player.getInventory().isFull()) {
			addToInventory(player);
			removeItem();
			return "You got " + item.getName() ;
		}else{
			return "You do not have enough space in your inventory to take this item";
		}
	}
	
	public String placeItem(PickupableItem item) {
		addItem(item);
		return "You have placed " + item.name + " into " + this.name;
	}
	
	private void addItem(PickupableItem item) {
		if(!isOpen || isLocked) return;
		this.item = item;
		item.setInPlayerInventory(false);
		containsItem = true;
	}
	
	private void addToInventory(Player player) {
		player.getInventory().add(item);
		item.setInPlayerInventory(true);
	}
	
	private void removeItem() {
		item = null;
		containsItem = false;
	}
	
}