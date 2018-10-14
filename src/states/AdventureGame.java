package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import application.GUI;
import application.InventoryDisplay;
import gameworld.GameWorld;
import gameworld.Location.Direction;
import gameworld.Room;
import gameworld.entities.Inventory;
import gameworld.entities.Key;
import gameworld.entities.PickUpAbleStrategy;
import gameworld.entities.Player;
import gameworld.entities.Potion;

/**
 * Handles the functionality of the game between user and game logic.
 * 
 * @author Carrie
 */
public class AdventureGame extends GUI{
	private GameWorld game;			// containing the game logic
	private Player player = Player.getInstance();			// player within the game
	private Room currentRoom;
	
	private File saveFile = new File("");	// XML file to store saved game
	private File loadFile = new File("");	// XML filename to load game from

	private boolean isSaved = false; // when a new game is made, it's not saved
 
	/**
	 * Instantiates a new adventure game.
	 * Reading the rooms from XML file, and generates a new gameworld.
	 */
	public AdventureGame() {
		//game = new GameWorld(loadFile);
		onStart();
	}

	/**
	 * Loads a game from the XML file.
	 * If a game is currently going on, asks user if to save before
	 * loading a previously saved game
	 */
	@Override
	protected void loadGame() {
		if (game == null) {
			game = new GameWorld(loadFile);
			return;
		}

		// if is not saved, 
		// ask user whether to save or not
		// if yes, then loadfile = savefile
		// and save current game to savefile

		// load game from loadfile
		game = new GameWorld(loadFile);
		// isSaved = false;
		//redraw(drawingArea);
	}

	/**
	 * Saves the current game to the XML file
	 */
	@Override
	protected void saveGame() { // save game to file
		if (isSaved)
			return;	// game already saved

		// save game to savefile

		// isSaved = true;	// indicate whether was saved
	}

	/**
	 * Starts a new game.
	 * If a game is currently going on, asks user if to save before
	 * loading a new game
	 */
	@Override
	protected void onStart() {
		if (game == null) {	// no game yet, so nothing to save
			game = new GameWorld(loadFile);
			return;
		}

		// ask user whether to save or not
		// if yes, then savegame

		game = new GameWorld(loadFile);	// load new game from file
		// isSaved = false;
		//redraw(drawingArea);
	}

	/**
	 * Draws the items in player's inventory.
	 */
	@Override
	public void updateInventory() {
		if (player == null) {
			System.out.println("player is null");	// test
			return;
		}


		// go through player's inventory
		// make an Inventory display
		// add to inventory

		// TEST:
		Inventory i = new Inventory();
		for (int index=0; index<10; index++)
			i.add(new Potion());
		player.setInventory(i);

		for (PickUpAbleStrategy item: player.getInventory()) {
			InventoryDisplay inventoryImageComponent = new InventoryDisplay() {
				// Repaints the component to display the image of the item.
				@Override
				public void paintComponent(Graphics g) {
					// draw images of the items
					Image img = new ImageIcon(this.getClass().getResource("/test.jpg")).getImage();
					g.drawImage(img, 0, 0, InventoryDisplay.IMAGE_WIDTH-5, InventoryDisplay.IMAGE_HEIGHT-5, null);

				}
			};
			inventoryImageComponent.setPreferredSize(new Dimension(InventoryDisplay.IMAGE_WIDTH, InventoryDisplay.IMAGE_HEIGHT));
			inventory.add(inventoryImageComponent);

			System.out.println(item.getDescription());
		}
	}

	/**
	 * Renders the game world to the display area.
	 */
	@Override
	protected void redraw(Graphics g) { // renders the world
		// TEST:
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, DRAWING_SIZE, DRAWING_SIZE);

		// gameworld.getroom
		// render the room's items plus player if needed
		// Draw.redraw(g, player.getCurrentRoom(), player);
	}
	
	/**
	 * Moves the player in the respective direction of the room, as indicated by the player.
	 * First checks to see if the location is valid for the player to move into, then 
	 * moves player into it. If the location is invalid, player doesn't move.
	 */
	@Override
	protected void navigatePlayer(Direction dir) {
		currentRoom.movePlayer(dir);
	}


	/**
	 * Main method to run the AdventureGame.
	 */
	public static void main(String[] args) {
		new AdventureGame();
	}
}
