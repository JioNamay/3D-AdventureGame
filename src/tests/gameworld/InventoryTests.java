
package tests.gameworld;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameworld.entities.Inventory;
import gameworld.entities.Player;
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
		player.setInventory(new Inventory());
	}
	
	@Test
	public void testInventoryAdd() {
		System.out.println("Add");
		assertTrue(player.getInventory().add(new Potion(null)));
	}
	
	@Test
	public void testInventoryAddInvalidWhenFull() {
		System.out.println("Add full");
		for(int i = 0; i < 10; i++) {
			player.getInventory().add(new Potion(null));
		}
		
		assertFalse(player.getInventory().add(new Potion(null)));
	}
	
	@Test
	public void testInventoryRemove() {
		PickUpAbleStrategy potion = new Potion(null);
		PickUpAbleStrategy potion2 = new Potion(null);
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
		PickUpAbleStrategy potion = new Potion(null);
		PickUpAbleStrategy potion2 = new Potion(null);
		PickUpAbleStrategy key = new Key(null);

		player.getInventory().add(potion);
		player.getInventory().add(key);
		player.getInventory().add(potion2);
		
		assertTrue(player.getInventory().contains(potion));
		assertTrue(player.getInventory().contains(potion2));
		assertTrue(player.getInventory().contains(key));
	}

}
