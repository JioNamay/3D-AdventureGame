package gameworld.entities;

import java.util.List;

import gameworld.Location;
import gameworld.entities.Player;
import gameworld.entities.Strategy;

public class Wall implements Strategy{

	@Override
	public List<String> getActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void performAction(String action) {
		// TODO Auto-generated method stub

	}

	// ======================================================

	/*public Wall(Location location) {
		super(location);
		location.setSolid(true);
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
	}*/

}
