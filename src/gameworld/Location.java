package gameworld;

/**
 * @author Deanne Alabastro
 *
 */
public class Location {
	public enum Direction { 
		NORTH,
		SOUTH,
		EAST,
		WEST
	}
	private int row;
	private int col;

	private boolean isSolid;
	private boolean isDoor;
	

	/**
	 * 
	 */
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}

	/**
	 * @return the isSolid
	 */
	public boolean isSolid() {
		return isSolid;
	}

	/**
	 * @param isSolid the isSolid to set
	 */
	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	/**
	 * @return the isDoor
	 */
	public boolean isDoor() {
		return isDoor;
	}

	/**
	 * @param isDoor the isDoor to set
	 */
	public void setDoor(boolean isDoor) {
		this.isDoor = isDoor;
	}

}
