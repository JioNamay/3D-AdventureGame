package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import application.GUI;
import application.InventoryDisplay;
import gameworld.GameWorld;
import gameworld.entities.Inventory;
import gameworld.entities.Key;
import gameworld.entities.PickUpAbleStrategy;
import gameworld.entities.Player;
import gameworld.entities.Potion;

/**
 * Handles threads and game state.
 */
public class AdventureGame extends GUI{
	private GameWorld game;			// containing the game logic
	private Player player = Player.getInstance();			// player within the game

	private String saveFile = "";	// XML filename to store saved game
	private String loadFile = "";	// XML filename to load game from

	private boolean isSaved = false; // when a new game is made, it's not saved
	//private InventoryDisplay[] inventoryItems = new InventoryDisplay[10];
 
	/**
	 * Instantiates a new adventure game.
	 */
	public AdventureGame() {
		game = new GameWorld();
		init();
	}

	/**
	 * Initialise the game.
	 */
	private void init() {

	}

	/**
	 * Loads a game from the XML file.
	 * If a game is currently going on, asks user if to save before
	 * loading a previously saved game
	 */
	@Override
	protected void loadGame() {
		if (game == null) {
			game = new GameWorld();
			return;
		}

		// ask user whether to save or not
		// if yes, then loadfile = save file
		// and save current game

		// load game from loadfile
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

		// save game to file

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
			game = new GameWorld();	// game starting state?
			return;
		}

		// ask user whether to save or not
		// if yes, then save current game

		game = new GameWorld();	// load new game
		// isSaved = false;
		//redraw(drawingArea);
	}

	/**
	 * Draws the items in player's inventory.
	 */
	@Override
	public void updateInventory() {
//		if (player == null) {
//			System.out.println("player is null");
//			return;
//		}


		// go through player's inventory
		// make an Inventory display
		// add to inventory

		// TEST:
		Inventory i = new Inventory();
		for (int index=0; index<10; index++)
			i.add(new Potion());

		for (PickUpAbleStrategy item: i) {
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
		// Draw.redraw(g, game.getRoom(), player.getCurrentRoom());
	}


	/**
	 * Main method to run the AdventureGame.
	 */
	public static void main(String[] args) {
		new AdventureGame();
	}

}
