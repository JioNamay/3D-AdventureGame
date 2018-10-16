package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Item.Action;

/**
 * The key can be used to unlock any locked items. It breaks after 1 use.
 * 
 * @author Deanne Alabastro 300346210
 */
public class Key extends PickUpAbleStrategy implements Damageable {

  /** The uses. */
  private int uses;

  /**
   * Instantiates a new key.
   */
  public Key() {
    this.uses = 1;
    this.name = "Key";
    this.description = "A key able to open any locks. It looks pretty fragile though.";
    this.coinBank = 3;
  }

  /**
   * Overloading the parent method, in the case that the action is Examine. Then
   * we return the String description of the key. It will also have a 'use'
   * method, which is to be used when player is in the same location as a locked
   * door Otherwise it will perform the parent's actions.
   *
   * @param action
   *          the action
   * @return the string
   */
  public String performAction(Action action) {
    switch (action) {
    case EXAMINE:
      return givePlayerCoins(1);
    case PICKUP:
      String result = pickUp();
      if (result.equals("You picked up " + this.getName()))
        Player.getInstance().getInventory().incrementKeys();
      return result;
    case DROP:
      result = drop();
      if (result.equals("Player dropped " + this.getName()))
        Player.getInstance().getInventory().decrementKeys();
      return result;
    case USE:
      getDamaged(1);
      return "Player used key. The key was too fragile and broke.";
    default:
      throw new IllegalArgumentException(
          "Unknown action: " + action.toString() + " for object: " + this.name);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Damageable#getHealth()
   */
  @Override
  public int getHealth() {
    return uses;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Damageable#die()
   */
  @Override
  public void die() {
    Player.getInstance().getInventory().decrementKeys();
    Player.getInstance().getInventory().remove(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Damageable#getDamaged(int)
   */
  @Override
  public void getDamaged(int amount) {
    if ((this.uses - amount) < 0) {
      this.uses = 0;
      this.die();
    } else {
      uses -= amount;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#getActions()
   */
  @Override
  public List<String> getActions() {
    if (!Player.getInstance().getInventory().contains(this))
      return actions; // return default actions if not in inventory

    // return new action if in inventory
    return Arrays.asList(Action.EXAMINE.toString(), Action.DROP.toString());
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#isSolid()
   */
  @Override
  public boolean isSolid() {
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#isDoor()
   */
  @Override
  public boolean isDoor() {
    return false;
  }

}
