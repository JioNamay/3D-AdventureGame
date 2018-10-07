package gameworld.entities;

import controller.Controller;
import gameworld.Location;

public abstract class PickupableEntity extends DamageableEntity{
	public PickupableEntity(Controller controller, Location location) {
		super(controller, location);
		// TODO Auto-generated constructor stub
	}

	public String pickUp(Player player) {
		if(player.getInventory().isFull()) return "Cannot pick up item. Inventory is Full";
		this.setLocation(player.getLocation());
		this.setActive(false);
		player.getInventory().add(this);
		return "Player picked up " + this.toString();
	}
	
	public String drop(Player player) {
		player.getInventory().remove(this);
		this.setLocation(new Location(player.getLocation().getRow(), player.getLocation().getCol()));
		this.setActive(true);
		return "Player dropped " + this.toString();
	}
}
