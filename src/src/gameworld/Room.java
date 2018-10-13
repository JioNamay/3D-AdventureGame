package gameworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameworld.entities.Door;
import gameworld.entities.Item;

public class Room {

	public static final int SIZE = 7;
	Location[][] locations = new Location[SIZE][SIZE];
	Map<Location,Item> gameItems = new HashMap<Location,Item>();
	List<Door> doors = new ArrayList<Door>();
	String name;
	
	public Room(String name) {
		this.name = name;
		initialiseLocations();
	}
	
	private void initialiseLocations() {
		for(int row = 0; row < SIZE; row++) {
			for(int col = 0; col < SIZE; col++) {
				locations[row][col] = new Location(row,col);
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
	 * @param gameObjects the gameObjects to set
	 */
	public void setGameObjects(Map<Location, Item> gameObjects) {
		this.gameItems = gameObjects;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void addGameObject(Item e) {
		this.gameItems.put(e.getLocation(), e);
	}
	
	public void removeGameObject(Location loc) {
		this.gameItems.remove(loc);
	}

	/**
	 * @return the doors
	 */
	public List<Door> getDoors() {
		return doors;
	}

	/**
	 * @param doors the doors to set
	 */
	public void setDoors(List<Door> doors) {
		this.doors = doors;
	}

	
	

}
