package controller;

import gameworld.GameWorld;
import states.AdventureGame;

/**
 * The Controller class makes it easier for all classes to access the game and the gameworld
 */
public class Controller {
	
	/** The game. */
	private AdventureGame game;
	
	/** The world. */
	private GameWorld world;
	
	/**
	 * Instantiates a new controller.
	 *
	 * @param game the game
	 */
	public Controller(AdventureGame game){
		this.game = game;
	}
	
	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public AdventureGame getGame() {
		return game;
	}

	/**
	 * Sets the game.
	 *
	 * @param game the new game
	 */
	public void setGame(AdventureGame game) {
		this.game = game;
	}

	/**
	 * Gets the game world.
	 *
	 * @return the game world
	 */
	public GameWorld getGameWorld() {
		return world;
	}

	/**
	 * Sets the game world.
	 *
	 * @param world the new game world
	 */
	public void setGameWorld(GameWorld world) {
		this.world = world;
	}
}
