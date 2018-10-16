package adventuregame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import application.GUI;
import application.InventoryDisplay;
import gameworld.GameWorld;
import gameworld.Location;
import gameworld.Location.Direction;
import gameworld.Room;
import gameworld.entities.Inventory;
import gameworld.entities.Key;
import gameworld.entities.PickUpAbleStrategy;
import gameworld.entities.Player;
import gameworld.entities.Potion;
import renderer.Renderer;

/**
 * Handles the functionality of the game between user and game logic.
 *
 * @author Carrie
 */
public class AdventureGame extends GUI {
	// GAME HANDLERS
	private GameWorld game; // containing the game logic
	// private Player player; // player within the game
	private Room currentRoom;
	private PickUpAbleStrategy selectedItem = null;
	private Renderer renderer = new Renderer();

	private File saveFile = new File(""); // XML file to store saved game
	private File loadFile = new File("GameWorld.xml"); // XML filename to load game from

	// APPLICATION HANDLERS
	private boolean isSaved = false; // when a new game is made, it's not saved
	private List<InventoryDisplay> displayAreas;
	private static InventoryDisplay selectedDisplay;

	/**
	 * Instantiates a new adventure game. Reading the rooms from XML file, and
	 * generates a new gameworld.
	 */
	public AdventureGame() {
		// displayAreas = new InventoryDisplay[10];

		// player = Player.getInstance();
		// game = new GameWorld(loadFile);
		onStart();
	}

	/**
	 * Loads a game from the XML file. If a game is currently going on, asks user if
	 * to save before loading a previously saved game
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
		// redraw(drawingArea);
	}

	/**
	 * Saves the current game to the XML file
	 */
	@Override
	protected void saveGame() { // save game to file
		if (isSaved)
			return; // game already saved

		// save game to savefile

		// isSaved = true; // indicate whether was saved
	}

	/**
	 * Starts a new game. If a game is currently going on, asks user if to save
	 * before loading a new game
	 */
	@Override
	protected void onStart() {
		if (game == null) { // no game yet, so nothing to save
			game = new GameWorld(loadFile);
			return;
		}

		// ask user whether to save or not
		// if yes, then savegame

		// run a new game anyways
		game = new GameWorld(loadFile); // load new game from file
		// isSaved = false;
		// redraw(drawingArea);
	}

	/**
	 * Draws the items in player's inventory.
	 */
	@Override
	public void updateInventory() {
		Player player = Player.getInstance();
		displayAreas = new ArrayList<InventoryDisplay>();

		// if (player.getInventory() == null) // nothing to display
		// return;

		// TEST:
		Inventory i = new Inventory();
		for (int index = 0; index < 10; index++)
			i.add(new Potion());
		player.setInventory(i);

		// draws every item in player's inventory
		for (PickUpAbleStrategy item : player.getInventory()) {
			InventoryDisplay inventoryImageComponent = new InventoryDisplay(item) {
				// Repaints the component to display the image of the item.
				@Override
				public void paintComponent(Graphics g) {
					// draw images of the items
					Image img = new ImageIcon(this.getClass().getResource("/test.jpg")).getImage();
					g.drawImage(img, 2, 2, InventoryDisplay.IMAGE_WIDTH - 2, InventoryDisplay.IMAGE_HEIGHT - 2, null);
				}
			};
			inventoryImageComponent
					.setPreferredSize(new Dimension(InventoryDisplay.IMAGE_WIDTH, InventoryDisplay.IMAGE_HEIGHT));
			displayAreas.add(inventoryImageComponent); // ??
			inventory.add(inventoryImageComponent);
		}

		// selects a random item from inventory if nothing is selected
		// if (selectedDisplay == null) {
		// int rand = (int) (Math.random() * displayAreas.size());
		// setSelectedItem(displayAreas.get(rand)); // by default, the selected area is
		// the first item
		//
		// }
	}

	/**
	 * Determines and highlights the selected item (only one selected at a time).
	 *
	 * @param display
	 *            the newly selected component (won't ever be null)
	 */
	public static void setSelectedItem(InventoryDisplay display) {
		selectedDisplay = display;
		selectedDisplay.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		Player.getInstance().setSelectedItem(selectedDisplay.getItem());
	}

	public static InventoryDisplay getSelectedItem() {
		return selectedDisplay;
	}

	/**
	 * Renders the game world to the display area.
	 */
	@Override
	protected void doDraw(Graphics g) {
		renderer.doDraw(g);
	}

	/**
	 * Listens for mouse clicks in the display area.
	 */
	@Override
	protected void doRelease(MouseEvent e) {
		Location l = renderer.doRelease(e);

		if (l == null) {
			return;
		}
	}

	/**
	 * Listens for mouse presses in the display area.
	 */
	@Override
	protected void doPress(MouseEvent e) {
		renderer.doPress(e);
	}

	/**
	 * Listens for mouse drags in the display area.
	 */
	@Override
	protected void doDrag(MouseEvent e) {
		renderer.doDrag(e);
	}

	/**
	 * Listens for mouse scrolls in the display area.
	 */
	@Override
	protected void doScroll(MouseWheelEvent e) {
		renderer.doScroll(e);
	}

	/**
	 * Moves the player in the respective direction of the room, as indicated by the
	 * player. First checks to see if the location is valid for the player to move
	 * into, then moves player into it. If the location is invalid, player doesn't
	 * move.
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
