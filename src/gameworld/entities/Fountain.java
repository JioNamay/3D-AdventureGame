/**
 * 
 */
package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Item.Action;

/**
 * @author Deanne Alabastro 300346210
 *
 */
public class Fountain extends CoinBank{
	
	public Fountain() {
		this.description = "beautiful foutain";
		this.name = "Fountain";
		this.coinBank = 1000;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#getActions()
	 */
	@Override
	public List<String> getActions() {
		return Arrays.asList(Action.EXAMINE.toString(), Action.THROWCOINS.toString());
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#isSolid()
	 */
	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#isDoor()
	 */
	@Override
	public boolean isDoor() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.Strategy#performAction(gameworld.entities.Item.Action)
	 */
	@Override
	public String performAction(Action action) {
		switch(action) {
		case THROWCOINS:
			return null;
		default:
			return description;
		}
	}
	
	private String grantWish() {
		// if coins thrown between 0 - 5return stick
		// if coins thrown between 6 - 10 return coins
		// if coins thrown between 11 - 15 return rock
		// if coins thrown between 16-25 return heavyBook
		// if coins thrown between 26-31 return note
		// if coins thrown between 32-45 return key
		// if coins thrown greater than 45 return potion
		// default or no space in inventory return coins
		return "";
		
	}

}
