package renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import application.GUI;
import gameworld.Location;
import gameworld.Room;
import gameworld.entities.Bookshelf;
import gameworld.entities.Cactus;
import gameworld.entities.Door;
import gameworld.entities.Fountain;
import gameworld.entities.HeavyBook;
import gameworld.entities.Item;
import gameworld.entities.Key;
import gameworld.entities.Note;
import gameworld.entities.Potion;
import gameworld.entities.Rock;
import gameworld.entities.Sofa;
import gameworld.entities.Stick;
import gameworld.entities.Table;
import gameworld.entities.TreasureChest;
import gameworld.entities.Tree;
import gameworld.entities.Wall;

public class Renderer {

	public int BOARD_ROTATION;

	public int PANEL_SIZE;
	public int NUM_OF_TILES;
	public int TILE_SIZE;

	public int CENTER_X;
	public int CENTER_Y;
	public int TILE_WIDTH;
	public double TILE_HEIGHT;

	private boolean mouseDown = false;
	private Point mouseLocation;

	private Map<String, BufferedImage> loadedImages = new HashMap<String, BufferedImage>();

	public Renderer() {
		BOARD_ROTATION = 0;

		PANEL_SIZE = GUI.DRAWING_SIZE; // size of the drawing JPanel (in pixels)
		CENTER_X = PANEL_SIZE / 2;
		CENTER_Y = PANEL_SIZE / 4;

		NUM_OF_TILES = Room.SIZE; // number of floor tiles from one side of the room to the other
		TILE_SIZE = (PANEL_SIZE) / NUM_OF_TILES / 2; // size of each location (floor tile) in the room (in
																// pixels)
		TILE_WIDTH = TILE_SIZE;
		TILE_HEIGHT = TILE_SIZE * 0.577;
	}

	public void doDraw(Graphics g) {
		Room r = new Room("Test");
		r.addGameItem(0, 0, new Item(new Bookshelf()));
		r.addGameItem(0, 1, new Item(new Cactus()));
		r.addGameItem(0, 2, new Item(new Fountain()));
		r.addGameItem(0, 3, new Item(new HeavyBook()));
		r.addGameItem(0, 4, new Item(new Key()));
		r.addGameItem(0, 5, new Item(new Note()));
		r.addGameItem(0, 6, new Item(new Potion()));
		r.addGameItem(1, 0, new Item(new Rock()));
		r.addGameItem(1, 1, new Item(new Sofa()));
		r.addGameItem(1, 2, new Item(new Stick()));
		r.addGameItem(1, 3, new Item(new Table()));
		r.addGameItem(1, 4, new Item(new TreasureChest()));
		r.addGameItem(1, 5, new Item(new Tree()));
		r.addGameItem(1, 6, new Item(new Wall()));
		// r.addGameItem(2, 0, new Item(new Door()));
		// r.addGameItem(2, 1, new Item(new Player()));

		// DRAW BACKGROUND

		g.setColor(Color.WHITE);
		int width = (int) g.getClip().getBounds2D().getWidth();
		int height = (int) g.getClip().getBounds2D().getHeight();
		g.fillRect(0, 0, width, height);

		// DRAW THE ISOMETRIC IMAGES

		Map<Location, Item> items = r.getGameItems();
		for (int row = 0; row < NUM_OF_TILES; row++) {
			for (int col = 0; col < NUM_OF_TILES; col++) {
				Item i = null;

				if (BOARD_ROTATION % 4 == 0) {// NORTH
					i = items.get(r.getLocation(row, col));
				} else if (BOARD_ROTATION % 4 == 1) {// EAST
					i = items.get(r.getLocation((NUM_OF_TILES - 1) - col, row));
				} else if (BOARD_ROTATION % 4 == 2) {// SOUTH
					i = items.get(r.getLocation((NUM_OF_TILES - 1) - row, (NUM_OF_TILES - 1) - col));
				} else if (BOARD_ROTATION % 4 == 3) {// WEST
					i = items.get(r.getLocation(col, (NUM_OF_TILES - 1) - row));
				}

				processLocation(g, r, i, row, col);
			}
		}
	}

	private void processLocation(Graphics g, Room r, Item i, int row, int col) {
		if (i == null) {
			drawImageAtLocation(g, "src/renderer/data/else/floor.png", row, col);
			return;
		}

		String url = "src/renderer/data/";
		String name = i.getName().toLowerCase();

		int itemRotation = 0;

		// these items are completely symmetrical from all views so only one image is
		// ever used
		if (name.equals("potion") || name.equals("rock") || name.equals("tree") || name.equals("wall")) {
			url += "else/";
		} else { // these items will look different based on the viewing direction
			switch (i.getItem().getDirection()) {
			case NORTH: {
				itemRotation = 0;
				break;
			}
			case EAST: {
				itemRotation = 1;
				break;
			}
			case SOUTH: {
				itemRotation = 2;
				break;
			}
			case WEST: {
				itemRotation = 3;
				break;
			}
			default:
				itemRotation = 0;
				break;
			}

			int finalRotation = BOARD_ROTATION + itemRotation;

			if (finalRotation % 4 == 0) {
				url += "north/";
			} else if (finalRotation % 4 == 1) {
				url += "east/";
			} else if (finalRotation % 4 == 2) {
				url += "south/";
			} else if (finalRotation % 4 == 3) {
				url += "west/";
			}
		}

		url += name;

		if (name.toLowerCase().equals("chest")) {
			TreasureChest c = (TreasureChest) i.getItem();
			if (c.isLocked()) {
				url += "_closed";
			} else if (c.isOpen()) {
				if (c.hasItem()) {
					url += "_full";
				} else {
					url += "_empty";
				}
			}
		} else if (name.toLowerCase().equals("door")) {
			Door d = (Door) i.getItem();
			if (d.isLocked()) {
				url += "_closed";
			} else {
				url += "_open";
			}
		}

		url += ".png";

		drawImageAtLocation(g, url.toLowerCase(), row, col);
	}

	private void drawImageAtLocation(Graphics g, String fileName, int row, int col) {

		if (!loadedImages.containsKey(fileName)) {
			try {
				loadedImages.put(fileName, ImageIO.read(new File(fileName)));
			} catch (IOException e) {
				System.out.println(fileName);
				e.printStackTrace();
			}
		}

		BufferedImage image = loadedImages.get(fileName);

		Polygon p = getIsometricPolygon(row, col);
		double x = p.getBounds().getMinX();
		double y = p.getBounds().getMaxY();
		double polygonWidth = p.getBounds().getMaxX() - p.getBounds().getMinX();
		double imageWidth = image.getWidth() / (image.getWidth() / polygonWidth);
		double imageHeight = image.getHeight() / (image.getWidth() / polygonWidth);

		Image scaledImage = image.getScaledInstance((int) imageWidth, (int) imageHeight, Image.SCALE_SMOOTH);
		g.drawImage(scaledImage, (int) x, (int) (y - scaledImage.getHeight(null)), null);
	}

	private Polygon getIsometricPolygon(int row, int col) {
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
		p.addPoint(CENTER_X + x1, CENTER_Y + y1);
		p.addPoint(CENTER_X + x2, CENTER_Y + y2);
		p.addPoint(CENTER_X + x3, CENTER_Y + y3);
		p.addPoint(CENTER_X + x4, CENTER_Y + y4);
		return p;
	}

	private Location getClickedLocation(MouseEvent e) {
		for (int row = 0; row < NUM_OF_TILES; row++) {
			for (int col = 0; col < NUM_OF_TILES; col++) {
				if (BOARD_ROTATION % 4 == 0) {// NORTH
					if (getIsometricPolygon(row, col).contains(e.getPoint())) {
						return new Location(row, col);
					}
				} else if (BOARD_ROTATION % 4 == 1) {// EAST
					if (getIsometricPolygon(col, (NUM_OF_TILES - 1) - row).contains(e.getPoint())) {
						return new Location(row, col);
					}
				} else if (BOARD_ROTATION % 4 == 2) {// SOUTH
					if (getIsometricPolygon((NUM_OF_TILES - 1) - row, (NUM_OF_TILES - 1) - col)
							.contains(e.getPoint())) {
						return new Location(row, col);
					}
				} else if (BOARD_ROTATION % 4 == 3) {// WEST
					if (getIsometricPolygon((NUM_OF_TILES - 1) - col, row).contains(e.getPoint())) {
						return new Location(row, col);
					}
				}
			}
		}

		return null;
	}

	public Location doRelease(MouseEvent e) {
		this.mouseDown = false;

		if (e.getButton() == 1) {
			return getClickedLocation(e);
		} else if (e.getButton() == 3) { // right click
			this.BOARD_ROTATION++;
		}

		return null;
	}

	public void doPress(MouseEvent e) {
		this.mouseDown = true;
		this.mouseLocation = e.getPoint();
	}

	public void doDrag(MouseEvent e) {
		if (mouseDown) {
			double dx = mouseLocation.getX() - e.getPoint().getX();
			double dy = mouseLocation.getY() - e.getPoint().getY();

			this.CENTER_X -= dx;
			this.CENTER_Y -= dy;
			this.mouseLocation = e.getPoint();
		}
	}

	public void doScroll(MouseWheelEvent e) {
		if (e.getWheelRotation() < 0) {
			TILE_SIZE++;
			TILE_WIDTH = TILE_SIZE;
			TILE_HEIGHT = TILE_SIZE * 0.577;
		} else if (e.getWheelRotation() > 0) {
			TILE_SIZE--;
			TILE_WIDTH = TILE_SIZE;
			TILE_HEIGHT = TILE_SIZE * 0.577;
		}
	}
}
