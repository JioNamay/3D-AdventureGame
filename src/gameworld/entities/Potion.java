package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.Location;
import gameworld.entities.Damageable;
import gameworld.entities.PickUpAbleStrategy;
import gameworld.entities.Player;

public class Potion extends PickUpAbleStrategy implements Damageable {

	/*public Potion(Location location) {
		super(location);
		this.uses = 1;
		this.description = "A magical potion that restores 10 points of health";
		this.name = "Health Potion";
		this.coinBank = 3;
	}*/


	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getDamaged(int amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void performAction(String action) {
		if (action.equals("Examine")) {
			// get examinedItem display area from GUI
			// and return the string description of the item to it
			// along with maybe a rendered image of it
		}
		else if (action.equals("Use")) {
			// use the potion
		}
		else {
			super.performAction(action);
		}
	}

	@Override
	public List<String> getActions() {
		actions.add("Use");
		return actions;
	}

	// ===================================================================

		/*@Override
		protected String examine() {
			return givePlayerCoins(3);
		}

		@Override
		protected List<String> getActions() {
			if (!this.isInPlayerInventory)
				return Arrays.asList(Action.EXAMINE.toString(), Action.PICKUP.toString() );
			else
				return Arrays.asList(Action.EXAMINE.toString(), Action.PICKUP.toString(), Action.DROP.toString(),
						Action.USE.toString());
		}

		@Override
		protected String makeUnusable() {
			this.setLocation(null);
			this.setInPlayerInventory(false);
			return "It seems that the " + this.name + " has been used too many times. It broke.";
		}

		@Override
		protected String performAction(Action action, Player player) {
			switch (action) {
			case EXAMINE:
				return examine();
			case PICKUP:
				return pickUp(player, this);
			case DROP:
				return drop(player, this);
			case USE:
				return use();
			default:
				throw new IllegalArgumentException("Unknown action: " + action);
			}
		}*/

}
