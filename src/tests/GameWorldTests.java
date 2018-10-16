package tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import gameworld.entities.Inventory;
import gameworld.entities.Player;

public class GameWorldTests {

  @Test
  public void testPlayerExists() {
    Player player = Player.getInstance();
    assertTrue(player != null);
  }

  @Test
  public void testPlayerSetInventory() {
    Player player = Player.getInstance();
    Inventory inventory = new Inventory();
    assertTrue(inventory.iterator() != null);
    player.setInventory(inventory);
    assertTrue(player.getInventory() != null);
    assertTrue(!player.getInventory().isFull());
  }

  /*
   * test: player has items in inventory when he picks it up, and the items are
   * the correct ones he picked up
   */

  /*
   * test: location tests
   */

  /*
   * null tests
   */

  /*
   * test: player's health deteoriates correctly, wrt the item that will inflict
   * the certain amt of damage
   */

  /*
   * test: monster health
   */

  /*
   * test: items behave correctly when their lifetimes are over
   */
}
