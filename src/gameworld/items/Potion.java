package gameworld.items;

import gameworld.Location;

public class Potion extends DamageableItem{

	private Location location;
	

	public Potion() {
		this.uses = 1;
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
	protected String examine() {
		return "A magical potions that restores 10 points of health";
	}

	@Override
	protected String[] getActions() {
		if(!this.isInPlayerInventory) return new String[] {Action.EXAMINE.toString(),Action.PICKUP.toString()};
		else return new String[]{Action.EXAMINE.toString(),Action.PICKUP.toString(), Action.DROP.toString(), Action.USE.toString()};
	}

}
