package tests.gameworld;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameworld.Room;
import gameworld.Location;
import gameworld.Location.Direction;
import gameworld.entities.Door;
import gameworld.entities.Inventory;
import gameworld.entities.Item;
import gameworld.entities.Item.Action;
import gameworld.entities.Key;
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
		foyer = new Room("Test");
		foyer.setName("Foyer");
		assertEquals(foyer.getName(), "Foyer", "Expected name foyer but was not");
		
		// set up door
		Door door = new Door();
		door.setFirstRoom(courtyard);
		door.setSecondRoom(foyer);
		door.setFirstRoomDirection(Direction.SOUTH);
		door.setSecondRoomDirection(Direction.NORTH);
		Item item = new Item(door);
		// add door to both rooms
		courtyard.addGameItem(0, 3, item);
		foyer.addGameItem(6, 3, item);

		// add gameItems to courtyard
		addWalls(courtyard);
		addWalls(foyer);
		courtyard.addGameItem(3, 3, new Item(new Potion()));
		foyer.addGameItem(3, 3, new Item(new Note()));
		Player.getInstance().setLocation(courtyard.getLocation(5, 3));
		Player.getInstance().setCurrentRoom(courtyard);

	}

	private void addWalls(Room room) {
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				if ((i == 0 || i == 6) || (j == 0 || j == 6)) {
					if (room.getLocation(i, j).isDoor())
						continue; // skip as this is a door

					room.addGameItem(i, j, new Item(new Wall()));
				}

			}
		}
	}

	@Test
	void testMoveValid1() {
		assertTrue(Player.getInstance().getCurrentRoom().movePlayer(Direction.NORTH));
	}

	@Test
	void testInvalidMovePastWall() {
		Room room = Player.getInstance().getCurrentRoom();
		assertFalse(room.movePlayer(Direction.SOUTH)); 
		room.movePlayer(Direction.WEST);
		assertTrue(Player.getInstance().getLocation().equals(room.getLocation(5, 2)));
		room.movePlayer(Direction.WEST);
		assertFalse(room.movePlayer(Direction.WEST)); // cant go past wall on left
		
	}

	@Test
	void testInvalidMovePastLockedDoor() {
		// move to door
		for (int i = 0; i < 4; i++) {
			assertTrue(Player.getInstance().getCurrentRoom().movePlayer(Direction.NORTH));
		}
		// Reach door location
		assertTrue(Player.getInstance().getCurrentRoom().movePlayer(Direction.NORTH));
		// cant get past door location to get to next room because it is locked
		assertFalse(Player.getInstance().getCurrentRoom().movePlayer(Direction.NORTH));
	}

	@Test
	void testPlayerCanMoveRooms() {
		// set player's location to be one off the door
		Room currentRoom = Player.getInstance().getCurrentRoom();
		Location nearDoor = currentRoom.getLocation(1, 3);
		Player.getInstance().setLocation(nearDoor);

		// unlock the door
		Player.getInstance().getInventory().add(new Key());
		Location doorLoc = currentRoom.getLocation(0, 3);
		Door door = (Door) currentRoom.getGameItems().get(doorLoc).getItem();
		door.performAction(Action.UNLOCK);
		currentRoom.movePlayer(Direction.NORTH); // move to door location
		assertFalse(currentRoom.movePlayer(Direction.NORTH)); // should be false because door is still closed
		door.performAction(Action.OPEN);
		assertTrue(currentRoom.movePlayer(Direction.NORTH)); // move rooms
		assertEquals(Player.getInstance().getCurrentRoom(), foyer, "Player expected to have moved rooms but did not");
		assertFalse(courtyard.hasPlayer());
		assertTrue(foyer.hasPlayer());
	}

	@Test
	void testPlayerDropItem() {
		Inventory in = Player.getInstance().getInventory();
		in.add(new Key());
		in.iterator().next().performAction(Action.DROP);
		Room room = Player.getInstance().getCurrentRoom();
		assertTrue(room.getGameItems().get(Player.getInstance().getLocation()) != null);
	}

	@Test
	void testPlayerPickUpItem() {
		Room room = Player.getInstance().getCurrentRoom();
		room.movePlayer(Direction.NORTH);
		room.movePlayer(Direction.NORTH);
		room.getGameItems().get(Player.getInstance().getLocation()).getItem().performAction(Action.PICKUP);
		assertTrue(Player.getInstance().getInventory().iterator().hasNext());
		assertTrue(room.getGameItems().get(Player.getInstance().getLocation()) == null);
	}

	@Test
	void testPlayerInvalidDrop() {
		Room room = Player.getInstance().getCurrentRoom();
		room.addGameItem(5, 3, new Item(new Potion()));
		Inventory in = Player.getInstance().getInventory();
		in.add(new Key());
		assertTrue(
				in.iterator().next().performAction(Action.DROP).equals("Player cannot drop Key location is occupied."));
		assertTrue(room.getGameItems().get(Player.getInstance().getLocation()) != null);
	}

	@Test
	void testRemoveGameItem() {
		courtyard.removeGameItem(courtyard.getLocation(3, 3));
		assertTrue(courtyard.getGameItems().get(courtyard.getLocation(3, 3)) == null);
		courtyard.addGameItem(3, 3, new Item(new Potion()));
		courtyard.removeGameItem(3, 3);
		assertTrue(courtyard.getGameItems().get(courtyard.getLocation(3, 3)) == null);

	}

	@Test
	void testGetGameItemLocation() {
		Potion potion = new Potion();
		courtyard.addGameItem(2, 2, new Item(potion));
		assertTrue(courtyard.getGameItemLocation(new Item(potion)).equals(courtyard.getLocation(2, 2)));
		assertTrue(courtyard.getGameItemLocation(new Item(new Potion())) == null);
	}

	@Test
	void testPlayerMoveRoomEast() {

	}

}
