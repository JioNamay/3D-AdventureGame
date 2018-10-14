/**
 * 
 */
package gameworld.entities;

import gameworld.Location;
import gameworld.Room;

/**
 * Sofa can be attacked and gives players coins when it disappears.
 * @author Deanne Alabastro 300346210
 *
 */
public class Sofa extends Monster{

	/**
	 * Instantiates a new Sofa.
	 */
	public Sofa() {
		this.name = "Sofa";
		this.description = "comfy sofa";
		this.health = 50;
		this.maxDamage = 15;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Damageable#die()
	 */
	@Override
	public void die() {
		// remove tree from world and replace with drop
		Room currentRoom = Player.getInstance().getCurrentRoom();
		Location sofaLoc = currentRoom.getGameItemLocation(new Item(this));
		currentRoom.removeGameItem(sofaLoc);
		Player.getInstance().addCoins(20); // give player coins
	}

}
