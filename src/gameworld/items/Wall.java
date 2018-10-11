package gameworld.items;

import java.util.List;

import gameworld.Location;
import gameworld.entities.Player;

public class Wall extends Item{

	public Wall(Location location) {
		super(location);
		location.setSolid(true);
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
