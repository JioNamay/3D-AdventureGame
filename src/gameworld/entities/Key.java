package gameworld.entities;

public class Key extends PickUpAbleStrategy implements Damageable {


	/**
	 * String description of the key
	 */
	@Override
	public String description() {
		return "Key is able to open any locked item.";
	}

	/**
	 * Overloading the parent method, in the case that the action
	 * is Examine. Then we return the String description of the key.
	 * It will also have a 'use' method, which is to be used when player
	 * is in the same location as a locked door
	 * Otherwise it will perform the parent's actions.
	 */
	public void performAction(String action) {
		if (action.equals("Examine")) {
			// get examinedItem display area from GUI
			// and return the string description of the item to it
			// along with maybe a rendered image of it
		}
		else {
			super.performAction(action);
		}
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

}
