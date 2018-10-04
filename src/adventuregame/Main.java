package adventuregame;

import states.AdventureGame;

/**
 * Launches the Adventure Game.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		AdventureGame game = new AdventureGame();
		game.start(); // starts the game thread.
	}

}
