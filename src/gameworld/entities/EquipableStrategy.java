package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Item.Action;

public abstract class EquipableStrategy extends PickUpAbleStrategy implements Damageable {

	protected int maxDamage;
	protected int durability;

	public int getAttackDamage() {
		return maxDamage;
	}

	public void setAttackDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	@Override
	public String performAction(Action action) {
		switch (action) {
		case EXAMINE:
			return givePlayerCoins(1);
		case DROP:
			if(Player.getInstance().getEquipedWeapon().equals(this)) {
				return this.performAction(Action.UNEQUIP) + "\n" + drop();
			}
		case EQUIP:
			Player.getInstance().setEquipedWeapon(this);
			return "Player equipped " + this.name;
		case UNEQUIP:
			Player.getInstance().setEquipedWeapon(null);
			return "Player unequipped " + this.name;
		default:
			return super.performAction(action);
		}
	}

	@Override
	public List<String> getActions() {
		if (!Player.getInstance().getInventory().contains(this))
			return actions; // return default actions if not in inventory
		if (Player.getInstance().getEquipedWeapon().equals(this)) //if equipped
			return Arrays.asList(Action.EXAMINE.toString(), Action.UNEQUIP.toString(), Action.DROP.toString());
		return Arrays.asList(Action.EXAMINE.toString(), Action.EQUIP.toString(), Action.DROP.toString());
	}

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public boolean isDoor() {
		return false;
	}

	@Override
	public int getHealth() {
		return durability;
	}

	@Override
	public void getDamaged(int amount) {
		if ((this.durability - amount) < 0) {
			this.durability = 0;
			this.die();
		} else {
			durability -= amount;
		}
	}

	@Override
	public void die() {
		Player.getInstance().getInventory().remove(this);
		Player.getInstance().setEquipedWeapon(null);
	}

}
