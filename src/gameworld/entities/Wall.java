package gameworld.entities;

import gameworld.Location.Direction;
import gameworld.entities.Item.Action;
import java.util.List;


/**
 * The wall object represents a wall and marks a location as solid so that a
 * player cannot walk past it.
 * 
 * @author Deanne Alabastro 300346210
 */
public class Wall implements Strategy {

  /**
   * Instantiates a new wall.
   */
  public Wall() {
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#getActions()
   */
  @Override
  public List<String> getActions() {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#getDescription()
   */
  @Override
  public String getDescription() {
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#getName()
   */
  @Override
  public String getName() {
    return "Wall";
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#isSolid()
   */
  @Override
  public boolean isSolid() {
    return true;
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

  @Override
  public boolean isWall() {
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#getDirection()
   */
  @Override
  public Direction getDirection() {
    return Direction.NORTH;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#setDirection(gameworld.Location.Direction)
   */
  @Override
  public void setDirection(Direction direction) {
    return;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gameworld.entities.Strategy#performAction(gameworld.entities.Item.Action)
   */
  @Override
  public String performAction(Action action) {
    return null;
  }
}
