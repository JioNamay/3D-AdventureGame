package gameworld.entities;

/**
 * A stick that drops from a tree upon its death. It can be used as a weapon for
 * 3 hits.
 * 
 * @author Deanne Alabastro 300346210
 */
public class Stick extends EquipableStrategy {

  /**
   * Instantiates a new stick.
   */
  public Stick() {
    this.durability = 3;
    this.description = "An ordinary stick.";
    this.name = "Stick";
    this.maxDamage = 10;
    this.coinBank = 3;
  }
}
