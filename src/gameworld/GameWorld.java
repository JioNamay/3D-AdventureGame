package gameworld;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import gameworld.entities.Player;

/**
 * GameWorld handles all the entities and items.
 */
public class GameWorld {
	// Rooms in the GameWorld:
	public static final String LIBRARY = "library";
	public static final String GUEST_BEDROOM = "Guest Bedroom";
	public static final String FOYER = "Foyer";
	public static final String COURTYARD = "Courtyard";
	public static final String MASTER_BEDROOM = "Master Bedroom";
	public static final String KITCHEN = "Kitchen";
	
	//public Map<String, Room> rooms;	// map to keep track of the rooms in the world??

	private Player player = Player.getInstance();
	private Room currentRoom;

	/**
	 * Instantiates a new game world.
	 * Reads rooms from the given XML file and sets up the world.
	 */
	public GameWorld(File file) {
		// parse the file and create the rooms
		// set player in current room if the player is in room
		
	}
	
	private void setUpWorld() {
	}

	/**
	 * Update.
	 */
	public void update() {

	}

}
