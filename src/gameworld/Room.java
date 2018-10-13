package gameworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameworld.Location.Direction;
import gameworld.entities.*;

public class Room {

	public static final int SIZE = 7;
	Location[][] locations = new Location[SIZE][SIZE];
	Map<Location, Item> gameItems = new HashMap<Location, Item>();
	boolean hasPlayer;
	List<Door> doors = new ArrayList<Door>();
	String name;

	public Room(String name) {
		this.name = name;
		initialiseLocations();
	}

	private void initialiseLocations() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				locations[row][col] = new Location(row, col);
			}
		}
	} 

	/**
	 * @return the locations
	 */
	public Location[][] getLocations() {
		return locations;
	}

	/**
	 * @return the gameObjects
	 */
	public Map<Location, Item> getGameObjects() {
		return gameItems;
	}

	/**
	 * @param gameObjects
	 *            the gameObjects to set
	 */
	public void setGameObjects(Map<Location, Item> gameObjects) {
		this.gameItems = gameObjects;
		initialiseLocationSolidity();
	}

	private void initialiseLocationSolidity() {
		for (Map.Entry<Location, Item> entry : gameItems.entrySet()) {
			if(entry.getValue().isSolid()) entry.getKey().setSolid(true);
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the hasPlayer
	 */
	public boolean hasPlayer() {
		return hasPlayer;
	}

	/**
	 * @param hasPlayer the hasPlayer to set
	 */
	public void setHasPlayer(boolean hasPlayer) {
		this.hasPlayer = hasPlayer;
	}

	public void addGameObject(Location loc, Item e) {
		this.gameItems.put(loc, e);
		if(e.isSolid()) loc.setSolid(true);
	}

	public String playerDropGameObject(PickUpAbleStrategy e) {
		// check if there is already an item (besides player) on player location
		Item playerLocItem = gameItems.get(Player.getInstance().getLocation());
		if (playerLocItem == null) {
			Player.getInstance().getInventory().remove(e);
			gameItems.put(Player.getInstance().getLocation(), new Item(e));
			return "Player dropped " + e.getName();
		}
		else return "Player cannot drop " + e.getName() + " location is occupied.";
		
	}

	public void removeGameObject(Location loc) {
		this.gameItems.remove(loc);
	}
	
	public void removeGameObject(PickUpAbleStrategy e) {
		Location loc = null;
		for (Map.Entry<Location, Item> entry : gameItems.entrySet()) {
			if(entry.getValue().getItem().equals(e)) loc = entry.getKey();
		}
		this.gameItems.remove(loc);
	}

	/**
	 * @return the doors
	 */
	public List<Door> getDoors() {
		return doors;
	}

	/**
	 * @param doors
	 *            the doors to set
	 */
	public void setDoors(List<Door> doors) {
		this.doors = doors;
	}
	
	public boolean movePlayer(Direction dir) {
		int playerLocRow = Player.getInstance().getLocation().getRow();
		int playerLocCol = Player.getInstance().getLocation().getCol();
		switch(dir){
		case NORTH:
			if(locations[playerLocRow + 1][playerLocCol].isSolid()) return false;
			Player.getInstance().setLocation(locations[playerLocRow + 1][playerLocCol]);
			return true;
		case EAST:
			if(locations[playerLocRow][playerLocCol + 1].isSolid()) return false;
			Player.getInstance().setLocation(locations[playerLocRow][playerLocCol + 1]);
			return true;
		case SOUTH:
			if(locations[playerLocRow - 1][playerLocCol].isSolid()) return false;
			Player.getInstance().setLocation(locations[playerLocRow - 1][playerLocCol]);
			return true;
		case WEST:
			if(locations[playerLocRow][playerLocCol - 1].isSolid()) return false;
			Player.getInstance().setLocation(locations[playerLocRow - 1][playerLocCol]);
			return true;
		default:
			throw new IllegalArgumentException("Direction: " + dir.toString() + " not recognised");
		}
	}
}
