package gameworld.entities;

import java.util.List;

import gameworld.Location;
import gameworld.Location.Direction;
import gameworld.Room;
import gameworld.entities.Item.Action;

public class Tree extends AttackingEntity implements Strategy{

	public Tree() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void die() {
		// remove tree from world and replace with drop
		Room currentRoom = Player.getInstance().getCurrentRoom();
		Location treeLoc = currentRoom.getGameItemLocation(new Item(this));
		currentRoom.addGameObject(treeLoc, new Item (new Stick()));
	}

	@Override
	public List<String> getActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDoor() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Direction getDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDirection(Direction direction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String performAction(Action action) {
		switch(action) {
		case ATTACK:
			Player.getInstance().attack(this);
		default:
			//return super.performAction(action);
		}	
	}

}
