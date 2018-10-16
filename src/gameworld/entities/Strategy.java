package gameworld.entities;

import gameworld.Location.Direction;
import gameworld.entities.Item.Action;
import java.util.List;

/**
 * The Interface Strategy are the common methods that every item in the
 * gameworld has.
 * 
 * @author yangcarr 300368805
 */
public interface Strategy {

  /**
   * Gets the actions that the user can perform on it.
   *
   * @return the actions
   */
  public List<String> getActions();

  /**
   * Gets the description.
   *
   * @return the description
   */
  public String getDescription();

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName();

  /**
   * Checks if is solid (player cannot move into its location).
   *
   * @return true, if is solid
   */
  public boolean isSolid();

  /**
   * Checks if is door.
   *
   * @return true, if is door
   */
  public boolean isDoor();

  /**
   * Checks if is wall.
   *
   * @return true, if is wall
   */
  public boolean isWall();

  /**
   * Gets the direction that the item is facing.
   *
   * @return the direction
   */
  public Direction getDirection();

  /**
   * Sets the direction that the item is facing.
   */
  public void setDirection(Direction direction);

  /**
   * Performs specified action based on user choice or item behavior.
   *
   * @param action
   *          the action
   * @return the result in String format
   */
  public String performAction(Action action);
}
