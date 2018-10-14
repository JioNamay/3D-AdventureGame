package gameworld.entities;

import java.util.List;

import gameworld.Location.Direction;
import gameworld.entities.Item.Action;

public class Stick extends EquipableStrategy {
	
	public Stick() {
		this.durability = 1;
		this.description = "A magical potion that restores 10 points of health";
		this.name = "Health Potion";
		this.coinBank = 3;
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
		// TODO Auto-generated method stub
		return null;
	}

}
