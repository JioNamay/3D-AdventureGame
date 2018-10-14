package gameworld.entities;

import gameworld.Location;
import gameworld.Room;

public class Tree extends Monster {
	public Tree() {
		this.name = "Tree";
		this.description = "An innocent tree";
	}

	@Override
	public void die() {
		// remove tree from world and replace with drop
		Room currentRoom = Player.getInstance().getCurrentRoom();
		Location treeLoc = currentRoom.getGameItemLocation(new Item(this));
		currentRoom.addGameItem(treeLoc.getRow(), treeLoc.getCol(), new Item(new Stick()));
		Player.getInstance().addCoins(5); // give player coins
	}
}
