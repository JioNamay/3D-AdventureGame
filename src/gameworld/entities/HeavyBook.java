package gameworld.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import gameworld.entities.Item.Action;

/**
 * The heavybook is an item that can be obtained through the bookshelf. It can
 * be used as a weapon. The player can also read from it.
 * 
 * @author Deanne Alabastro 300346210
 */
public class HeavyBook extends EquipableStrategy {
	
	/** The probability - used to determine what the user reads from the book. */
	private int probability;

	/**
	 * Instantiates a new heavy book.
	 */
	public HeavyBook() {
		this.durability = 5;
		this.maxDamage = 15;
		this.description = "heavy book";
		this.name = "Book";
		this.coinBank = 3;
		
		Random rand = new Random();
		probability = rand.nextInt(5 + 1) + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gameworld.entities.EquipableStrategy#performAction(gameworld.entities.Item.
	 * Action)
	 */
	@Override
	public String performAction(Action action) {
		switch (action) {
		case READ:
			return read();
		default:
			return super.performAction(action);
		}
	}

	/**
	 * Read the book.
	 *
	 * @return the words written in the book
	 */
	private String read() {
		switch (probability) {
		case 1:
			return "July 10 XXXX: The fountain outside is really miraculous! "
					+ "Today it gave me a bottle with some mysterious liquid. I wonder what it is.";
		case 2:
			return "September 2 XXXX: I finally drank the contents of that bottle. "
					+ "I don't think there's anything special about it. I guess I felt less tired after drinking it.";
		case 3:
			return "It seems this book got wet at some point. The words are illegible.";
		case 4:
			return "May 20 XXXX: My momma always told me to do unto others as you would have them do to you. "
					+ "She was right.";
		case 5: 
			return "May 20 XXXX: I accidentally kicked the sofa. Thanks to that I hurt my toe.";
		default:
			return "This book only has blank pages.";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameworld.entities.EquipableStrategy#getActions()
	 */
	@Override
	public List<String> getActions() {
		if (!Player.getInstance().getInventory().contains(this))
			return actions; // return default actions if not in inventory
		if (Player.getInstance().getEquippedWeapon().equals(this)) // if equipped
			return Arrays.asList(Action.EXAMINE.toString(), Action.READ.toString(), Action.UNEQUIP.toString(),
					Action.DROP.toString());
		return Arrays.asList(Action.EXAMINE.toString(), Action.READ.toString(), Action.EQUIP.toString(),
				Action.DROP.toString());
	}

}
