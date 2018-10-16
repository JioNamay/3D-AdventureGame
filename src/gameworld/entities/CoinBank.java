package gameworld.entities;

import gameworld.Location.Direction;

/**
 * The coinbank class is extended by every entity who is able to give player
 * coins when a player interacts with it.
 * 
 * @author Deanne Alabastro 300346210
 */

public abstract class CoinBank implements Strategy {

  /** A long description of the entity. */
  protected String description;

  /** The name of the entity (short description) */
  protected String name;

  /** The coin bank. */
  protected int coinBank;

  /** The direction the item is facing. */
  protected Direction direction;

  /**
   * Give player coins.
   *
   * @param amount
   *          the amount
   * @return the string
   */
  protected String givePlayerCoins(int amount) {
    if (coinBank == 0)
      return description;

    int givenAmount = amount; // the amount of coins actually given to player
    // add the rest of coinbank to player if amount exceeds coinbank
    if (((coinBank - amount) < 0) && coinBank != 0) {
      Player.getInstance().addCoins(coinBank);
      givenAmount = coinBank;
      coinBank = 0;
    } else {
      Player.getInstance().addCoins(amount);
      coinBank -= amount;
    }

    return "You found " + givenAmount + " coins\n" + description;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#isWall()
   */
  @Override
  public boolean isWall() {
    return false;
  }

  /**
   * Gets the description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the coin bank.
   *
   * @return the coinBank
   */
  public int getCoinBank() {
    return coinBank;
  }

  /**
   * Sets the coin bank.
   *
   * @param coinBank
   *          the coinBank to set
   */
  public void setCoinBank(int coinBank) {
    this.coinBank = coinBank;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#getDirection()
   */
  @Override
  public Direction getDirection() {
    if (direction == null)
      return Direction.NORTH;
    return direction;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#setDirection()
   */
  @Override
  public void setDirection(Direction direction) {
    this.direction = direction;
  }

}
