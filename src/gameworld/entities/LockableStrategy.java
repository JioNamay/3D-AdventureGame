package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Item.Action;

/**
 * For all the items that can be locked.
 * @author yangcarr 300368805
 * @author Deanne Alabastro 300346210
 */
public abstract class LockableStrategy extends CoinBank {
	
	/** The actions. */
	protected List<String> actions = Arrays.asList(Action.EXAMINE.toString(), Action.UNLOCK.toString()); 

	/** The is locked. */
	protected boolean isOpen, isLocked;
	
	/** The description. */
	protected String description;
	
	/** The name. */
	protected String name;
 
	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#getActions()
	 */
	@Override
	public List<String> getActions() {
		if(isLocked) {
			if (Player.getInstance().getInventory().hasKey())
				return actions;
			else return Arrays.asList(Action.EXAMINE.toString());
		}else {
			if(isOpen) return Arrays.asList(Action.EXAMINE.toString(), Action.CLOSE.toString());
			else return Arrays.asList(Action.EXAMINE.toString(), Action.OPEN.toString());
		}
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#performAction(gameworld.entities.Item.Action)
	 */
	@Override
	public String performAction(Action action) {
		switch(action) {
		case UNLOCK:
			return unlock();
		case OPEN:
			return open();
		case CLOSE:
			return close();
		default:
			throw new IllegalArgumentException("Unknown action: " + action.toString() + " for object: "+ this.name);
		}
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.CoinBank#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Unlock.
	 *
	 * @return the string
	 */
	private String unlock() {
		if (Player.getInstance().getInventory().hasKey()) {
			PickUpAbleStrategy key = Player.getInstance().getInventory().getAKey();
			key.performAction(Action.USE);
			isLocked = false;
			return "Player unlocked " + this.name;
		}
		return "You cannot unlock something without a key";
	}
	
	/**
	 * Open.
	 *
	 * @return the string
	 */
	private String open() {
		if(isLocked) return "You cannot open a " + this.name + " while it is locked!";
		isOpen = true;
		return "You opened a " + this.name;
	}
	
	/**
	 * Close.
	 *
	 * @return the string
	 */
	private String close() {
		if(isLocked || !isOpen) return "You cannot close a door that's locked or already closed!";
		isOpen = false;
		return "You closed a " + this.name;
	}

	/**
	 * @return the isOpen
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * @param isOpen the isOpen to set
	 */
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	/**
	 * @return the isLocked
	 */
	public boolean isLocked() {
		return isLocked;
	}

	/**
	 * @param isLocked the isLocked to set
	 */
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

}
