
package tests.gameworld;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameworld.entities.Inventory;
import gameworld.entities.Player;
import gameworld.entities.Item.Action;
import gameworld.Location;
import gameworld.Room;
import gameworld.entities.*;

/**
 * Tests for the Inventory class
 * @author Deanne Alabastro 300346210
 *
 */
class InventoryTests {
	Player player;
	
	@BeforeEach
	public void setupPlayerAndInventory() {
		player = Player.getInstance();
		player.resetInventoryForTest();
	}
	 
	@Test
	public void testInventoryAdd() {
		assertTrue(player.getInventory().add(new Potion()));
	}
	
	@Test
	public void testInventoryAddInvalidWhenFull() {
		for(int i = 0; i < 10; i++) {
			player.getInventory().add(new Potion());
		}
		
		assertFalse(player.getInventory().add(new Potion()));
	}
	
	@Test
	public void testInventoryRemove() {
		PickUpAbleStrategy potion = new Potion();
		PickUpAbleStrategy potion2 = new Potion();
		// Add two potions to make sure that the right one is removed
		player.getInventory().add(potion);
		player.getInventory().add(potion2);
		
		player.getInventory().remove(potion);
		
		Iterator<PickUpAbleStrategy> it = player.getInventory().iterator();
		PickUpAbleStrategy item = it.next();

		// check that the right one has been removed
		assertTrue(item != null);
		assertTrue(item == potion2);
		assertTrue(item.equals(potion2));
	}
	
	@Test
	public void testInventoryContain() {
		PickUpAbleStrategy potion = new Potion();
		PickUpAbleStrategy potion2 = new Potion();
		PickUpAbleStrategy key = new Key();

		player.getInventory().add(potion);
		player.getInventory().add(key);
		player.getInventory().add(potion2);
		
		assertTrue(player.getInventory().contains(potion));
		assertTrue(player.getInventory().contains(potion2));
		assertTrue(player.getInventory().contains(key));
	}
	
	@Test
	public void testInventoryHasKey() {
		Room room = new Room("Key pickup test"); // need room to test key pickup which increments inventory keys
		player.setCurrentRoom(room);
		Location[][] roomLocs = room.getLocations();
		
		PickUpAbleStrategy key = new Key();
		room.addGameItem(2,2, new Item(key));
		player.setLocation(roomLocs[2][2]);

		
		key.performAction(Action.PICKUP);
		assertTrue(player.getInventory().contains(key));
		assertTrue(player.getInventory().hasKey());
	}
	
	@Test
	public void testInventoryDecrementsKeyCount() {
		Room room = new Room("Key pickup test"); // need room to test key pickup which increments inventory keys
		player.setCurrentRoom(room);
		Location[][] roomLocs = room.getLocations();
		
		PickUpAbleStrategy key = new Key();
		room.addGameItem(2,2, new Item(key));
		player.setLocation(roomLocs[2][2]);
		// check pick up works
		key.performAction(Action.PICKUP);
		assertTrue(player.getInventory().contains(key));
		assertTrue(player.getInventory().hasKey());
		
		// check drop is correct and decrements keys
		key.performAction(Action.DROP);
		assertFalse(player.getInventory().contains(key));
		assertFalse(player.getInventory().hasKey());
	}
	
	@Test
	public void testInventorySetKeyCount() {
		player.getInventory().setKeyCount(3);
		assertTrue(player.getInventory().getKeyCount() == 3);
	}

}
