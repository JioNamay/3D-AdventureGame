package gameworld.items;

import java.util.Arrays;
import java.util.List;

import gameworld.Location;
import gameworld.entities.Player;

public class Fountain extends Item{

	public Fountain(Location location) {
		super(location);
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
		// TODO Auto-generated method stub
		return "A mysterious fountain";
	}

	@Override
	protected String performAction(Action action, Player player) {
		switch(action) {
		case THROWCOINS:
			if(player.getCoins() == 0) return "You have no coins to throw";
			// ASK how many coins they want to throw
			// give player item based on amount of coins
			return examine();
		case EXAMINE:
			return examine();
		default: 
			return examine();
		}
	}

	@Override
	protected List<String> getActions() {
		return Arrays.asList(Action.EXAMINE.toString(), Action.THROWCOINS.toString());
	}

}
