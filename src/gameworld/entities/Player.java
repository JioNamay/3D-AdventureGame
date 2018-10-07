package gameworld.entities;

import controller.Controller;
import gameworld.Location;

public class Player extends FighterEntity {
	private int health;
	private Inventory inventory;
	private Weapon weapon;
	

	public Player(Controller controller, Location loc) {
		// TODO Auto-s constructor stub
		super(controller, loc);
		this.health = 100;
	}
	
	public void setInventory(Inventory inventory) {
		if(this.inventory == null) this.inventory = inventory;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public void equipWeapon() {
		
	}
	
	// ********** INHERITED METHODS ********** //

	@Override
	public void die() {
		// Game end
		
	}

	public void recover(int amount) {
		health = ((this.health + amount) > 100) ? 100 : health + amount;
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void attack(DamageableEntity opponent) {
		
	}
}
