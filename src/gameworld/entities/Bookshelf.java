/**
 *
 */
package gameworld.entities;

import gameworld.Room;
import gameworld.entities.Item.Action;

/**
 * The Bookshelf is attackable and the player can also take books from it.
 *
 * @author Deanne Alabastro 300346210
 */
public class Bookshelf extends AttackingItems implements Container {

  public Bookshelf() {
    this.name = "Bookshelf";
    this.description = "a sturdy bookshelf";
    this.health = 100;
    this.maxDamage = 20;
  }

  /*
   * (non-Javadoc)
   *
   * @see gameworld.entities.Damageable#die()
   */
  @Override
  public void die() {
    // give player coins
    Player.getInstance().addCoins(5);
    // remove from room
    Room currentRoom = Player.getInstance().getCurrentRoom();
    currentRoom.removeGameItem(currentRoom.getGameItemLocation(new Item(this)));
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * gameworld.entities.Container#placeItem(gameworld.entities.PickUpAbleStrategy)
   */
  @Override
  public void placeItem(PickUpAbleStrategy item) {
    Player player = Player.getInstance();
    // remove book from inventory if player is trying to place book in bookshelf
    if (player.getSelectedItem() instanceof HeavyBook)
      player.getInventory().remove(player.getSelectedItem());
  }

  /*
   * (non-Javadoc)
   *
   * @see gameworld.entities.Container#takeItem()
   */
  @Override
  public PickUpAbleStrategy takeItem() {
    return new HeavyBook();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gameworld.entities.LockableStrategy#performAction(gameworld.entities.Item.
   * Action)
   */
  @Override
  public String performAction(Action action) {
    switch (action) {
    case TAKE:
      if (Player.getInstance().getInventory().isFull())
        return "Can't take anything, inventory is full";
      Player.getInstance().getInventory().add(takeItem());
      return "Player took a book from the bookshelf";
    case PLACE:
      if (Player.getInstance().getSelectedItem() instanceof HeavyBook) {
        placeItem(Player.getInstance().getSelectedItem());
        return "Player placed a book in the bookshelf";
      }
      return "Player can only place books in the bookshelf";
    default:
      return super.performAction(action);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Container#hasItem()
   */
  @Override
  public boolean hasItem() {
    return true;
  }

}
