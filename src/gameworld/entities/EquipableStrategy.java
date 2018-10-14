package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Item.Action;

/**
 * The EquipableStrategy is for every item that has the capability to be a weapon.
 * @author Deanne Alabastro 300346210
 */
public abstract class EquipableStrategy extends PickUpAbleStrategy implements Damageable {

	/** The max damage. */
	protected int maxDamage;
	
	/** The durability. */
	protected int durability;

	/**
	 * Gets the attack damage.
	 *
	 * @return the attack damage
	 */
	public int getAttackDamage() {
		return maxDamage;
	}

	/**
	 * Sets the attack damage.
	 *
	 * @param maxDamage the new attack damage
	 */
	public void setAttackDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}
 
	/* (non-Javadoc)
	 * @see gameworld.entities.PickUpAbleStrategy#performAction(gameworld.entities.Item.Action)
	 */
	@Override
	public String performAction(Action action) {
		switch (action) {
		case EXAMINE:
			return givePlayerCoins(1);
		case DROP:
			if(Player.getInstance().getEquippedWeapon().equals(this)) {
				return this.performAction(Action.UNEQUIP) + "\n" + drop();
			}
		case EQUIP:
			Player.getInstance().setEquippedWeapon(this);
			return "Player equipped " + this.name;
		case UNEQUIP:
			Player.getInstance().setEquippedWeapon(null);
			return "Player unequipped " + this.name;
		default:
			return super.performAction(action);
		}
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#getActions()
	 */
	@Override
	public List<String> getActions() {
		if (!Player.getInstance().getInventory().contains(this))
			return actions; // return default actions if not in inventory
		if (Player.getInstance().getEquippedWeapon().equals(this)) //if equipped
			return Arrays.asList(Action.EXAMINE.toString(), Action.UNEQUIP.toString(), Action.DROP.toString());
		return Arrays.asList(Action.EXAMINE.toString(), Action.EQUIP.toString(), Action.DROP.toString());
	} 

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#isSolid()
	 */
	@Override
	public boolean isSolid() {
		return false;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#isDoor()
	 */
	@Override
	public boolean isDoor() {
		return false;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Damageable#getHealth()
	 */
	@Override
	public int getHealth() {
		return durability;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Damageable#getDamaged(int)
	 */
	@Override
	public void getDamaged(int amount) {
		if ((this.durability - amount) < 0) {
			this.durability = 0;
			this.die();
		} else {
			durability -= amount;
		}
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Damageable#die()
	 */
	@Override
	public void die() {
		Player.getInstance().getInventory().remove(this);
		Player.getInstance().setEquippedWeapon(null);
	}

}
