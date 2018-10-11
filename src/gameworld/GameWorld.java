package gameworld;

import controller.Controller;
import gameworld.entities.Player;

/**
 * GameWorld handles all the entities and items.
 */
public class GameWorld {
	
	/** The controller. */
	private Controller controller;
	private Player player = Player.getInstance();
	
	/**
	 * Instantiates a new game world.
	 *
	 */
	public GameWorld() {
		Room currentRoom = new Room("Test Room");
		
		player.setLocation(new Location(4,2));
		
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
	
	/**
	 * Load game world.
	 *
	 * @param path the path
	 */
	private void loadGameWorld(String path){
		
	}
	
	/**
	 * Save game world.
	 */
	private void SaveGameWorld(){
		// should return something. Maybe a file? or maybe not return something.
		// up to you bennette :)
	}
	
	/**
	 * Gets the controller.
	 *
	 * @return the controller
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}
}
