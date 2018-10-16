/**
 * 
 */
package tests.gameworld;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameworld.Room;
import gameworld.entities.Item;
import gameworld.entities.Item.Action;
import gameworld.entities.Key;
import gameworld.entities.Player;
import gameworld.entities.Table;

/**
 * @author Deanne Alabastro 300346210
 *
 */
class TableTests {

	private Player player;
	private Table table;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Player.getInstance().resetPlayer();
		player = Player.getInstance();
		table = new Table();
	}

	@Test
	void testGetDescription() {
		Room room = new Room("Test");
		player.setCurrentRoom(room);
		assertTrue(table.isLocked());
		assertFalse(table.isOpen());
		assertTrue(table.getDescription().equals("A locked ordinary table with drawers"));
		Key key = new Key();
		room.addGameItem(2, 3, new Item(key));
		key.performAction(Action.PICKUP);
		table.performAction(Action.UNLOCK);
		assertFalse(table.isLocked());
		assertTrue(table.getDescription().equals("A closed ordinary table with drawers"));
		table.performAction(Action.OPEN);
		assertTrue(table.isOpen());
		assertTrue(table.getDescription().contains("An open ordinary table with drawers containing"));
	}
	
	@Test
	void validTake() {
		assertTrue(table.performAction(Action.TAKE).contains("You got "));
	}
	


}
