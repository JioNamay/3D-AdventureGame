package gameworld;

import java.util.ArrayList;
import java.util.List;

import gameworld.items.Door;

public class Room {

	public static final int SIZE = 7;
	Location[][] locations = new Location[SIZE][SIZE];
	List<GameObjectInterface> gameObjects = new ArrayList<GameObjectInterface>();
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
	public List<GameObjectInterface> getGameObjects() {
		return gameObjects;
	}

	/**
	 * @param gameObjects the gameObjects to set
	 */
	public void setGameObjects(List<GameObjectInterface> gameObjects) {
		this.gameObjects = gameObjects;
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
	
	public void addGameObject(GameObjectInterface obj) {
		this.gameObjects.add(obj);
	}
	
	public void removeGameObject(GameObjectInterface obj) {
		this.gameObjects.remove(obj);
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
