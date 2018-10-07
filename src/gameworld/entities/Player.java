package gameworld.entities;

import controller.Controller;
import gameworld.Location;

public class Player extends Entity implements Damageable{
	private boolean canHealthRegenerate;
	private int health;
	private Inventory inventory;

	public Player(Controller controller, Location loc) {
		// TODO Auto-s constructor stub
		super(controller, loc);
		this.canHealthRegenerate = true;
		this.health = 100;
	}
	
	public void setInventory(Inventory inventory) {
		if(this.inventory == null) this.inventory = inventory;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	// ********** INHERITED METHODS ********** //
	
	@Override
	public boolean canHealthRegenerate() {
		return canHealthRegenerate;
	}

	@Override
	public void die() {
		// Game end
		
	}

	@Override
	public void recover(int amount) {
		health = ((this.health + amount) > 100) ? 100 : health + amount;
	}

	@Override
	public void hurt(int amount) {
		if((this.health - amount) < 0) {
			this.health = 0;
			this.setActive(false);
			this.die();
		}else {
			health -= amount;
		}
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
