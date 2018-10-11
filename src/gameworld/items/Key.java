package gameworld.items;

import java.util.List;

import gameworld.Location;
import gameworld.entities.Player;

public class Key extends PickupableItem{

	public Key(Location location) {
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
	protected String makeUnusable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String examine() {
		return null;
	}

	@Override
	protected String performAction(Action action, Player player) {

		return null;
	}

	@Override
	protected List<String> getActions() {
		// TODO Auto-generated method stub
		return null;
	}

}
