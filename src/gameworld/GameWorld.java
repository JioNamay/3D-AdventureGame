package gameworld;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import gameworld.entities.Player;

/**
 * GameWorld handles all the entities and items.
 */
@XmlRootElement(name = "GameWorld")
public class GameWorld {


	private Room currentRoom;
	private Player player = Player.getInstance();

	/**
	 * Instantiates a new game world.
	 *
	 */
	public GameWorld() {
		
		this.currentRoom = new Room("Test Room");
		
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
	

}
