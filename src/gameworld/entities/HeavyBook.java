package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Item.Action;

public class HeavyBook extends EquipableStrategy {

	public HeavyBook() {
		this.durability = 5;
		this.maxDamage = 15;
		this.description = "A magical potion that restores 10 points of health";
		this.name = "Health Potion";
		this.coinBank = 3;
	}
	
	@Override
	public String performAction(Action action) {
		switch(action) {
		case USE:
			return read();
		default:
			return super.performAction(action);
		}	
	}

	private String read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getActions() {
		if (!Player.getInstance().getInventory().contains(this))
			return actions; // return default actions if not in inventory
		return Arrays.asList(Action.EXAMINE.toString(), Action.USE.toString(), Action.EQUIP.toString(),
				Action.DROP.toString());
	}

}
