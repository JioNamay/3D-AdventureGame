package gameworld.items;

import java.util.List;
import java.util.Random;

import gameworld.Location;
import gameworld.entities.Player;

public class Cactus extends Item{

	public Cactus(Location location) {
		super(location);
		coinBank = 5;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String examine() {
		Random rand = new Random();
		int probability = rand.nextInt(4 + 1) + 1;
		switch(probability) {
		case 1:
			givePlayerCoins(1);
		case 2:
			int damage = rand.nextInt(4 + 1) + 1;
			Player.getInstance().getDamaged(damage);
			return "Shouldn't have gotten too close to the cactus. It decided to take " + damage + " bits of your life";
		case 3:
			if(Player.getInstance().getInventory().isFull()) givePlayerCoins(1);
			Key key = new Key(Player.getInstance().getLocation());
			Player.getInstance().getInventory().add(key);
			key.setInPlayerInventory(true);
			return "You found a " + key.getName();
		default:
			return description;
		}
	}

	@Override
	protected String performAction(Action action, Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<String> getActions() {
		// TODO Auto-generated method stub
		return null;
	}

}
