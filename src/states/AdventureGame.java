package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import application.GUI;
import application.InventoryDisplay;
import gameworld.GameWorld;
import gameworld.entities.Player;

/**
 * Handles threads and game state.
 */
public class AdventureGame extends GUI{
	private GameWorld game;			// containing the game logic
	private Player player;			// player within the game

	private String saveFile = "";	// XML filename to store saved game
	private String loadFile = "";	// XML filename to load game from

	private boolean isSaved = false; // when a new game is made, it's not saved
	//private InventoryDisplay[] inventoryItems = new InventoryDisplay[10];

	/**
	 * Instantiates a new adventure game.
	 */
	public AdventureGame() {
		game = new GameWorld();
		player = Player.getInstance();
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
	protected void updateInventory(JPanel inventory) {
		if (player == null)
			return;

		// go through player's inventory
		// make an Inventory display
		// add to inventory
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
	}


	/**
	 * Main method to run the AdventureGame.
	 */
	public static void main(String[] args) {
		new AdventureGame();
	}

}
