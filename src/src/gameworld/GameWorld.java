package gameworld;

import gameworld.entities.Player;

/**
 * GameWorld handles all the entities and items.
 */
public class GameWorld {

	private Player player = Player.getInstance();
	private Room room;

	/**
	 * Instantiates a new game world.
	 *
	 */
	public GameWorld() {
		this.room = new Room("Test Room");

	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Room getRoom() {
		return room;
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
