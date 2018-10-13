package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Item.Action;

public class Rock extends PickUpAbleStrategy implements Damageable{

	private int durability;

	public Rock() {
		this.durability = 1;
		this.description = "A suspicious looking rock";
		this.name = "Rock";
		this.coinBank = 3;
	}

	@Override
	public List<String> getActions() {
		if(!Player.getInstance().getInventory().contains(this)) return actions; // return default actions if not in inventory
		
		// return new action if in inventory
		return Arrays.asList(Action.EXAMINE.toString(), Action.DROP.toString());
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
	public void die() {
		return;
	}

	@Override
	public void getDamaged(int amount) {
		if((this.durability - amount) < 0) {
			this.durability = 0;
			this.die();
		}else {
			durability -= amount;
		}
	}
	
	@Override
	public String performAction(Action action) {
		switch(action) {
		case EXAMINE:
			return givePlayerCoins(3);
		case DROP:
			PickUpAbleStrategy key = new Key();
			Player.getInstance().getInventory().remove(this);
			Player.getInstance().getInventory().add(key);
			return "Dropping the rock causes it to shatter into pieces revealing a key! It has been added to your inventory";
		default:
			return super.performAction(action);
		}	
	}
}
