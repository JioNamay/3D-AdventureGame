package gameworld.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import gameworld.entities.Item.Action;

/**
 * The cactus exhibits 4 behaviors randomly every time the player examines it. This includes attacking the 
 * player, returning its description and giving coins, and giving the player a key.
 * @author Deanne Alabastro 300346210
 */
public class Cactus extends CoinBank{
	
	/**
	 * Instantiates a new cactus.
	 */
	public Cactus() {
		this.description = "An innocent cactus";
		this.name = "Cactus";
		this.coinBank = 5;
	}

	/** The actions. */
	private List<String> actions = Arrays.asList(Action.EXAMINE.toString());

	/**
	 * Examine.
	 *
	 * @return the string
	 */
	private String examine() {
		// generate random probability
		Random rand = new Random();
		int probability = rand.nextInt(4 + 1) + 1;
		
		// do action depending on probability
		switch(probability) {
		case 1:
			// give player coins, returns description if not enough coins in coinbank
			return givePlayerCoins(1);
		case 2:
			// deal some damage to the player
			int damage = rand.nextInt(5 + 1) + 1;
			Player.getInstance().getDamaged(damage);
			return "Shouldn't have gotten too close to the cactus. It decided to take " + damage + " bits of your life";
		case 3:
			// give the player a key if their inventory isn't full, else follow case 1
			if(Player.getInstance().getInventory().isFull()) givePlayerCoins(1);
			Key key = new Key();
			Player.getInstance().getInventory().add(key);
			return "You found a " + key.getName();
		default:
			return description;
		}
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#getActions()
	 */
	@Override
	public List<String> getActions() {
		return actions;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#performAction(gameworld.entities.Item.Action)
	 */
	@Override
	public String performAction(Action action) {
		switch(action) {
		case EXAMINE:
			return examine();
		default: 
			throw new IllegalArgumentException("Unknown action: " + action.toString() + " for object: "+ this.name);
		}
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#isSolid()
	 */
	@Override
	public boolean isSolid() {
		return true;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#isDoor()
	 */
	@Override
	public boolean isDoor() {
		return false;
	}
}
