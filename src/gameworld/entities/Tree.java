package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.Location;
import gameworld.Location.Direction;
import gameworld.Room;
import gameworld.entities.Item.Action;

public class Tree extends AttackingEntity implements Strategy {

	private String name;
	private String description;

	public Tree() {
		this.name = "Tree";
		this.description = "An innocent tree";
	}

	@Override
	public void die() {
		// remove tree from world and replace with drop
		Room currentRoom = Player.getInstance().getCurrentRoom();
		Location treeLoc = currentRoom.getGameItemLocation(new Item(this));
		currentRoom.addGameItem(treeLoc.getRow(), treeLoc.getCol(), new Item(new Stick()));
		Player.getInstance().addCoins(5); // give player coins
	}

	@Override
	public List<String> getActions() {
		return Arrays.asList(Action.EXAMINE.toString(), Action.ATTACK.toString());
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public boolean isDoor() {
		return false;
	}

	@Override
	public Direction getDirection() {
		return Direction.NORTH;
	}

	@Override
	public void setDirection(Direction direction) {
		return;
	}

	@Override
	public String performAction(Action action) {
		switch (action) {
		case EXAMINE:
			return description;
		case ATTACK:
			EquipableStrategy weapon = Player.getInstance().getEquipedWeapon();
			if (weapon != null)
				weapon.getDamaged(1); // decrease durability of player's weapon
			int damage = Player.getInstance().attack(this); // get attacked by player
			
			// result of the player's attack
			String result = null;
			if (damage > 0)
				result = "Player caused " + damage + " damage to " + this.getName() + "'s life";
			else
				result = "Player's attack missed and caused no damage";

			// result of the item's retaliation
			String retaliationStr = null;
			damage = this.attack(Player.getInstance());
			if (damage > 0)
				retaliationStr = "The " + this.getName()
						+ " shook from the attack attempt and dropped bits of itself onto the player. It caused "
						+ damage + "damage.";
			else
				retaliationStr = "Bits of the " + this.getName()
						+ " fell near the player due to the attack. It caused no damage.";
			return result + "\n" + retaliationStr;

		default:
			throw new IllegalArgumentException("Unknown action: " + action.toString() + " for object: "+ this.getName());
		}
	}

}
