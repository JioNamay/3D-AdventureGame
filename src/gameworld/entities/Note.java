/**
 * 
 */
package gameworld.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import gameworld.entities.Item.Action;

/**
 * The note allows you to set the writing the player can see in it. It also
 * provides other words to read if the writing is not set.
 * 
 * @author Deanne Alabastro 300346210
 *
 */
public class Note extends PickUpAbleStrategy {
  /** The probability - used to determine what the user reads from the note. */
  private int probability;

  /** The writing on the note - allows developers to set what is written. */
  private String writing;

  public Note() {
    this.description = "note with some writing";
    this.name = "Note";
    this.coinBank = 3;

    Random rand = new Random();
    probability = rand.nextInt(6 + 1) + 1;
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
    return Arrays.asList(Action.EXAMINE.toString(), Action.READ.toString(), Action.DROP.toString());
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
    case READ:
      return read();
    default:
      return super.performAction(action);
    }
  }

  /**
   * Read the Note.
   *
   * @return the words written in the Note
   */
  private String read() {
    if (writing != null)
      return writing;
    switch (probability) {
    case 1:
      return "Toss some coins into the fountain and make a wish.";
    case 2:
      return "Sometimes the best places to hide keys are in the most obvious or overlooked places.";
    case 3:
      return "This note is blank";
    case 4:
      return "I love secret rooms. I wonder where the entrance is.";
    case 5:
      return "Bookshelves are the best at hiding things but one wrong move and all the books may come tumbling down.";
    default:
      return "The writing is illegible";
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#isSolid()
   */
  @Override
  public boolean isSolid() {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#isDoor()
   */
  @Override
  public boolean isDoor() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * @return the writing
   */
  public String getWriting() {
    return writing;
  }

  /**
   * @param writing
   *          the writing to set
   */
  public void setWriting(String writing) {
    this.writing = writing;
  }

}
