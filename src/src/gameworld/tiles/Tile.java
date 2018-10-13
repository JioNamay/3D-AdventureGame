package gameworld.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;

	protected BufferedImage texture; // tile image
	protected final int id; // every id is unique to a Tile

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
	}

	public void tick() {

	}

	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}

	public int getId() {
		return id;
	}

}
