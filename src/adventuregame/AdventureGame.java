package adventuregame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import application.GUI;
import application.InventoryDisplay;
import gameworld.GameWorld;
import gameworld.Location;
import gameworld.Location.Direction;
import gameworld.Room;
import gameworld.entities.Item;
import gameworld.entities.PickUpAbleStrategy;
import gameworld.entities.Player;
import gameworld.entities.Potion;
import gameworld.entities.Strategy;
import gameworld.entities.Item.Action;
import renderer.Renderer;

/**
 * Handles the functionality of the game between user and game logic.
 *
 * @author yangcarr
 */
public class AdventureGame extends GUI {
	// GAME HANDLERS

	// private Player player; // player within the game
	private PickUpAbleStrategy selectedItem = null;
	private Renderer renderer = new Renderer();

	private GameWorld game; // containing the game logic
	private Room currentRoom = null;
	private Location.Direction dir;

	// APPLICATION HANDLERS
	private File saveFile = new File(""); // XML file to store saved game
	private File loadFile = new File("GameWorld.xml"); // XML filename to load game from

	private boolean isSaved = false; // when a new game is made, it's not saved
	private List<InventoryDisplay> displayAreas;
	private static InventoryDisplay selectedDisplay;
	private JFrame frame;

	/**
	 * Instantiates a new adventure game. Reading the rooms from XML file, and
	 * generates a new gameworld.
	 */
	public AdventureGame() {
		onStart();
		frame = this;
		game = new GameWorld(loadFile);
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
		updateInventory();
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
		game = new GameWorld(loadFile);	// load new game from file
		//player.resetPlayer();	// resets the player
		// isSaved = false;
		// redraw(drawingArea);
	}

	/**
	 * Asks user whether to save the current game or not
	 * @return user's response
	 */
	protected String askSave() {
		if (isSaved)
			return "";

		int ans = JOptionPane.showConfirmDialog(container, "Would you like to save your game before you leave?",
				null, JOptionPane.YES_NO_OPTION);
		if (ans == JOptionPane.YES_OPTION)
			return "YES";

		return "NO";
	}

	/**
	 * Draws the items in player's inventory.
	 */
	public void updateInventory() {
		if (player.getInventory() == null)
			return;

		displayAreas = new ArrayList<InventoryDisplay>();

		// draws every item in player's inventory
		for (PickUpAbleStrategy item : player.getInventory()) {
			InventoryDisplay inventoryImageComponent = new InventoryDisplay(item) {
				@Override
				public void paintComponent(Graphics g) {	// draw images of the items
					String url = "src/renderer/data/";

					String name = item.getName().toLowerCase();
 
					if (name.equals("potion") || name.equals("rock") || name.equals("tree") || name.equals("wall")) {
						url += "else/";
					} else {
						url += "south/";
					}

					url += name;
					url += ".png";

					BufferedImage image = null;
					try {
						image = ImageIO.read(new File(url));
					} catch (IOException e) {
						e.printStackTrace();
					}

					if (image != null) {
						Image scaledImage = image.getScaledInstance((int) InventoryDisplay.IMAGE_WIDTH - 2, (int) InventoryDisplay.IMAGE_HEIGHT - 2, Image.SCALE_SMOOTH);
						g.drawImage(scaledImage, 2, 2, InventoryDisplay.IMAGE_WIDTH - 2, InventoryDisplay.IMAGE_HEIGHT - 2, null);
					}
				}
			};
			inventoryImageComponent
					.setPreferredSize(new Dimension(InventoryDisplay.IMAGE_WIDTH, InventoryDisplay.IMAGE_HEIGHT));
			displayAreas.add(inventoryImageComponent); // ??
			inventoryImageComponent.setPreferredSize(new Dimension(InventoryDisplay.IMAGE_WIDTH, InventoryDisplay.IMAGE_HEIGHT));
			displayAreas.add(inventoryImageComponent);
			inventoryContainer.add(inventoryImageComponent);
			//System.out.println("adding item. size is = " + displayAreas.size());
		}

		//System.out.println("no. of displays: " + displayAreas.size());

		// selects a random item from inventory if nothing is selected
		if (selectedDisplay == null) {
			int rand = (int) (Math.random() * displayAreas.size());
			setSelectedItem(displayAreas.get(rand));
		}
	}

	/**
	 * Determines and highlights the selected item (only one selected at a time).
	 * The selected item in the inventory is passed on to player.
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
	protected void doDraw(Graphics g, Room r) {
		renderer.doDraw(g, r);
	}

	/**
	 * Listens for mouse clicks in the display area.
	 */
	@Override
	protected void doRelease(MouseEvent e) {
		Location clickedLocation = renderer.doRelease(e);
		
		if (clickedLocation == null) {
		  return;
		}

		currentRoom = player.getCurrentRoom();
		if (!currentRoom.getGameItems().containsKey(currentRoom.getLocation(clickedLocation.getRow(), clickedLocation.getCol()))) {
			return;
		}

		Item item = currentRoom.getGameItems().get(currentRoom.getLocation(clickedLocation.getRow(), clickedLocation.getCol()));

		// player clicked an area that doesn't contain an item
		if (item == null || item.getName().toLowerCase().equals("wall")) {
			return;
		}

		JPopupMenu actionMenu = new JPopupMenu();
		List<String> itemActions = item.getActions();
		// go through the possible actions for the selected item
		for (String actions: itemActions) {
			JMenuItem action = new JMenuItem(actions);
			action.addActionListener(createItemListener(action.getText(), item.getItem()));
			actionMenu.add(action);
		}

		actionMenu.show(e.getComponent(), e.getX(), e.getY());
	}

	/**
	 * The action to be performed on this item in the room.
	 *
	 * @param action action to perform on item
	 * @param item item user is interacting with
	 * @return the ActionListener set on the menu item
	 */
	private ActionListener createItemListener(String action, Strategy item) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (action.equals(Action.THROWCOINS.toString())) {
					String s = JOptionPane.showInputDialog("How many coins do you want to throw?: ");
					int fountainCoins = Integer.parseInt(s);	// throws exception if not possible
					if(Player.getInstance().getCoins() < fountainCoins) {
					  JOptionPane.showMessageDialog( frame,
					      "Player does not own the inputted amount of coins.",
					      "Not enough coins",
					      JOptionPane.ERROR_MESSAGE);
					  
					  return;
					}
					player.addFountainCoins(fountainCoins);
				}
				String desc = item.performAction(Action.valueOf(action));
				GUI.getActionDisplay().setText(desc);
				GUI.getPlayerStatDisplay().setText(player.toString());
			}
		};
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
	 * move. master
	 */
	@Override
	protected void navigatePlayer(Direction dir) {
		player.getCurrentRoom().movePlayer(dir);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			dir = Location.Direction.NORTH;
			break;
		case KeyEvent.VK_DOWN:
			dir = Location.Direction.SOUTH;
			break;
		case KeyEvent.VK_LEFT:
			dir = Location.Direction.WEST;
			break;
		case KeyEvent.VK_RIGHT:
			dir = Location.Direction.EAST;
			break;
		default:
		//	JOptionPane.showMessageDialog(this, "Not a valid direction for player");
			return;
		}
	}

	/**
	 * Takes user input from keyboard and moves player in the specified direction.
	 * Note that it only moves the player one location at a time.
	 * Moves player in the specified direction. Note that it only moves the player
	 * one location at a time.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("player will be moving in direction: " + dir.toString());	// test
		navigatePlayer(dir);
	}

	/**
	 * Main method to run the AdventureGame.
	 */
	public static void main(String[] args) {
		new AdventureGame();
	}



}
