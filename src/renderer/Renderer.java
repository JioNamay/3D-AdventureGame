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
import gameworld.entities.Door;
import gameworld.entities.Item;
import gameworld.entities.Player;
import gameworld.entities.TreasureChest;

/**
 * The Class Renderer.
 *
 * @author manaentawe 300428465
 */
public class Renderer {

  /** The board rotation. */
  public int BOARD_ROTATION;

  /** The panel size. */
  public int PANEL_SIZE;

  /** The num of tiles. */
  public int NUM_OF_TILES;

  /** The tile size. */
  public int TILE_SIZE;

  /** The center x. */
  public int CENTER_X;

  /** The center y. */
  public int CENTER_Y;

  /** The tile width. */
  public int TILE_WIDTH;

  /** The tile height. */
  public double TILE_HEIGHT;

  /** The mouse down. */
  private boolean mouseDown = false;

  /** The mouse location. */
  private Point mouseLocation;

  /** The loaded images. */
  private Map<String, BufferedImage> loadedImages = new HashMap<String, BufferedImage>();

  /** Toggle the visibility of the walls */
  private boolean hideWalls = false;

  /**
   * Instantiates a new renderer.
   */
  public Renderer() {
    BOARD_ROTATION = 0;

    PANEL_SIZE = GUI.DRAWING_WIDTH;
    CENTER_X = PANEL_SIZE / 2;
    CENTER_Y = PANEL_SIZE / 4;

    NUM_OF_TILES = Room.SIZE;
    TILE_SIZE = (PANEL_SIZE) / NUM_OF_TILES / 2;
    TILE_WIDTH = TILE_SIZE;
    TILE_HEIGHT = TILE_SIZE * 0.577;
  }

  /**
   * Translate the 2D room into the 3D isometric representation and render it to
   * the drawing panel.
   *
   * @param g
   *          the graphics component to render to
   * @param r
   *          the room to render
   */
  public void doDraw(Graphics g, Room r) {

    // Clear the screen.
    int width = (int) g.getClip().getBounds2D().getWidth();
    int height = (int) g.getClip().getBounds2D().getHeight();

    g.setColor(new Color(125, 195, 235));
    g.fillRect(0, 0, width, height);

    // Display name of the room.
    g.setColor(Color.BLACK);
    g.drawString(r.getName(), PANEL_SIZE / 2 - (r.getName().length() * 3), TILE_SIZE / 2);

    // Draw the isometric images.
    Map<Location, Item> items = r.getGameItems();

    for (int row = 0; row < NUM_OF_TILES; row++) {
      for (int col = 0; col < NUM_OF_TILES; col++) {
        Player p = Player.getInstance();

        // North rotation.
        if (BOARD_ROTATION % 4 == 0) {
          int pRow = row;
          int pCol = col;

          if (p.getLocation().getRow() == pRow && p.getLocation().getCol() == pCol) {
            processLocation(g, r, p, row, col);
            continue;
          }
          // East rotation.
        } else if (BOARD_ROTATION % 4 == 1) {
          int pRow = (NUM_OF_TILES - 1) - col;
          int pCol = row;

          if (p.getLocation().getRow() == pRow && p.getLocation().getCol() == pCol) {
            processLocation(g, r, p, row, col);
            continue;
          }
          // South rotation.
        } else if (BOARD_ROTATION % 4 == 2) {
          int pRow = (NUM_OF_TILES - 1) - row;
          int pCol = (NUM_OF_TILES - 1) - col;

          if (p.getLocation().getRow() == pRow && p.getLocation().getCol() == pCol) {
            processLocation(g, r, p, row, col);
            continue;
          }
          // West rotation.
        } else if (BOARD_ROTATION % 4 == 3) {
          int pRow = col;
          int pCol = (NUM_OF_TILES - 1) - row;

          if (p.getLocation().getRow() == pRow && p.getLocation().getCol() == pCol) {
            processLocation(g, r, p, row, col);
            continue;
          }
        }

        Item i = null;

        // North rotation.
        if (BOARD_ROTATION % 4 == 0) {
          i = items.get(r.getLocation(row, col));
          // East rotation.
        } else if (BOARD_ROTATION % 4 == 1) {
          i = items.get(r.getLocation((NUM_OF_TILES - 1) - col, row));
          // South rotation.
        } else if (BOARD_ROTATION % 4 == 2) {
          i = items.get(r.getLocation((NUM_OF_TILES - 1) - row, (NUM_OF_TILES - 1) - col));
          // West rotation.
        } else if (BOARD_ROTATION % 4 == 3) {
          i = items.get(r.getLocation(col, (NUM_OF_TILES - 1) - row));
        }

        processLocation(g, r, i, row, col);
      }
    }

  }

  /**
   * Processes the location by extracting the correct image to display for the
   * state of the player object at the given location. Player is never null.
   *
   * @param g
   *          the graphics
   * @param r
   *          the room
   * @param p
   *          the player
   * @param row
   *          the row
   * @param col
   *          the col
   */
  private void processLocation(Graphics g, Room r, Player p, int row, int col) {
    //
    // start of the url of the image.
    String url = "src/renderer/data/";
    String name = "player";

    int itemRotation = 0;

    switch (p.getDirection()) {
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

    // combines the local direction of the item with the global rotation of the
    // board and direct the url to the appropriate subfolder.
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

    // append the name of the item to the url.
    url += name;

    // append the file extension to the url.
    url += ".png";

    drawImageAtLocation(g, url.toLowerCase(), row, col);
  }

  /**
   * Processes the location by extracting the correct image to display for the
   * state of the item object at the given location. Item can be null.
   *
   * @param g
   *          the graphics
   * @param r
   *          the room
   * @param i
   *          the item
   * @param row
   *          the row
   * @param col
   *          the col
   */
  private void processLocation(Graphics g, Room r, Item i, int row, int col) {

    // if there is no item at the specified location, draw the floor tile image.
    if (i == null) {
      drawImageAtLocation(g, "src/renderer/data/else/floor.png", row, col);
      return;
    }

    // start of the url of the image.
    String url = "src/renderer/data/";
    String name = i.getName().toLowerCase();

    if (name.equals("wall") && hideWalls) {
      drawImageAtLocation(g, "src/renderer/data/else/wall_hidden.png", row, col);
      return;
    }

    int itemRotation = 0;

    // the following items are symmetrical from all views so only one image is used.
    if (name.equals("potion") || name.equals("rock") || name.equals("tree")
        || name.equals("wall")) {
      url += "else/";
    } else { // the following items appear different based on the viewing direction.
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

      // combines the local direction of the item with the global rotation of the
      // board and direct the url to the appropriate subfolder.
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

    // append the name of the item to the url.
    url += name;

    // special cases for items with differing view's based on the item's current
    // state.
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
      if (d.isOpen()) {
        url += "_open";
      } else {
        url += "_closed";
      }
    }

    // append the file extension to the url.
    url += ".png";

    drawImageAtLocation(g, url.toLowerCase(), row, col);
  }

  /**
   * Draw image at location.
   *
   * @param g
   *          the graphics
   * @param fileName
   *          the file name
   * @param row
   *          the row
   * @param col
   *          the col
   */
  private void drawImageAtLocation(Graphics g, String fileName, int row, int col) {

    // map the images by file name so that images are only loaded once.
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
    Image scaledImage = image.getScaledInstance((int) imageWidth, (int) imageHeight,
        Image.SCALE_SMOOTH);

    g.drawImage(scaledImage, (int) x, (int) (y - scaledImage.getHeight(null)), null);
  }

  /**
   * Calculate the isometric polygon that represents the specified location.
   *
   * @param row
   *          the row
   * @param col
   *          the col
   * @return the isometric polygon
   */
  private Polygon getIsometricPolygon(int row, int col) {
    int x = col;
    int y = row;

    // top coordinate.
    int x1 = (int) (((x + 0) - (y + 0)) * TILE_WIDTH);
    int y1 = (int) (((x + 0) + (y + 0)) * TILE_HEIGHT);

    // right coordinate.
    int x2 = (int) (((x + 1) - (y + 0)) * TILE_WIDTH);
    int y2 = (int) (((x + 1) + (y + 0)) * TILE_HEIGHT);

    // bottom coordinate.
    int x3 = (int) (((x + 1) - (y + 1)) * TILE_WIDTH);
    int y3 = (int) (((x + 1) + (y + 1)) * TILE_HEIGHT);

    // left coordinate.
    int x4 = (int) (((x + 0) - (y + 1)) * TILE_WIDTH);
    int y4 = (int) (((x + 0) + (y + 1)) * TILE_HEIGHT);

    Polygon p = new Polygon();
    p.addPoint(CENTER_X + x1, CENTER_Y + y1);
    p.addPoint(CENTER_X + x2, CENTER_Y + y2);
    p.addPoint(CENTER_X + x3, CENTER_Y + y3);
    p.addPoint(CENTER_X + x4, CENTER_Y + y4);

    return p;
  }

  /**
   * Gets the clicked location.
   *
   * @param e
   *          the mouse event
   * @return the clicked location
   */
  private Location getClickedLocation(MouseEvent e) {
    for (int row = 0; row < NUM_OF_TILES; row++) {
      for (int col = 0; col < NUM_OF_TILES; col++) {
        // North rotation.
        if (BOARD_ROTATION % 4 == 0) {
          if (getIsometricPolygon(row, col).contains(e.getPoint())) {
            return new Location(row, col);
          }
          // East rotation.
        } else if (BOARD_ROTATION % 4 == 1) {
          if (getIsometricPolygon(col, (NUM_OF_TILES - 1) - row).contains(e.getPoint())) {
            return new Location(row, col);
          }
          // South rotation.
        } else if (BOARD_ROTATION % 4 == 2) {
          if (getIsometricPolygon((NUM_OF_TILES - 1) - row, (NUM_OF_TILES - 1) - col)
              .contains(e.getPoint())) {
            return new Location(row, col);
          }
          // West rotation.
        } else if (BOARD_ROTATION % 4 == 3) {
          if (getIsometricPolygon((NUM_OF_TILES - 1) - col, row).contains(e.getPoint())) {
            return new Location(row, col);
          }
        }
      }
    }

    return null;
  }

  /**
   * Process mouse release. Used to return the coordinates of a clicked location
   * (left-click), and to rotate the board (right-click).
   *
   * @param e
   *          the mouse event
   * @return the location or null (if no location was clicked)
   */
  public Location doRelease(MouseEvent e) {
    if(e.getButton() == 2) { // middle button
      this.hideWalls = !hideWalls;
    }

    this.mouseDown = false;

    if (e.getButton() == 1) { // left click
      return getClickedLocation(e);
    } else if (e.getButton() == 3) { // right click
      this.BOARD_ROTATION++;
    }

    return null;
  }

  /**
   * Process mouse press. Used to coordinate the dragging motion.
   *
   * @param e
   *          the mouse event
   */
  public void doPress(MouseEvent e) {
    this.mouseDown = true;
    this.mouseLocation = e.getPoint();
  }

  /**
   * Process mouse drag. Translates the board, left, right, up, and down.
   *
   * @param e
   *          the mouse event
   */
  public void doDrag(MouseEvent e) {
    if (mouseDown) {
      double dx = mouseLocation.getX() - e.getPoint().getX();
      double dy = mouseLocation.getY() - e.getPoint().getY();

      this.CENTER_X -= dx;
      this.CENTER_Y -= dy;
      this.mouseLocation = e.getPoint();
    }
  }

  /**
   * Process mouse scroll. Zooms the board in and out.
   *
   * @param e
   *          the mouse event
   */
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
