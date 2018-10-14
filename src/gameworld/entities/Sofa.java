/**
 * 
 */
package gameworld.entities;

import gameworld.Location;
import gameworld.Room;
import gameworld.entities.Item.Action;

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
	
	@Override
	public String performAction(Action action) {
		switch (action) {
		case EXAMINE:
			return description;
		case ATTACK:
			EquipableStrategy weapon = Player.getInstance().getEquippedWeapon();
			if (weapon != null)
				weapon.getDamaged(1); // decrease durability of player's weapon
			int damage = Player.getInstance().attack(this); // get attacked by player
			
			// result of the player's attack
			String result = null;
			if (damage > 0)
				result = "Player caused " + damage + " damage to " + this.name + "'s life";
			else
				result = "Player's attack missed and caused no damage";

			// result of the item's retaliation
			String retaliationStr = null;
			damage = this.attack(Player.getInstance());
			if (damage > 0)
				retaliationStr = "The " + this.name
						+ " shook from the attack attempt and moved towards the player. The player stubbed their toe, causing "
						+ damage + "damage.";
			else
				retaliationStr = "The " + this.name
						+ " moved and almost hit your knee. It caused no damage.";
			return result + "\n" + retaliationStr;

		default:
			throw new IllegalArgumentException("Unknown action: " + action.toString() + " for object: "+ this.name);
		}

}
