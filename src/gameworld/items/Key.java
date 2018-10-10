package gameworld.items;

import java.util.List;

import gameworld.Location;
import gameworld.entities.Player;

public class Key extends PickupableItem{

	public Key(Location location) {
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		return null;
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
