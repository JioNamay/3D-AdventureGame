package gameworld;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import gameworld.entities.Door;
import gameworld.entities.Item;

import gameworld.Location.Direction;
import gameworld.entities.*;


@XmlRootElement(name = "Room")
public class Room {

	public static final int SIZE = 7;
	Location[][] locations = new Location[SIZE][SIZE];
	Map<Location, Item> gameItems = new HashMap<Location, Item>();
	boolean hasPlayer;
	String name;
	
	public Room() {
		super();
	}
	
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
	 * @return the the items within room. 
	 */
	@XmlElement(name = "gameObjects")
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
		initialiseDoorLocation(); 
	}

	private void initialiseLocationSolidity() {
		for (Map.Entry<Location, Item> entry : gameItems.entrySet()) {
			if (entry.getValue().isSolid())
				entry.getKey().setSolid(true);
		}
	}
	
	private void initialiseDoorLocation() {
		for (Map.Entry<Location, Item> entry : gameItems.entrySet()) {
			if (entry.getValue().isDoor())
				entry.getKey().setDoor(true);
		}
	}

	/**
	 * @return the name
	 */
	@XmlElement(name = "RoomName")
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

	 public boolean hasPlayer() {
		return hasPlayer;
	}

	/**
	 * @param hasPlayer
	 *            the hasPlayer to set
	 */
	public void setHasPlayer(boolean hasPlayer) {
		this.hasPlayer = hasPlayer;
	}

	public void addGameObject(Location loc, Item e) {
		this.gameItems.put(loc, e);
		if (e.isSolid())
			loc.setSolid(true);
		if(e.isDoor())
			loc.setDoor(true);
	}

	public String playerDropGameObject(PickUpAbleStrategy e) {
		// check if there is already an item (besides player) on player location
		Item playerLocItem = gameItems.get(Player.getInstance().getLocation());
		if (playerLocItem == null) {
			Player.getInstance().getInventory().remove(e);
			gameItems.put(Player.getInstance().getLocation(), new Item(e));
			return "Player dropped " + e.getName();
		} else
			return "Player cannot drop " + e.getName() + " location is occupied.";

	}

	public void removeGameObject(Location loc) {
		this.gameItems.remove(loc);
	}

	public void removeGameObject(PickUpAbleStrategy e) {
		Location loc = null;
		for (Map.Entry<Location, Item> entry : gameItems.entrySet()) {
			if (entry.getValue().getItem().equals(e))
				loc = entry.getKey();
		}
		this.gameItems.remove(loc);
	}

	public boolean movePlayer(Direction dir) {
		int playerLocRow = Player.getInstance().getLocation().getRow();
		int playerLocCol = Player.getInstance().getLocation().getCol();
		switch (dir) {
		case NORTH:
			if (locations[playerLocRow + 1][playerLocCol].isSolid()) // check if player can move into loc
				return false;
			else if(locations[playerLocRow][playerLocCol].isDoor()) // check if loc is a door
				return movePlayerToConnectingRoom(Direction.NORTH);
			
			// move normally
			Player.getInstance().setLocation(locations[playerLocRow + 1][playerLocCol]);
			return true;
		case EAST:
			if (locations[playerLocRow][playerLocCol + 1].isSolid())
				return false;
			else if(locations[playerLocRow][playerLocCol].isDoor()) 
				return movePlayerToConnectingRoom(Direction.EAST);
			
			Player.getInstance().setLocation(locations[playerLocRow][playerLocCol + 1]);
			return true;
		case SOUTH:
			if (locations[playerLocRow - 1][playerLocCol].isSolid())
				return false;
			else if(locations[playerLocRow][playerLocCol].isDoor()) 
				return movePlayerToConnectingRoom(Direction.SOUTH);
			
			Player.getInstance().setLocation(locations[playerLocRow - 1][playerLocCol]);
			return true;
		case WEST:
			if (locations[playerLocRow][playerLocCol - 1].isSolid())
				return false;
			else if(locations[playerLocRow][playerLocCol].isDoor()) 
				return movePlayerToConnectingRoom(Direction.WEST);
			
			Player.getInstance().setLocation(locations[playerLocRow - 1][playerLocCol]);
			return true;
		default:
			throw new IllegalArgumentException("Direction: " + dir.toString() + " not recognised");
		}
	}

	private boolean movePlayerToConnectingRoom(Direction dir) {
		int playerLocRow = Player.getInstance().getLocation().getRow();
		int playerLocCol = Player.getInstance().getLocation().getCol();

		Door door = (Door) gameItems.get(Player.getInstance().getLocation()).getItem();
		Location[][] connectingRoomLoc = null; // locations of the room player is going to be moving to
		Room goingTo = null; // room the player is going to be moving to

		// check which side of the door player is in
		if (Player.getInstance().getCurrentRoom().equals(door.getFrom())) {
			connectingRoomLoc = door.getGoesTo().getLocations(); // get the locations of the other side
			goingTo = door.getGoesTo();
		} else {
			connectingRoomLoc = door.getFrom().getLocations();
			goingTo = door.getFrom();
		}

		// move the player to a location in the other room
		switch (dir) {
		case NORTH:
			Player.getInstance().setLocation(connectingRoomLoc[SIZE - 1][playerLocCol]);
			break;
		case EAST:
			Player.getInstance().setLocation(connectingRoomLoc[playerLocRow][0]);
			break;
		case SOUTH:
			Player.getInstance().setLocation(connectingRoomLoc[0][playerLocCol]);
			break;
		case WEST:
			Player.getInstance().setLocation(connectingRoomLoc[playerLocRow][SIZE - 1]);
			break;
		default:
			throw new IllegalArgumentException("Direction: " + dir.toString() + " not recognised");
		}
		
		// set the player's current room to the new one
		Player.getInstance().setCurrentRoom(goingTo);
		return true;
	}

}
