package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.Location.Direction;
import gameworld.entities.Item.Action;

/**
 * A monster is any entity besides the Player that can attack and get damaged.
 * @author Deanne Alabastro 300346210
 */
public abstract class Monster extends AttackingEntity implements Strategy{
	
	/** The name. */
	protected String name;
	
	/** The description. */
	protected String description;
	
	/** The direction. */
	protected Direction direction;
	
	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#getActions()
	 */
	@Override
	public List<String> getActions() {
		return Arrays.asList(Action.EXAMINE.toString(), Action.ATTACK.toString());
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#isSolid()
	 */
	@Override
	public boolean isSolid() {
		return true;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#isDoor()
	 */
	@Override
	public boolean isDoor() {
		return false;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#getDirection()
	 */
	@Override
	public Direction getDirection() {
		if(direction == null) return Direction.NORTH;
		return direction;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#setDirection(gameworld.Location.Direction)
	 */
	@Override
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#performAction(gameworld.entities.Item.Action)
	 */
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
						+ " shook from the attack attempt and dropped bits of itself onto the player. It caused "
						+ damage + "damage.";
			else
				retaliationStr = "Bits of the " + this.name
						+ " fell near the player due to the attack. It caused no damage.";
			return result + "\n" + retaliationStr;

		default:
			throw new IllegalArgumentException("Unknown action: " + action.toString() + " for object: "+ this.name);
		}
	}

	
}
