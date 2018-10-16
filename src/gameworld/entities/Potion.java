package gameworld.entities;

import java.util.Arrays;
import java.util.List;

import gameworld.entities.Item.Action;

/**
 * The potion can recover the player's health by 10.
 * 
 * @author Deanne Alabastro 300346210
 */
public class Potion extends PickUpAbleStrategy implements Damageable {

  /** The uses. */
  private int uses;

  /**
   * Instantiates a new potion.
   */
  public Potion() {
    this.uses = 1;
    this.description = "A magical potion that restores 10 points of health";
    this.name = "Potion";
    this.coinBank = 3;
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
   * @see
   * gameworld.entities.PickUpAbleStrategy#performAction(gameworld.entities.Item.
   * Action)
   */
  @Override
  public String performAction(Action action) {
    switch (action) {
    case EXAMINE:
      return givePlayerCoins(3);
    case USE:
      Player.getInstance().recover(10);
      getDamaged(1);
      return "Player drank the potion and recovers 10 bits of life. As the last drop of liquid leaves the bottle, the bottle magically disappears!";
    default:
      return super.performAction(action);
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
    return Arrays.asList(Action.EXAMINE.toString(), Action.USE.toString(), Action.DROP.toString());
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
