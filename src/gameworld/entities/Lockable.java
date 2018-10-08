package gameworld.entities;

import controller.Controller;
import gameworld.Location;

public final class Lockable extends Entity{
	private boolean isOpen = false;
	private boolean isLocked = true;
	
	public Lockable(Controller controller, Location location) {
		super(controller, location);
		// TODO Auto-generated constructor stub
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
	protected void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void render() {
		// TODO Auto-generated method stub
		
	}
}
