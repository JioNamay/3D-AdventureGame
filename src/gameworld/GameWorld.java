package gameworld;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import gameworld.Location.Direction;
import gameworld.entities.Bookshelf;
import gameworld.entities.Cactus;
import gameworld.entities.Door;
import gameworld.entities.Fountain;
import gameworld.entities.Item;
import gameworld.entities.Note;
import gameworld.entities.Player;
import gameworld.entities.Rock;
import gameworld.entities.Sofa;
import gameworld.entities.Table;
import gameworld.entities.TreasureChest;
import gameworld.entities.Tree;
import gameworld.entities.Wall;

/**
 * GameWorld keeps track of all the rooms in the game. Each Room is like a
 * single board on its own, whereas this class represents the overall boards.
 *
 * @author yangcarr
 */

public class GameWorld {
  // Rooms in the GameWorld:
  public static final String LIBRARY = "library";
  public static final String STUDY = "study";
  public static final String FOYER = "foyer";
  public static final String COURTYARD = "courtyard";

  private Room courtyard = new Room("Courtyard");
  private Room foyer = new Room("Foyer");
  private Room study = new Room("Study");
  private Room library = new Room("Library");

  public Map<String, Room> rooms = new HashMap<String, Room>(); // map to keep track of the rooms in
                                                                // the world

  private Room currentRoom;

  /**
   * Instantiates a new game world. Reads rooms from the given XML file and sets
   * up the world.
   */
  public GameWorld(File file) {
    // rooms are created and added to the map
    // parse the file and create the rooms
    // set player in current room if the player is in room
    rooms.put(COURTYARD, courtyard);
    rooms.put(FOYER, foyer);
    rooms.put(STUDY, study);
    rooms.put(LIBRARY, library);
    setUpWorld();

  }

  private void setUpWorld() {
    Player.getInstance();
    makeCourtyard();
    makeFoyer();
    makeStudy();
    makeLibrary();
  }

  /**
   * Initialising the Courtyard and its objects inside it.
   */
  public void makeCourtyard() {
    Room courtyard = new Room("Courtyard");
    rooms.put(COURTYARD, courtyard);
    // add items etc....
    Room foyer = new Room("Foyer");
    rooms.put(FOYER, foyer);

    // set up door
    Door door = new Door();
    door.setFirstRoom(courtyard);
    door.setSecondRoom(foyer);
    door.setFirstRoomDirection(Direction.SOUTH);
    door.setSecondRoomDirection(Direction.NORTH);
    Item item = new Item(door);

    // Add door to current room
    courtyard.addGameItem(0, 3, item);

    // Add gameItems to courtyard

    // add door to both rooms
    courtyard.addGameItem(0, 3, item);
    foyer.addGameItem(6, 3, item);

    // add gameItems to courtyard

    addWalls(courtyard);

    courtyard.addGameItem(1, 1, new Item(new Tree()));
    courtyard.addGameItem(1, 4, new Item(new Rock()));
    courtyard.addGameItem(1, 5, new Item(new Tree()));

    courtyard.addGameItem(3, 3, new Item(new Fountain()));

    courtyard.addGameItem(5, 1, new Item(new Tree()));
    courtyard.addGameItem(5, 5, new Item(new Tree()));

    Player.getInstance().setLocation(courtyard.getLocation(5, 3));
    Player.getInstance().setCurrentRoom(courtyard);
  }

  public void makeFoyer() {
    Room foyer = new Room("Foyer");
    rooms.put(FOYER, foyer);
    // add items etc....
    Room study = new Room("Study");
    rooms.put(STUDY, study);

    Room library = new Room("library");
    rooms.put(LIBRARY, library);

    // set up doors
    Door door1 = new Door();
    door1.setFirstRoom(foyer);
    door1.setSecondRoom(study);
    door1.setFirstRoomDirection(Direction.WEST);
    door1.setSecondRoomDirection(Direction.EAST);

    Item item1 = new Item(door1);

    // Add door to the room
    foyer.addGameItem(0, 3, item1);

    Door door2 = new Door();
    door2.setFirstRoom(foyer);
    door2.setSecondRoom(library);
    door2.setFirstRoomDirection(Direction.EAST);
    door2.setSecondRoomDirection(Direction.WEST);

    Item item2 = new Item(door2);

    // Add door to the room
    foyer.addGameItem(6, 3, item2);

    Door door3 = new Door();
    door3.setFirstRoom(foyer);
    door3.setSecondRoom(courtyard);
    door3.setFirstRoomDirection(Direction.SOUTH);
    door3.setSecondRoomDirection(Direction.NORTH);
    Item item3 = new Item(door3);

    // Add door to the room
    foyer.addGameItem(3, 6, item3);

    // Add Walls
    addWalls(foyer);

    // Add Game Items
    foyer.addGameItem(5, 5, new Item(new Note()));

  }

  public void makeStudy() {
    Door door1 = new Door();
    door1.setFirstRoom(study);
    door1.setSecondRoom(foyer);
    door1.setFirstRoomDirection(Direction.WEST);
    door1.setSecondRoomDirection(Direction.EAST);
    Item item1 = new Item(door1);

    // Add Walls
    addWalls(study);
    // Add door to the room
    study.addGameItem(6, 3, item1);

    // Add Game items
    TreasureChest c = new TreasureChest();
    c.setDirection(Direction.EAST);
    study.addGameItem(2, 2, new Item(c));

    Table t = new Table();
    t.setDirection(Direction.SOUTH);
    study.addGameItem(2, 3, new Item(c));
    study.addGameItem(2, 4, new Item(new Cactus()));

  }

  public void makeLibrary() {

    Door door1 = new Door();
    door1.setFirstRoom(library);
    door1.setSecondRoom(foyer);
    door1.setFirstRoomDirection(Direction.WEST);
    door1.setSecondRoomDirection(Direction.EAST);
    Item item1 = new Item(door1);

    // Add Walls
    addWalls(library);

    // Add Game items
    TreasureChest c = new TreasureChest();
    c.setDirection(Direction.WEST);
    study.addGameItem(5, 1, new Item(c));

    study.addGameItem(2, 2, new Item(new Cactus()));

    Sofa s = new Sofa();
    s.setDirection(Direction.SOUTH);
    study.addGameItem(3, 3, new Item(s));

    Bookshelf b = new Bookshelf();
    c.setDirection(Direction.WEST);
    study.addGameItem(5, 2, new Item(c));

    Bookshelf b1 = new Bookshelf();
    c.setDirection(Direction.WEST);
    study.addGameItem(5, 3, new Item(b1));

    study.addGameItem(2, 4, new Item(new Cactus()));

    Sofa s1 = new Sofa();
    s.setDirection(Direction.SOUTH);
    study.addGameItem(3, 4, new Item(s));

    Bookshelf b2 = new Bookshelf();
    c.setDirection(Direction.WEST);
    study.addGameItem(5, 4, new Item(b2));

    TreasureChest c1 = new TreasureChest();
    c.setDirection(Direction.WEST);
    study.addGameItem(5, 5, new Item(c1));

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

  /**
   * Update.
   */
  public void update() {

  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

}
