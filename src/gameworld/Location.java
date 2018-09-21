package gameworld;

/**
 * @author Deanne Alabastro
 *
 */
public class Location {
	private int row;
	private int col;
	
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
}


