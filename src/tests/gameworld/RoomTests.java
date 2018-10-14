package tests.gameworld;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameworld.Room;
import gameworld.Location.Direction;
import gameworld.entities.Door;
import gameworld.entities.Item;
import gameworld.entities.Player;

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
		Player.getInstance().setLocation(courtyard.getLocation(5, 3));
		
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
