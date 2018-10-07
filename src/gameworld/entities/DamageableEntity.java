package gameworld.entities;

import controller.Controller;
import gameworld.Location;

public abstract class DamageableEntity extends Entity{
	protected int health;
	public DamageableEntity (Controller controller, Location location) {
		super(controller, location);
	}
	
	protected abstract void die();
	
	protected void hurt(int amount) {
		if((this.health - amount) < 0) {
			this.health = 0;
			this.setActive(false);
			this.die();
		}else {
			health -= amount;
		}
	}
}
