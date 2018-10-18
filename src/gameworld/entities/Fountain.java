package gameworld.entities;

import gameworld.entities.Item.Action;
import java.util.Arrays;
import java.util.List;



/**
 * Fountain that gives players things based on amount of coins thrown.
 * @author Deanne Alabastro 300346210
 *
 */
public class Fountain extends CoinBank {

  /**
   * Instantiates a new fountain.
   */
  public Fountain() {
    this.description = "beautiful foutain";
    this.name = "Fountain";
    this.coinBank = 1000;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#getActions()
   */
  @Override
  public List<String> getActions() {
    return Arrays.asList(Action.EXAMINE.toString(), Action.THROWCOINS.toString());
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#isSolid()
   */
  @Override
  public boolean isSolid() {
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gameworld.entities.Strategy#isDoor()
   */
  @Override
  public boolean isDoor() {
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * gameworld.entities.Strategy#performAction(gameworld.entities.Item.Action)
   */
  @Override
  public String performAction(Action action) {
    switch (action) {
      case THROWCOINS:
        return grantWish();
      default:
        return description;
    }
  }

  private String grantWish() {
    int coins = Player.getInstance().getFountainCoins();
    Player.getInstance().setFountainCoins(0);
    Player.getInstance().removeCoins(coins);
    Inventory in = Player.getInstance().getInventory();
    if(in.isFull()) {
      Player.getInstance().addCoins(10);
      return "Player got coins";
    }else {
      // if coins thrown between 0 - 5return stick
      if(coins > 1 && coins < 6) {
        in.add(new Stick());
        return "Player got Stick";
      }else if(coins > 6 && coins < 11) {
        // if coins thrown between 6 - 10 return coins
        Player.getInstance().addCoins(10);
        return "Player got coins";
      }else if(coins > 11 && coins < 16) {
        // if coins thrown between 11 - 15 return rock
        in.add(new Rock());
        return "Player got Rock";
      }else if(coins > 16 && coins < 26) {
        // if coins thrown between 16-25 return heavyBook
        in.add(new HeavyBook());
        return "Player got Heavy Book";
      }else if(coins > 26 && coins < 32) {  
        // if coins thrown between 26-31 return note 
        // if coins thrown between 26-31 return note
        in.add(new Note());
        return "Player got Note";
      }else if(coins > 32 && coins < 46) {
        // if coins thrown between 32-45 return key
        in.add(new Key());
        return "Player got Key";
      }else{
        // if coins thrown greater than 45 return potion
        in.add(new Potion());
        return "Player got Potion";
      }
         
    }
  
  }

}
