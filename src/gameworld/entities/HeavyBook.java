package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Item.Action;

public class HeavyBook extends EquipableStrategy {

	public HeavyBook() {
		// TODO Auto-generated constructor stub
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
