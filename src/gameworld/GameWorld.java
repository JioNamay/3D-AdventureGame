package gameworld;

import gameworld.entities.Player;

/**
 * GameWorld handles all the entities and items.
 */
public class GameWorld {

	private Player player = Player.getInstance();

	/**
	 * Instantiates a new game world.
	 *
	 */
	public GameWorld() {
		Room currentRoom = new Room("Test Room");

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
