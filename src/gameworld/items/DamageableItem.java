package gameworld.items;

public abstract class DamageableItem extends Item{

	protected int uses;

	public DamageableItem() {
		// TODO Auto-generated constructor stub
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
		if(uses == 0) makeUnusable();
	}
	
	protected String makeUnusable() {
		this.setLocation(null);
		this.setInPlayerInventory(false);
		return "It seems that the " + this.name + " has been used too many times. It broke.";
	}

}
