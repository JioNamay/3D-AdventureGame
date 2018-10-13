package gameworld.entities;

import java.util.List;

import gameworld.Location.Direction;
import gameworld.entities.Item.Action;

/**
 * The wall object represents a wall and marks a location as solid so that a player cannot walk past it.
 */
public class Wall implements Strategy{

	/**
	 * Instantiates a new wall.
	 *
	 * @param location the location
	 */
	public Wall() {
		
	}

	@Override
	public List<String> getActions() {
		return null;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isDoor() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Direction getDirection() {
		return null;
	}

	@Override
	public void setDirection(Direction direction) {
	
	}

	@Override
	public String performAction(Action action) {
		return null;
	}
}
