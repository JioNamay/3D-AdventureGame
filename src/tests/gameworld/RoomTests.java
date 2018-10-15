package tests.gameworld;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameworld.Room;
import gameworld.Location.Direction;
import gameworld.entities.Door;
import gameworld.entities.Item;
import gameworld.entities.Note;
import gameworld.entities.Player;
import gameworld.entities.Potion;
import gameworld.entities.Wall;

class RoomTests {
	Room courtyard;
	Room foyer;

	@BeforeEach
	void setUp() throws Exception {
		courtyard = new Room("Courtyard");
		// add items etc....
		foyer = new Room("Foyer");
		// set up door
		Door door = new Door();
		door.setFirstRoom(courtyard);
		door.setSecondRoom(foyer);
		door.setFirstRoomDirection(Direction.SOUTH);
		door.setSecondRoomDirection(Direction.NORTH);
		Item item = new Item(door);
		// add door to both rooms
		courtyard.addGameItem(0,3, item);
		foyer.addGameItem(6, 3, item);
		
		// add gameItems to courtyard
		addWalls(0,3,courtyard);
		addWalls(6,3,foyer);
		courtyard.addGameItem(3, 3, new Item(new Potion()));
		foyer.addGameItem(3, 3, new Item(new Note()));
		Player.getInstance().setLocation(courtyard.getLocation(5, 3));
		
	}
	
	private void addWalls(int row, int col, Room room) {
		for (int i = 0; i < 7; i++) {
	        for (int j = 0; j < 7; j++) {
	        	if((i == 0 || i == 6) || (j == 0 || j == 6)) {
	        		if(i == row && j ==col) 
	        			continue; // skip as this is a door
	        		
	        		room.addGameItem(i, j, new Item(new Wall()));
	        	}
	        	
	        }
	    }
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
