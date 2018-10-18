package gameworld.entities;

import gameworld.Location;
import gameworld.Location.Direction;
import gameworld.Room;

/**
 * The player class. There can only be one instance of the player.
 *
 * @author Deanne Alabastro 300346210
 */
public class Player extends AttackingEntity {


  /** The inventory. */
  private Inventory inventory;

  /** The location. */
  private Location location;

  /** The current room. */
  private Room currentRoom;

  /** The selected item. */
  private PickUpAbleStrategy selectedItem;

  /** The equiped weapon. */
  private EquipableStrategy equippedWeapon;

  /** The coins. */
  private int coins = 0;

  /** The coins to throw in fountain. */
  private int fountainCoins = 0;

  /** The direction. */
  private Direction direction;

  // ********** SINGLETON PATTERN ********** //
  /** The only instance of player. */
  private static Player instance = new Player();

  /**
   * Instantiates a new player - private to ensure that only one player is ever
   * made.
   */
  private Player() {
    this.health = 100;
    this.maxDamage = 5;
    inventory = new Inventory();
    direction = Direction.NORTH;
  }

  /**
   * Gets the single instance of Player.
   *
   * @return single instance of Player
   */
  public static Player getInstance() {
    return instance;
  }

  /**
   * Resets the players fields.
   */
  public void resetPlayer() {
    this.health = 100;
    this.maxDamage = 5;
    inventory = new Inventory();
    coins = 0;
    equippedWeapon = null;
    selectedItem = null;
    currentRoom = null;
    location = null;
    direction = Direction.NORTH;
  }

  /**
   * Returns the direction.
   * @return the direction
   */
  public Direction getDirection() {
    return direction;
  }

  /**
   * Sets the direction.
   * @param direction
   *          the direction to set
   */
  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  /**
   * Gets the coins.
   *
   * @return the coins
   */
  public int getCoins() {
    return coins;
  }

  /**
   * Sets the number of coins the user wants to throw into the fountain.
   *
   * @param coins the coins to set
   */
  public void addFountainCoins(int coins) {
    this.setFountainCoins(this.getFountainCoins() + coins);
  }

  /**
   * Return fountain coins.
   * @return the fountainCoins
   */
  public int getFountainCoins() {
    return fountainCoins;
  }

  /**
   * Set fountain coins.
   * @param fountainCoins the fountainCoins to set
   */
  public void setFountainCoins(int fountainCoins) {
    this.fountainCoins = fountainCoins;
  }

  /**
   * Adds the coins.
   *
   * @param coins
   *          the coins to set
   */
  public void addCoins(int coins) {
    this.coins += coins;
  }
  
  /**
   * Removes the coins.
   *
   * @param coins the coins
   */
  public void removeCoins(int coins) {
    this.coins -= coins;
  }

  /**
   * Sets the inventory.
   *
   * @param inventory
   *          the new inventory
   */
  public void setInventory(Inventory inventory) {
    if (this.inventory == null) {
      this.inventory = inventory;
    }
  }

  /**
   * Gets the inventory.
   *
   * @return the inventory
   */
  public Inventory getInventory() {
    return this.inventory;
  }

  /**
   * Sets the attack damage.
   */
  private void setAttackDamage() {
    if (equippedWeapon == null) {
      // player uses fist
      this.maxDamage = 5;
    } else {
      this.maxDamage = equippedWeapon.getAttackDamage();
    }
  }

  // ********** INHERITED "ABSTRACT" METHODS ********** //

  /*
   * (non-Javadoc)
   *
   * @see gameworld.entities.Entity#die()
   */
  @Override
  public void die() {
    // haven't implemented so just end game.
    System.out.println("Player died. End game");
    System.exit(0);
  }

  /**
   * Recover.
   *
   * @param amount
   *          the amount
   */
  public void recover(int amount) {
    health = ((this.health + amount) > 100) ? 100 : health + amount;
  }

  /**
   * Gets the current room.
   *
   * @return the currentRoom
   */
  public Room getCurrentRoom() {
    return currentRoom;
  }

  /**
   * Sets the current room.
   *
   * @param currentRoom
   *          the currentRoom to set
   */
  public void setCurrentRoom(Room currentRoom) {
    if (this.currentRoom != null) {
      this.currentRoom.setHasPlayer(false); // tell the old room that it no longer has player
    }
    this.currentRoom = currentRoom;
    currentRoom.setHasPlayer(true);

  }

  /**
   * Gets the location.
   *
   * @return the location
   */
  public Location getLocation() {
    return location;
  }

  /**
   * Sets the location.
   *
   * @param location
   *          the location to set
   */
  public void setLocation(Location location) {
    this.location = location;
  }

  /**
   * Gets the selected item.
   *
   * @return the selectedItem
   */
  public PickUpAbleStrategy getSelectedItem() {
    return selectedItem;
  }

  /**
   * Sets the selected item.
   *
   * @param selectedItem
   *          the selectedItem to set
   */
  public void setSelectedItem(PickUpAbleStrategy selectedItem) {
    this.selectedItem = selectedItem;
  }

  /**
   * Gets the equipped weapon.
   *
   * @return the equipedWeapon
   */
  public EquipableStrategy getEquippedWeapon() {
    return equippedWeapon;
  }

  /**
   * Sets the equipped weapon.
   *
   * @param equippedWeapon
   *          the equipedWeapon to set
   */
  public void setEquippedWeapon(EquipableStrategy equippedWeapon) {
    this.equippedWeapon = equippedWeapon;
    setAttackDamage(); // update player's attack damage
  }

  /**
   * Method returns the player stats:
   *  max damage, health, no. of coins, equipped weapon
   */
  @Override
  public String toString() {
    String info = "";
    info += "Health: " + health + "\n";
    info += "Max attack damage: " + maxDamage + "\n";
    info += "No. of coins: " + coins + "\n";
    
    String weapon = (equippedWeapon == null) ? "none" : equippedWeapon.getName();
    info += "Weapon equipped: " + weapon + "\n";
    
    return info;
  }
}
