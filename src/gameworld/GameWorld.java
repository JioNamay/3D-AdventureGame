package gameworld;

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

	private Player player = Player.getInstance();
	private Room currentRoom;

	/**
	 * Instantiates a new game world.
	 * The player starts off in the courtyard.
	 */
	public GameWorld() {
		setUpWorld();	// sets out the layout of the gameworld
		// current room is the courtyard
		// set player in the courtyard
	}
	
	public void setUpWorld() {
		
	}

	/**
	 * Update.
	 */
	public void update() {

	}

	/**
	 * Render.
	 */
	public void render() {

	}

}
