package gameworld.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import gameworld.Location;
import gameworld.entities.Item.Action;

public class Cactus extends CoinBank{
	public Cactus(Location location) {
		super(location);
		this.description = "An innocent cactus";
		this.name = "Cactus";
		this.coinBank = 5;
	}

	private List<String> actions = Arrays.asList(Action.EXAMINE.toString());

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
			int damage = rand.nextInt(4 + 1) + 1;
			Player.getInstance().getDamaged(damage);
			return "Shouldn't have gotten too close to the cactus. It decided to take " + damage + " bits of your life";
		case 3:
			// give the player a key if their inventory isn't full, else follow case 1
			if(Player.getInstance().getInventory().isFull()) givePlayerCoins(1);
			Key key = new Key(null);
			Player.getInstance().getInventory().add(key);
			return "You found a " + key.getName();
		default:
			return description;
		}
	}

	@Override
	public List<String> getActions() {
		return actions;
	}

	@Override
	public String performAction(Action action) {
		switch(action) {
		case EXAMINE:
			return examine();
		default: 
			throw new IllegalArgumentException("Unknown action: " + action.toString() + " for object: "+ this.name);
		}
	}
}
