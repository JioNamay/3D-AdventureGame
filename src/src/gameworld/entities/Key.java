package gameworld.entities;

import java.util.List;

import gameworld.Location;
import gameworld.entities.Item.Action;

public class Key extends PickUpAbleStrategy implements Damageable {



	public Key(Location location) {
		super(location);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Overloading the parent method, in the case that the action
	 * is Examine. Then we return the String description of the key.
	 * It will also have a 'use' method, which is to be used when player
	 * is in the same location as a locked door
	 * Otherwise it will perform the parent's actions.
	 */
	public String performAction(Action action) {
		if (action.equals("Examine")) {
			// get examinedItem display area from GUI
			// and return the string description of the item to it
			// along with maybe a rendered image of it
		}
		else {
			return super.performAction(action);
		}
		
		return null;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void die() {
		// what key does when its lifetime is over
		// ie. after it has been used

	}

	@Override
	public void getDamaged(int amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getActions() {
		// TODO Auto-generated method stub
		return null;
	}

}
