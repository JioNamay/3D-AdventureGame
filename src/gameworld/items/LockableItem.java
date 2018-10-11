package gameworld.items;

import gameworld.Location;

public abstract class LockableItem extends Item{
	
	protected boolean isOpen = false;
	protected boolean isLocked = true;
	protected Direction direction;
	
	public LockableItem(Location location) {
		super(location);
	}
	
	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public boolean open() {
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

