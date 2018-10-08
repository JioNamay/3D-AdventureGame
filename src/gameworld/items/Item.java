package gameworld.items;

import gameworld.GameObjectInterface;
import gameworld.entities.Player;

public abstract class Item implements GameObjectInterface{
	public enum Action{
		EXAMINE,
		PICKUP, 
		DROP,
		USE
	}
	protected String description;
	protected String name;
	protected Item.ItemActionStrategy strategy;
	protected boolean isInPlayerInventory = false;
	
	/**
	 * @return true if in player's inventory
	 */
	protected boolean isInPlayerInventory() {
		return isInPlayerInventory;
	}

	/**
	 * @param whether the item is in the player's inventory
	 */
	protected void setInPlayerInventory(boolean isInPlayerInventory) {
		this.isInPlayerInventory = isInPlayerInventory;
	}

	// ********** STRATEGY METHODS ********** //
	protected String performAction(Action action, Item item, Player player) {
		switch(action) {
		case PICKUP:
			strategy = new PickupableStrategy();
			break;
		case DROP:
			strategy = new PickupableStrategy();
			break;
		default:
			break;
		}
		return strategy.performAction(action, this, player);
	} 
	
	protected void setItemAction(ItemActionStrategy strategy) {
		this.strategy = strategy;
	}
	
	// ********** ABSTRACT METHODS ********** //
	abstract protected String examine();
	abstract protected String[] getActions();
	
	// ********** STRATEGY INTERFACE ********** //
	public interface ItemActionStrategy {
		public String performAction(Action action,Item item, Player player);
	}
}
