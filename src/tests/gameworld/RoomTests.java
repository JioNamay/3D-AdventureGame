package tests.gameworld;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameworld.Location;
import gameworld.Location.Direction;
import gameworld.Room;
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
  Room eastRoom;

  @BeforeEach
  void setUp() throws Exception {
    Player.getInstance().resetPlayer();
    courtyard = new Room("Courtyard");
    // add items etc....
    foyer = new Room("Test");
    foyer.setName("Foyer");
    assertEquals(foyer.getName(), "Foyer", "Expected name foyer but was not");
    eastRoom = new Room("East");

    // set up door between courtyard and foyer
    Door door = new Door();
    door.setFirstRoom(courtyard);
    door.setSecondRoom(foyer);
    door.setFirstRoomDirection(Direction.SOUTH);
    door.setSecondRoomDirection(Direction.NORTH);
    Item item = new Item(door);
    // add door to both rooms
    courtyard.addGameItem(0, 3, item);
    foyer.addGameItem(6, 3, item);

    // set up door between courtyard and eastRoom
    Door eastDoor = new Door();
    eastDoor.setFirstRoom(courtyard);
    eastDoor.setSecondRoom(eastRoom);
    eastDoor.setFirstRoomDirection(Direction.WEST);
    eastDoor.setSecondRoomDirection(Direction.EAST);
    Item eastItem = new Item(eastDoor);
    // add door to both rooms
    courtyard.addGameItem(3, 6, eastItem);
    eastRoom.addGameItem(3, 0, eastItem);

    // add gameItems to courtyard
    addWalls(courtyard);
    addWalls(foyer);
    // addWalls(eastRoom);
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
    Player.getInstance().setLocation(room.getLocation(4, 5)); // move next to east wall
    assertFalse(room.movePlayer(Direction.EAST));
    Player.getInstance().setLocation(room.getLocation(1, 5));
    assertFalse(room.movePlayer(Direction.NORTH));
    assertTrue(room.movePlayer(Direction.SOUTH));

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
  void testPlayerCanMoveRoomsNorthSouth() {
    // set player's location to be one off the door
    Room currentRoom = Player.getInstance().getCurrentRoom();
    Location nearDoor = currentRoom.getLocation(1, 3);
    Player.getInstance().setLocation(nearDoor);

    // unlock the door
    Key key = new Key();
    key.performAction(Action.PICKUP);

    Location doorLoc = currentRoom.getLocation(0, 3);
    Door door = (Door) currentRoom.getGameItems().get(doorLoc).getItem();
    assertTrue(door.isLocked());
    assertTrue(door.performAction(Action.UNLOCK).equals("Player unlocked Door"));

    assertFalse(door.isLocked());
    currentRoom.movePlayer(Direction.NORTH); // move to door location
    assertFalse(currentRoom.movePlayer(Direction.NORTH)); // should be false because door is still
                                                          // closed
    door.performAction(Action.OPEN);

    // move to room north

    assertTrue(currentRoom.movePlayer(Direction.NORTH)); // move rooms
    assertEquals(Player.getInstance().getCurrentRoom(), foyer,
        "Player expected to have moved rooms but did not");
    assertTrue(Player.getInstance().getLocation().equals(foyer.getLocation(6, 3)));
    assertFalse(courtyard.hasPlayer());
    assertTrue(foyer.hasPlayer());

    // move to room south
    assertTrue(Player.getInstance().getLocation().isDoor());
    assertTrue(foyer.movePlayer(Direction.SOUTH)); // move rooms
    assertEquals(Player.getInstance().getCurrentRoom(), courtyard,
        "Player expected to have moved rooms but did not");
    assertTrue(Player.getInstance().getLocation().equals(courtyard.getLocation(0, 3)));
    assertTrue(courtyard.hasPlayer());
    assertFalse(foyer.hasPlayer());

  }

  @Test
  void testPlayerMoveRoomEastWest() {
    Room currentRoom = Player.getInstance().getCurrentRoom();
    // Get key
    Key key = new Key();
    key.performAction(Action.PICKUP);
    // move to door
    currentRoom.movePlayer(Direction.EAST);
    currentRoom.movePlayer(Direction.EAST);
    currentRoom.movePlayer(Direction.NORTH);
    currentRoom.movePlayer(Direction.NORTH);
    currentRoom.movePlayer(Direction.EAST);
    // make sure player cant get through locked door
    assertFalse(currentRoom.movePlayer(Direction.EAST));
    Door door = (Door) currentRoom.getGameItems().get(Player.getInstance().getLocation()).getItem();
    door.performAction(Action.UNLOCK);
    assertFalse(currentRoom.movePlayer(Direction.EAST)); // still closed
    door.performAction(Action.OPEN);
    assertTrue(door.isOpen(), "Door should be opened");
    // move to room east
    assertTrue(currentRoom.movePlayer(Direction.EAST)); // move rooms
    assertEquals(Player.getInstance().getCurrentRoom(), eastRoom,
        "Player expected to have moved rooms but did not");
    assertTrue(Player.getInstance().getLocation().equals(eastRoom.getLocation(3, 0)));
    assertTrue(eastRoom.hasPlayer());
    assertFalse(courtyard.hasPlayer());

    // move to room west
    assertTrue(eastRoom.movePlayer(Direction.WEST)); // move rooms
    assertEquals(Player.getInstance().getCurrentRoom(), courtyard,
        "Player expected to have moved rooms but did not");
    assertTrue(Player.getInstance().getLocation().equals(courtyard.getLocation(3, 6)));
    assertTrue(courtyard.hasPlayer());
    assertFalse(eastRoom.hasPlayer());
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
    room.getGameItems().get(Player.getInstance().getLocation()).getItem()
        .performAction(Action.PICKUP);
    assertTrue(Player.getInstance().getInventory().iterator().hasNext());
    assertTrue(room.getGameItems().get(Player.getInstance().getLocation()) == null);
  }

  @Test
  void testPlayerInvalidDrop() {
    Room room = Player.getInstance().getCurrentRoom();
    room.addGameItem(5, 3, new Item(new Potion()));
    Inventory in = Player.getInstance().getInventory();
    in.add(new Key());
    assertTrue(in.iterator().next().performAction(Action.DROP)
        .equals("Player cannot drop Key location is occupied."));
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
}
