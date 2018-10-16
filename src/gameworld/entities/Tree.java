package gameworld.entities;

import gameworld.Location;
import gameworld.Room;

/**
 * The tree drops a stick when dead.
 * @author Deanne Alabastro 300346210
 */
public class Tree extends AttackingItems {
	
	/**
	 * Instantiates a new tree.
	 */
	public Tree() {
		this.name = "Tree";
		this.description = "An innocent tree";
		this.health = 10;
		this.maxDamage = 10;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Damageable#die()
	 */
	@Override
	public void die() {
		// remove tree from world and replace with drop
		Room currentRoom = Player.getInstance().getCurrentRoom();
		Location treeLoc = currentRoom.getGameItemLocation(new Item(this));
		currentRoom.addGameItem(treeLoc.getRow(), treeLoc.getCol(), new Item(new Stick()));
		Player.getInstance().addCoins(5); // give player coins
	}
}
