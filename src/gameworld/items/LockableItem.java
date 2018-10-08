package gameworld.items;

import gameworld.Location;
import gameworld.entities.Player;

public final class LockableItem extends Item{
	
	private boolean isOpen = false;
	private boolean isLocked = true;
	
	public LockableItem(Location location) {
		super(location);
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
	
	
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocation(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String examine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String performAction(Action action, Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getActions() {
		// TODO Auto-generated method stub
		return null;
	}
}
