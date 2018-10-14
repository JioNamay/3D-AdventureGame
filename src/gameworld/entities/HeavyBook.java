package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Item.Action;

/**
 * The heavybook is an item that can be obtained through the bookshelf. It can be used as a weapon.
 * @author Deanne Alabastro 300346210
 */
public class HeavyBook extends EquipableStrategy {

	/**
	 * Instantiates a new heavy book.
	 */
	public HeavyBook() {
		this.durability = 5;
		this.maxDamage = 15;
		this.description = "A magical potion that restores 10 points of health";
		this.name = "Health Potion";
		this.coinBank = 3;
	}
	
	/* (non-Javadoc)
	 * @see gameworld.entities.EquipableStrategy#performAction(gameworld.entities.Item.Action)
	 */
	@Override
	public String performAction(Action action) {
		switch(action) {
		case USE:
			return read();
		default:
			return super.performAction(action);
		}	
	}

	/**
	 * Read.
	 *
	 * @return the string
	 */
	private String read() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see gameworld.entities.EquipableStrategy#getActions()
	 */
	@Override
	public List<String> getActions() {
		if (!Player.getInstance().getInventory().contains(this))
			return actions; // return default actions if not in inventory
		return Arrays.asList(Action.EXAMINE.toString(), Action.USE.toString(), Action.EQUIP.toString(),
				Action.DROP.toString());
	}

}
