package gameworld.entities;

import java.util.Random;

import gameworld.GameObjectInterface;

/**
 * The Entity class includes any attackable thing that may optionally drop items
 * upon death
 */
public abstract class Entity implements GameObjectInterface {
	
	// ********** ATTACKING METHODS ********** //
	
	/** The max damage. */
	protected int maxDamage;
	
	/** The health. */
	protected int health;
	
	/**
	 * Decrease the object's health.
	 *
	 * @param amount the amount
	 * @return the damaged
	 */
	protected void getDamaged(int amount) {
		if((this.health - amount) < 0) {
			this.health = 0;
			this.die();
		}else {
			health -= amount;
		}
	}

	/**
	 * Attack.
	 *
	 * @param opponent
	 *            the opponent
	 */
	protected boolean attack(Entity opponent) {
		int attackDamage = calculateAttackDamage();
		if (attackDamage == 0)
			return false;
		else {
			opponent.getDamaged(attackDamage);
			return true;
		}
	}

	/**
	 * Generate random attack damage.
	 *
	 * @return random attack damage to be used in calculating actual attack damage
	 */
	private int generateRandomDamage() {
		int damage = 0;
		int max = maxDamage / 2; // makes sure calculated damage does not go above maxDamage
		for (int i = 0; i < 2; i++) {
			damage += new Random().nextInt(max + 1);
		}
		return damage;
	}

	/**
	 * Calculates attack damage by randomly generating a number between the damage
	 * range. Can also be 0 which means the attack had no effect or missed.
	 *
	 * @return attack damage to be used in attack
	 */
	protected int calculateAttackDamage() {
		// generate the entity's attack damage, taking the min between two possible
		// random attack damages
		int damage = Math.min(generateRandomDamage(), generateRandomDamage());

		// with 5% chance, check to see if entity can increase attack damage through a
		// "critical hit"
		if (new Random().nextInt(101) < 5)
			damage += generateRandomDamage();

		return damage;
	}
	
	// ********** ABSTRACT METHODS ********** //
	
	/**
	 * Die.
	 */
	protected abstract void die();
	
	/**
	 * Gets the entity's stats.
	 *
	 * @return the stats
	 */
	abstract protected String[] getStats();

}
