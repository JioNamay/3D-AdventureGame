package states;

import java.awt.Color;
import java.awt.Graphics;

import application.GUI;
import gameworld.GameWorld;
import gameworld.entities.Player;

/**
 * Handles threads and game state.
 */
public class AdventureGame extends GUI{
	private GameWorld game;			// containing the game logic
	private Player player;			// player within the game

	private String saveFile = "";	// XML filename to store saved game
	private String loadFile;	// XML filename to load game from

	private boolean isSaved = false; // when a new game is made, it's not saved

	/**
	 * Instantiates a new adventure game.
	 */
	public AdventureGame() {
		game = new GameWorld();
		player = Player.getInstance();
		init();
	}

	/**
	 * Private method within the game to initialise the game,
	 * ie. set the player within the gameworld
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
	}

	@Override
	protected void redraw(Graphics g) { // renders the world
		// TEST:
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, DRAWING_SIZE, DRAWING_SIZE);
	}

	/**
	 * Main method to run the AdventureGame.
	 */
	public static void main(String[] args) {
		new AdventureGame();
	}




}
