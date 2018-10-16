package gameworld;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import gameworld.Location.Direction;
import gameworld.entities.Door;
import gameworld.entities.Fountain;
import gameworld.entities.Item;
import gameworld.entities.Player;
import gameworld.entities.Rock;
import gameworld.entities.Tree;
import gameworld.entities.Wall;


/**
 * GameWorld keeps track of all the rooms in the game.
 * Each Room is like a single board on its own, whereas this class
 * represents the overall boards.
 *
 * @author yangcarr
 */

public class GameWorld {
	// Rooms in the GameWorld:
	public static final String LIBRARY = "library";
	public static final String STUDY = "study";
	public static final String FOYER = "foyer";
	public static final String COURTYARD = "courtyard";
	
	public Map<String, Room> rooms = new HashMap<String, Room>();	// map to keep track of the rooms in the world
	
	private Room currentRoom;

	/**
	 * Instantiates a new game world.
	 * Reads rooms from the given XML file and sets up the world.
	 */
	public GameWorld(File file) {
		// rooms are created and added to the map
		// parse the file and create the rooms
		// set player in current room if the player is in room
		//currentRoom = null;
	  setUpWorld();
	}

	private void setUpWorld() {
	  Player.getInstance();
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
		// add door to both rooms
		courtyard.addGameItem(0,3, item);
		foyer.addGameItem(6, 3, item);
		
		// add gameItems to courtyard
		addWalls(courtyard);
		addWalls(courtyard);

		courtyard.addGameItem(1, 2, new Item(new Tree()));
		courtyard.addGameItem(1, 2, new Item(new Rock()));
		courtyard.addGameItem(1, 5, new Item(new Tree()));
		
		courtyard.addGameItem(3, 3, new Item(new Fountain()));
		
		courtyard.addGameItem(5, 2, new Item(new Tree()));
		courtyard.addGameItem(5, 5, new Item(new Tree()));
		
		
		Player.getInstance().setLocation(courtyard.getLocation(5, 3));
		Player.getInstance().setCurrentRoom(courtyard);	
		
	}
	
	/*
	 * Initialising the Courtyard and its objects 
	 * inside it.
	 */
		
	public void makeCourtyard(){
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
		// add door to both rooms
		courtyard.addGameItem(0,3, item);
		foyer.addGameItem(6, 3, item);
		
		// add gameItems to courtyard
		addWalls(courtyard);
		addWalls(courtyard);

		courtyard.addGameItem(1, 2, new Item(new Tree()));
		courtyard.addGameItem(1, 2, new Item(new Rock()));
		courtyard.addGameItem(1, 5, new Item(new Tree()));
		
		courtyard.addGameItem(3, 3, new Item(new Fountain()));
		
		courtyard.addGameItem(5, 2, new Item(new Tree()));
		courtyard.addGameItem(5, 5, new Item(new Tree()));
		
		
		Player.getInstance().setLocation(courtyard.getLocation(5, 3));
		Player.getInstance().setCurrentRoom(courtyard);	
		
	}
	
	public void makeFoyer(){
		
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
		Item item = new Item(door1);
		
		Door door2 = new Door();
		door2.setFirstRoom(foyer);
		door2.setSecondRoom(library);
		door2.setFirstRoomDirection(Direction.EAST);
		door2.setSecondRoomDirection(Direction.WEST);
		
		Door door3 = new Door();
		door3.setFirstRoom(foyer);
		//door3.setSecondRoom(courtyard);
		door3.setFirstRoomDirection(Direction.SOUTH);
		door3.setSecondRoomDirection(Direction.EAST);
		
		// add door to both rooms
		foyer.addGameItem(0,3, item);
		study.addGameItem(6, 3, item);
		
		// add gameItems to foyer
		addWalls(foyer);
		addWalls(foyer);
		
	}

	
	
	
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

	/**
	 * Update.
	 */
	public void update() {
	}

	public Room getCurrentRoom() { return currentRoom; }
}
