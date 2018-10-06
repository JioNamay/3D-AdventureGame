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

	public Tile getTile() {
		return this.tile;
	}
}
