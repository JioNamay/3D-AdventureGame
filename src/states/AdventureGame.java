package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	// GAME HANDLERS
	private GameWorld game;			// containing the game logic
	//private Player player;			// player within the game
	private Room currentRoom;
	private PickUpAbleStrategy selectedItem = null;
	
	private File saveFile = new File("");	// XML file to store saved game
	private File loadFile = new File("GameWorld.xml");	// XML filename to load game from

	// APPLICATION HANDLERS
	private boolean isSaved = false; // when a new game is made, it's not saved
	// holds an array of jcomponents in which inventory items are displayed
	//private InventoryDisplay displayAreas[] = new InventoryDisplay[10];
	private List<InventoryDisplay> displayAreas;
	// private List<JPanel> displayAreas;
 
	/**
	 * Instantiates a new adventure game.
	 * Reading the rooms from XML file, and generates a new gameworld.
	 */
	public AdventureGame() {
		//displayAreas = new InventoryDisplay[10];
		
		//player = Player.getInstance();
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
		
		// run a new game anyways
		game = new GameWorld(loadFile);	// load new game from file
		// isSaved = false;
		//redraw(drawingArea);
	}

	/**
	 * Draws the items in player's inventory.
	 */
	@Override
	public void updateInventory() {
	//public void updateInventory(MouseEvent e) {
		Player player = Player.getInstance();
		displayAreas = new ArrayList<InventoryDisplay>();
		//displayAreas = new ArrayList<JPanel>();


		// go through player's inventory
		// make an Inventory display
		// add to inventory

		// TEST:
		Inventory i = new Inventory();
		for (int index=0; index<10; index++)
			i.add(new Potion());
		player.setInventory(i);

		for (PickUpAbleStrategy item: player.getInventory()) {
			JPanel holder = new JPanel();
			InventoryDisplay inventoryImageComponent = new InventoryDisplay(item) {
				// Repaints the component to display the image of the item.
				@Override
				public void paintComponent(Graphics g) {
					// draw images of the items
					Image img = new ImageIcon(this.getClass().getResource("/test.jpg")).getImage();
					g.drawImage(img, 2, 2, InventoryDisplay.IMAGE_WIDTH-2, InventoryDisplay.IMAGE_HEIGHT-2, null);
					
				}
			};
			inventoryImageComponent.setPreferredSize(new Dimension(InventoryDisplay.IMAGE_WIDTH, InventoryDisplay.IMAGE_HEIGHT));
			displayAreas.add(inventoryImageComponent);
			//holder.add(inventoryImageComponent);		// storing jcomponent in jpanel
			//displayAreas.add(holder);
			
			inventory.add(inventoryImageComponent);
			//inventory.add(holder);

			System.out.println(item.getDescription());
		}
		
		System.out.println("no. of drawing areas: " + displayAreas.size());
		
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

	@Override
	public void mouseClicked(MouseEvent e) {
		/*if (displayAreas.isEmpty())
			return;*/
		System.out.println("mouse clicked: x = " + e.getX() + ", y = " + e.getY());
		
		for (InventoryDisplay display: displayAreas) {
		//for (JPanel display: displayAreas) {
			if (display.contains(e.getX(), e.getY()))
				//display.setBackground(Color.GREEN);
				//display.highlight();
				System.out.println("inventory item: " + display.getItem().getDescription());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Main method to run the AdventureGame.
	 */
	public static void main(String[] args) {
		new AdventureGame();
	}

	

}
