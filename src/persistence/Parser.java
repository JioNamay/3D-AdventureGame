package persistence;

import java.util.HashMap;

import javax.xml.stream.XMLStreamException;

import gameworld.GameWorld;
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

/**
 * This class is includes parser methods for parsing the GameWorld.xml file.
 * 
 * @author bennetteyee 300375341
 */
public class Parser {

  // GAME TAG
  public static final String GAME_MAP = "gamemap";

  // ROOM TAGS
  public static final String LIBRARY = "library";
  public static final String FOYER = "foyer";
  public static final String COURTYARD = "courtyard";
  public static final String STUDY = "study";

  public GameWorld parseGameWorld() throws XMLStreamException {
    String tag = GAME_MAP;
    return null;

  }

  public Room parseRoom() throws XMLStreamException {
    // parse each different room tag

    // create new room, according to that room name
    // parse door
    // parse type of item, parse x, y
    // set location of that item in room

    // create a getAttributes method of tag

    return null;

  }

  public Door parseDoor() throws XMLStreamException {
    return null;
  }

  public Item parseItems() throws XMLStreamException {
    // parse according to the type of id
    return null;

  }

  public Rock parseRock() throws XMLStreamException {
    return null;
  }

  public Sofa parseSofa() throws XMLStreamException {
    // parse according to the direction of the sofa
    return null;
  }

  public Table parseTable() throws XMLStreamException {
    return null;

  }

  public Tree parseTree() throws XMLStreamException {
    return null;
  }

  public Fountain parseFountain() throws XMLStreamException {
    return null;
  }

  public Cactus parseCactus() throws XMLStreamException {
    return null;
  }

  public Bookshelf parseBookshelf() throws XMLStreamException {
    return null;
  }

  public TreasureChest parseTreasureChest() throws XMLStreamException {
    return null;
  }

  public Wall parseWall() throws XMLStreamException {
    return null;
  }

  public Note parseNote() throws XMLStreamException {
    return null;
  }

  public HeavyBook parseHeavyBook() throws XMLStreamException {
    return null;
  }

  public Key parseKey() throws XMLStreamException {
    return null;
  }

  public Potion parsePotion() throws XMLStreamException {
    return null;
  }

  public Stick parseStick() throws XMLStreamException {
    return null;
  }

  private int getIntegerAttribute(HashMap<String, String> vals, String name, String errorMessage)
      throws XMLParseException {
    try {
      return Integer.parseInt(getStringAttribute(vals, name, errorMessage));
    } catch (NumberFormatException e) {
      throw new XMLParseException("non integer value in '" + name + "' attribute");
    }
  }

  private String getStringAttribute(HashMap<String, String> vals, String name, String errorMessage)
      throws XMLParseException {

    String val = vals.get(name);
    if (val == null)
      throw new XMLParseException(errorMessage);
    return val;
  }

}
