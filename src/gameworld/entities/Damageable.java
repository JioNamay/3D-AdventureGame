package gameworld.entities;

public interface Damageable {
	public int getHealth();		// get current lifetime of the item
	public void die();			// what the item does when its lifetime is over
	public void getDamaged(int amount);
}
