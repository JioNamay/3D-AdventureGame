//package gameworld.entities;
//
//import java.util.ArrayList;
//import java.util.concurrent.ThreadLocalRandom;
//
//import gameworld.items.Item;
//import gameworld.Location;
//
//public class Monster implements Damageable{
//
//	private ArrayList<Item> itemDrops; // the list of possible items the monster can drop after being defeated
//
//	/**
//	 * Construct a new monster, which will battle the player if the player
//	 * encounters the monster.
//	 */
//	public Monster(ArrayList<Item> itemDrops) {
//		this.itemDrops = itemDrops;
//	}
//
//	// ********** GETTERS AND SETTERS ********** //
//
//	/**
//	 * Get the list of possible items the monster can drop after being defeated.
//	 *
//	 * @return the itemDrops
//	 */
//	public ArrayList<Item> getItemDrops() {
//		return itemDrops;
//	}
//
//	/**
//	 * Set the list of possible items the monster can drop after being defeated.
//	 *
//	 * @param itemDrops
//	 *            the list of possible items the monster can drop after being
//	 *            defeated
//	 */
//	public void setItemDrops(ArrayList<Item> itemDrops) {
//		this.itemDrops = itemDrops;
//	}
//
//
//	@Override
//	public void die() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public int getHealth() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int getDamage() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//}
