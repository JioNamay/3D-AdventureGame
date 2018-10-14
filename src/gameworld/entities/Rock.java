package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Item.Action;

/**
 * The rock, when dropped, removes itself from the players inventory and replaces itself with a key.
 * @author Deanne Alabastro 300346210
 */
public class Rock extends PickUpAbleStrategy implements Damageable{

	/** The durability. */
	private int durability;

	/**
	 * Instantiates a new rock.
	 */
	public Rock() {
		this.durability = 1;
		this.description = "A suspicious looking rock";
		this.name = "Rock";
		this.coinBank = 3;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#getActions()
	 */
	@Override
	public List<String> getActions() {
		if(!Player.getInstance().getInventory().contains(this)) return actions; // return default actions if not in inventory
		
		// return new action if in inventory
		return Arrays.asList(Action.EXAMINE.toString(), Action.DROP.toString());
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
	 * @see gameworld.entities.Damageable#die()
	 */
	@Override
	public void die() {
		return;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Damageable#getDamaged(int)
	 */
	@Override
	public void getDamaged(int amount) {
		if((this.durability - amount) < 0) {
			this.durability = 0;
			this.die();
		}else {
			durability -= amount;
		}
	}
	
	/* (non-Javadoc)
	 * @see gameworld.entities.PickUpAbleStrategy#performAction(gameworld.entities.Item.Action)
	 */
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
