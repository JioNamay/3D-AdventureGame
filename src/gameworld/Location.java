package gameworld;

import gameworld.tiles.Tile;

/**
 * @author Deanne Alabastro
 *
 */
public class Location {
	private int row;
	private int col;
	private Tile tile;

	private boolean isSolid;
	

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

	public Tile getTile() {
		return this.tile;
	}
}
