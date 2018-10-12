package gameworld.entities;

import gameworld.Location;

public class Tree extends Entity{

	private Location location;

	public Tree(Location location) {
		this.location = location;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public void setLocation(Location location) {
		this.location = location;
		
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
	protected void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String[] getStats() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
