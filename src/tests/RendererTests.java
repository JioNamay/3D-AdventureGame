package tests;

import java.awt.Graphics;

import javax.swing.JComponent;

import org.junit.jupiter.api.Test;

import gameworld.Location;
import gameworld.Location.Direction;
import gameworld.Room;
import gameworld.entities.Bookshelf;
import gameworld.entities.Cactus;
import gameworld.entities.Fountain;
import gameworld.entities.HeavyBook;
import gameworld.entities.Item;
import gameworld.entities.Key;
import gameworld.entities.Note;
import gameworld.entities.Potion;
import gameworld.entities.Rock;
import gameworld.entities.Sofa;
import gameworld.entities.Stick;
import gameworld.entities.Strategy;
import gameworld.entities.Table;
import gameworld.entities.TreasureChest;
import gameworld.entities.Tree;
import gameworld.entities.Wall;
import renderer.Renderer;

public class RendererTests {

  public Room getListOfAllItems(Direction d) {
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

    for (int row = 0; row < Room.SIZE; row++) {
      for (int col = 0; col < Room.SIZE; col++) {
        Strategy i = (Strategy) r.getGameItems().get(new Location(row, col));
        i.setDirection(d);
      }
    }

    return r;
  }

  @Test
  public void testLoadedCorrectImages() {
    Renderer render = new Renderer();

    JComponent drawing = new JComponent() {
      protected void paintComponent(Graphics g) {
        render.doDraw(g, getListOfAllItems(Direction.NORTH));
      }
    };

    drawing.repaint();
  }

  @Test
  public void testLoadedCorrectDirectionImages() {
    Renderer render = new Renderer();

    JComponent drawing = new JComponent() {
      protected void paintComponent(Graphics g) {
        for (Direction d : Direction.values()) {
          render.doDraw(g, getListOfAllItems(d));
        }
      }
    };

    drawing.repaint();
  }
}
