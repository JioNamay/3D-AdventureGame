package gameworld.items;

import gameworld.Location;

public abstract class DamageableItem extends Item{

	protected int uses;

	public DamageableItem(Location location) {
		super(location);
	}
	
	/**
	 * @return the uses
	 */
	protected int getUses() {
		return uses;
	}

	/**
	 * @param uses the uses to set
	 */
	protected void setUses(int uses) {
		this.uses = uses;
	}
	
	protected String use() {
		this.uses--;
		if(uses == 0) return makeUnusable();
		return "Player used " + this.name;
	}
	
	abstract protected String makeUnusable();

}
