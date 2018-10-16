package gameworld.entities;

import java.util.Random;

/**
 * Has all the attacking methods used by entities that can attack such as the Player and items that
 * retaliate when attacked
 * @author Deanne Alabastro 300346210
 */
public abstract class AttackingEntity implements Damageable{

	/** The max damage. */
	protected int maxDamage;

	/** The health. */
	protected int health;

	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets the health.
	 *
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}


	/**
	 * Gets the max damage.
	 *
	 * @return the maxDamage
	 */
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	 * Sets the max damage.
	 *
	 * @param maxDamage the maxDamage to set
	 */
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	/**
	 * Decrease the object's health.
	 *
	 * @param amount the amount
	 * @return the damaged
	 */
	public void getDamaged(int amount) {
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
	 * @param opponent            the opponent
	 * @return the attackDamage
	 */
	protected int attack(AttackingEntity opponent) {
		int attackDamage = calculateAttackDamage();
		opponent.getDamaged(attackDamage);
		return attackDamage;
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
}
