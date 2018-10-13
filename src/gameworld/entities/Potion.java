package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Damageable;
import gameworld.entities.Item.Action;
import gameworld.entities.PickUpAbleStrategy;
import gameworld.entities.Player;

public class Potion extends PickUpAbleStrategy implements Damageable {

	private int uses;

	public Potion() {
		this.uses = 1;
		this.description = "A magical potion that restores 10 points of health";
		this.name = "Health Potion";
		this.coinBank = 3;
	}


	@Override
	public int getHealth() {
		return uses;
	}

	@Override
	public void die() {
		Player.getInstance().getInventory().remove(this);
	}

	@Override
	public void getDamaged(int amount) {
		if((this.uses - amount) < 0) {
			this.uses = 0;
			this.die();
		}else {
			uses -= amount;
		}
	}
 

	@Override
	public String performAction(Action action) {
		switch(action) {
		case EXAMINE:
			return givePlayerCoins(3);
		case USE:
			getDamaged(1);
			return "Player drank the potion. As the last drop of liquid leaves the bottle, the bottle magically disappears!";
		default:
			return super.performAction(action);
		}	
	}

	@Override
	public List<String> getActions() {
		if(!Player.getInstance().getInventory().contains(this)) return actions; // return default actions if not in inventory
		
		// return new action if in inventory
		return Arrays.asList(Action.EXAMINE.toString(), Action.USE.toString(), Action.DROP.toString());
	}


	@Override
	public boolean isSolid() {
		return false;
	}


	@Override
	public boolean isDoor() {
		return false;
	}

}
