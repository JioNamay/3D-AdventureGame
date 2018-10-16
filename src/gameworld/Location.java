package gameworld;

/**
 * Location class, knows if its solid (unwalkable) or is a door.
 * @author Deanne Alabastro 300346210
 *
 */
public class Location {
  
  /**
   * The Enum Direction.
   */
  public enum Direction {
    NORTH, 
    SOUTH,  
    EAST, 
    WEST
  }

  /** The row. */
  private int row;
  
  /** The col. */
  private int col;

  /** The is solid. */
  private boolean isSolid;
  
  /** The is door. */
  private boolean isDoor;

  /**
   * Instantiates a new location.
   *
   * @param row the row
   * @param col the col
   */
  public Location(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Gets the row.
   *
   * @return the row
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Gets the col.
   *
   * @return the col
   */
  public int getCol() {
    return this.col;
  }

  /**
   * Checks if is solid.
   *
   * @return the isSolid
   */
  public boolean isSolid() {
    return isSolid;
  }

  /**
   * Sets the solid.
   *
   * @param isSolid          the isSolid to set
   */
  public void setSolid(boolean isSolid) {
    this.isSolid = isSolid;
  }

  /**
   * Checks if is door.
   *
   * @return the isDoor
   */
  public boolean isDoor() {
    return isDoor;
  }

  /**
   * Sets the door.
   *
   * @param isDoor          the isDoor to set
   */
  public void setDoor(boolean isDoor) {
    this.isDoor = isDoor;
  }

}
