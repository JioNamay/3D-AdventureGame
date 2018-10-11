package gameworld.items;

import java.util.List;

import gameworld.GameObjectInterface;
import gameworld.Location;
import gameworld.entities.Player;

public abstract class Item implements GameObjectInterface{
	public enum Action{
		EXAMINE,
		PICKUP, 
		DROP,
		USE,
		PLACE,
		TAKE,
		OPEN,
		CLOSE,
		UNLOCK,
		LOCK
	}
	
	public enum Direction{
		NORTH,
		SOUTH,
		EAST,
		WEST
	}
	protected String description;
	protected String name;
	protected Location location;
	protected boolean isInPlayerInventory = false;
	protected int coinBank;
	
	public Item(Location location) {
		this.location = location;
	}
	
	/**
	 * @return the coinBank
	 */
	public int getCoinBank() {
		return coinBank;
	}

	/**
	 * @param coinBank the coinBank to set
	 */
	public void setCoinBank(int coinBank) {
		this.coinBank = coinBank;
	}
	
	public String givePlayerCoins(int amount) {
		// if coinbank is not 0 but the amount is more than what coinbank has, just add all the remaining to player
		if((coinBank - amount < 0) && coinBank != 0) {
			Player.getInstance().addCoins(coinBank);
			String returnStr = "You found " + coinBank + " coins\n" + description;
			coinBank = 0;
			return returnStr;
		}
		
		if(coinBank == 0) return description; // no more coins left, return just description
		
		// give player the amount of coins
		Player.getInstance().addCoins(amount);
		coinBank -= amount;
		return "You found " + amount + " coins\n" + description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

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

	// ********** ABSTRACT METHODS ********** //
	abstract protected String examine();
	abstract protected String performAction(Action action,Player player);
	abstract protected List<String> getActions();
	
}
