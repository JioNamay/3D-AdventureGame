package gameworld;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import controller.Controller;
import gameworld.entities.Player;

/**
 * GameWorld handles all the entities and items.
 */
@XmlRootElement(name = "GameWorld")
public class GameWorld {

	/** The controller. */
	private Controller controller;
	private Player player = Player.getInstance();
	
	private Room currentRoom;
	
	/**
	 * Instantiates a new game world.
	 *
	 */
	public GameWorld() {
		this.currentRoom = new Room("Test Room");
		
		
		//player.setLocation(new Location(4,2));
		
	}
	
	@XmlElement(name = "CurrentRoom")
	public Room getCurrentRoom() {
		return currentRoom;
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
