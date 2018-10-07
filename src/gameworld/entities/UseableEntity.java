package gameworld.entities;

import controller.Controller;
import gameworld.Location;

public abstract class UseableEntity extends DamageableEntity {

	public UseableEntity(Controller controller, Location location) {
		super(controller, location);
	}
	
	protected void use() {
		this.hurt(1);
	}
}
