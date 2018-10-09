package gameworld.entities;

public interface Damageable {
	public int getLifeTime();		// get current lifetime of the item
	public void die();			// what the item does when its lifetime is over
}
