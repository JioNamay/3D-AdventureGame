package renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import application.GUI;
import gameworld.GameWorld;
import gameworld.Location;
import gameworld.Room;

public class Draw {

	public static int PANEL_SIZE;
	public static int NUM_OF_TILES;
	public static int MARGIN;
	public static int TILE_SIZE;

	public static int CENTER;
	public static int TILE_WIDTH;
	public static double TILE_HEIGHT;

	public static Map<String, BufferedImage> loadedImages = new HashMap<String, BufferedImage>();

	public static void redraw(Graphics g) {

		// INIT FIELDS

		PANEL_SIZE = GUI.DRAWING_SIZE; // size of the drawing JPanel (in pixels)
		CENTER = PANEL_SIZE / 2;
		MARGIN = PANEL_SIZE / 6; // number of pixels from where the drawing of the floor tiles will begin (in
									// pixels)

		NUM_OF_TILES = Room.SIZE; // number of floor tiles from one side of the room to the other
		TILE_SIZE = (PANEL_SIZE - 2 * MARGIN) / NUM_OF_TILES; // size of each location (floor tile) in the room (in pixels)
		TILE_WIDTH = TILE_SIZE;
		TILE_HEIGHT = TILE_SIZE * 0.577;

		GameWorld gw = new GameWorld(null);
		Room room = gw.getCurrentRoom();
		// Location[][] locations = room.getLocations();

		// DRAW BACKGROUND

		g.setColor(Color.WHITE);

		int width = (int) g.getClip().getBounds2D().getWidth();
		int height = (int) g.getClip().getBounds2D().getHeight();

		g.fillRect(0, 0, width, height);

		// DRAW FLOOR TILES

		g.setColor(Color.BLACK);

		for (int row = 0; row < NUM_OF_TILES; row++) {
			for (int col = 0; col < NUM_OF_TILES; col++) {

				int topLeft = MARGIN + col * TILE_SIZE;
				int botRight = MARGIN + row * TILE_SIZE;

				// g.drawRect(topLeft, botRight, TILE_SIZE, TILE_SIZE);
			}
		}

		// DRAW ISOMETRIC FLOOR TILE IMAGES

		for (int row = 0; row < NUM_OF_TILES; row++) {
			for (int col = 0; col < NUM_OF_TILES; col++) {

				if ((row == 2 && col == 3) || (row == 5 && col == 6)) {
					drawImageAtLocation(g, "src/renderer/data/chest_closed.png", row, col);
				} else {
					drawImageAtLocation(g, "src/renderer/data/floor_tile.png", row, col);
				}
			}
		}

		// DRAW ISOMETRIC TILES

		g.setColor(Color.RED);

		for (int row = 0; row < NUM_OF_TILES; row++) {
			for (int col = 0; col < NUM_OF_TILES; col++) {
				// g.drawPolygon(getIsometricPolygon(row, col, MARGIN, CENTER, TILE_WIDTH,
				// TILE_HEIGHT));
			}
		}
	}

	private static void drawImageAtLocation(Graphics g, String fileName, int row, int col) {

		if (!loadedImages.containsKey(fileName)) {
			try {
				loadedImages.put(fileName, ImageIO.read(new File(fileName)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		BufferedImage image = loadedImages.get(fileName);

		Polygon p = getIsometricPolygon(row, col, MARGIN, CENTER, TILE_WIDTH, TILE_HEIGHT);
		double x = p.getBounds().getMinX();
		double y = p.getBounds().getMaxY();
		double polygonWidth = p.getBounds().getMaxX() - p.getBounds().getMinX();
		double imageWidth = image.getWidth() / (image.getWidth() / polygonWidth);
		double imageHeight = image.getHeight() / (image.getWidth() / polygonWidth);

		Image scaledImage = image.getScaledInstance((int) imageWidth, (int) imageHeight, Image.SCALE_SMOOTH);
		g.drawImage(scaledImage, (int) x, (int) (y - scaledImage.getHeight(null)), null);
	}

	private static Polygon getIsometricPolygon(int row, int col, int MARGIN, int CENTER, int TILE_WIDTH, double TILE_HEIGHT) {
		int x = col;
		int y = row;

		int x1 = (int) (((x + 0) - (y + 0)) * TILE_WIDTH);
		int y1 = (int) (((x + 0) + (y + 0)) * TILE_HEIGHT);

		int x2 = (int) (((x + 1) - (y + 0)) * TILE_WIDTH);
		int y2 = (int) (((x + 1) + (y + 0)) * TILE_HEIGHT);

		int x3 = (int) (((x + 1) - (y + 1)) * TILE_WIDTH);
		int y3 = (int) (((x + 1) + (y + 1)) * TILE_HEIGHT);

		int x4 = (int) (((x + 0) - (y + 1)) * TILE_WIDTH);
		int y4 = (int) (((x + 0) + (y + 1)) * TILE_HEIGHT);

		Polygon p = new Polygon();
		p.addPoint(CENTER + x1, MARGIN + y1);
		p.addPoint(CENTER + x2, MARGIN + y2);
		p.addPoint(CENTER + x3, MARGIN + y3);
		p.addPoint(CENTER + x4, MARGIN + y4);
		return p;
	}
}
