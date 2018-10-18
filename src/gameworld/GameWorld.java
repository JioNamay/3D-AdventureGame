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
 * @author
 * @author alabasdean 300346210
 * @author manaentawe 300428465
 */

public class GameWorld {

	/** The Constant LIBRARY. */
	// Rooms in the GameWorld:
	public static final String LIBRARY = "library";

	/** The Constant STUDY. */
	public static final String STUDY = "study";

	/** The Constant FOYER. */
	public static final String FOYER = "foyer";

	/** The Constant COURTYARD. */
	public static final String COURTYARD = "courtyard";

	/** The courtyard. */
	private	Room courtyard = new Room("Courtyard");

	/** The foyer. */
	private	Room foyer = new Room("Foyer");

	/** The study. */
	private	Room study = new Room("Study");

	/** The library. */
	private Room library = new Room("Library");

	/** The rooms. */
	public Map<String, Room> rooms = new HashMap<String, Room>();	// map to keep track of the rooms in the world

	/**
	 * Instantiates a new game world. Reads rooms from the given XML file and sets
	 * up the world.
	 *
	 * @param file the file
	 */
	public GameWorld(File file) {
		// rooms are created and added to the map
		// parse the file and create the rooms
		// set player in current room if the player is in room
		rooms.put(COURTYARD, courtyard);
		rooms.put(FOYER,foyer);
		rooms.put(STUDY, study);
		rooms.put(LIBRARY, library);
		setUpWorld();
	}

	/**
	 * Sets the up world.
	 */
	private void setUpWorld() {
		Player.getInstance();
		makeCourtyard();
		makeFoyer();
		makeStudy();
		makeLibrary();

		// starting location of the player
		Player.getInstance().setCurrentRoom(courtyard);
		Player.getInstance().setLocation(courtyard.getLocation(5, 3));
	}

	/**
	 * Make courtyard room.
	 */
	public void makeCourtyard() {
		// set up door
		Door door = new Door();
		door.setFirstRoom(courtyard);
		door.setSecondRoom(foyer);
		door.setFirstRoomDirection(Direction.SOUTH);
		door.setSecondRoomDirection(Direction.NORTH);

		// add door to the rooms
		courtyard.addGameItem(0,3, new Item(door));
		foyer.addGameItem(6, 3, new Item(door));

		// add items
		courtyard.addGameItem(1, 1, new Item(new Tree()));
		courtyard.addGameItem(1, 4, new Item(new Rock()));
		courtyard.addGameItem(1, 5, new Item(new Tree()));

		courtyard.addGameItem(3, 3, new Item(new Fountain()));

		courtyard.addGameItem(5, 1, new Item(new Tree()));
		courtyard.addGameItem(5, 5, new Item(new Tree()));

		// add walls
		addWalls(courtyard);
	}


	/**
	 * Make foyer room.
	 */
	public void makeFoyer() {
		// set up doors
		Door door = new Door();
		door.setFirstRoom(foyer);
		door.setSecondRoom(study);
		door.setFirstRoomDirection(Direction.EAST);
		door.setSecondRoomDirection(Direction.WEST);

		// add door to the rooms
		foyer.addGameItem(3, 0, new Item(door));
		study.addGameItem(3, 6, new Item(door));

		// add items
		foyer.addGameItem(3, 3, new Item(new Note()));
		foyer.addGameItem(4, 1, new Item(new Rock()));
		foyer.addGameItem(1, 5, new Item(new Tree()));

		Cactus c = new Cactus();
		c.setDirection(Direction.SOUTH);
		foyer.addGameItem(1, 1, new Item(c));

		c = new Cactus();
		c.setDirection(Direction.WEST);
		foyer.addGameItem(5, 5, new Item(c));

		Sofa s = new Sofa();
		s.setDirection(Direction.SOUTH);
		foyer.addGameItem(1, 3, new Item(s));

		s = new Sofa();
		s.setDirection(Direction.WEST);
		foyer.addGameItem(3, 5, new Item(s));

		// add walls
		addWalls(foyer);
	}

	/**
	 * Make study room.
	 */
	public void makeStudy(){
		// set up doors
		Door door = new Door();
		door.setFirstRoom(study);
		door.setSecondRoom(library);
		door.setFirstRoomDirection(Direction.NORTH);
		door.setSecondRoomDirection(Direction.SOUTH);

		// add door to the rooms
		study.addGameItem(6, 3, new Item(door));
		library.addGameItem(0, 3, new Item(door));

		// add items
		TreasureChest c = new TreasureChest();
		c.setDirection(Direction.EAST);
		study.addGameItem(2, 3 , new Item(c));

		Table t = new Table();
		t.setDirection(Direction.EAST);
		study.addGameItem(3, 3 , new Item(t));

		Sofa s = new Sofa();
		s.setDirection(Direction.EAST);
		study.addGameItem(3, 2, new Item(s));
		study.addGameItem(4, 3, new Item(new Cactus()));

		// add walls
		addWalls(study);
	}

	/**
	 * Make library room.
	 */
	public void makeLibrary(){
		// note: door set up and added in makeStudy()

		// add items
		library.addGameItem(2, 2, new Item(new Cactus()));
		library.addGameItem(2, 4, new Item(new Cactus()));

		Sofa s = new Sofa();
		s.setDirection(Direction.EAST);
		library.addGameItem(3, 2, new Item(s));

		s = new Sofa();
		s.setDirection(Direction.WEST);
		library.addGameItem(3, 4, new Item(s));

		library.addGameItem(5, 1, new Item(new TreasureChest()));
		library.addGameItem(5, 5, new Item(new TreasureChest()));

		library.addGameItem(5, 2, new Item(new Bookshelf()));
		library.addGameItem(5, 3, new Item(new Bookshelf()));
		library.addGameItem(5, 4, new Item(new Bookshelf()));

		// add walls
		addWalls(library);
	}

	/**
	 * Adds the walls.
	 *
	 * @param room the room
	 */
	private void addWalls(Room room) {
		for (int i = 0; i < 7; i++) {
	        for (int j = 0; j < 7; j++) {
	        	if((i == 0 || i == 6) || (j == 0 || j == 6)) {
	        		if(room.getLocation(i, j).isDoor())
	        			continue; // skip as this is a door

	        		room.addGameItem(i, j, new Item(new Wall()));
	        	}

	        }
	    }
	}
}
