package gameworld.entities;

import controller.Controller;
import gameworld.Location;

public class Player extends Entity {
	private int health;
	private Inventory inventory;
	private WeaponEntity weapon;
	private Location location;
	
	public Player(Controller controller, Location loc) {
		this.health = 100;
	}
	
	public void setInventory(Inventory inventory) {
		if(this.inventory == null) this.inventory = inventory;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public void equipWeapon(WeaponEntity weapon) {
		this.weapon = weapon;
		setAttackDamage();
	}
	
	private void setAttackDamage() {
		if(this.weapon == null) {
			// player uses fist
			this.maxDamage = 5;
		}else {
			//this.maxDamage = weapon.getAttackDamage(); 
		}
	}

	// ********** INHERITED "ABSTRACT" METHODS ********** //

	@Override
	public void die() {
		// Game end
		
	}

	public void recover(int amount) {
		health = ((this.health + amount) > 100) ? 100 : health + amount;
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
	protected String[] getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
}
