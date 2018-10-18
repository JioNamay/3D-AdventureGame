package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import adventuregame.AdventureGame;
import gameworld.entities.Item.Action;
import gameworld.entities.PickUpAbleStrategy;

/**
 * This class specifies the individual JComponents that are to be placed within
 * the inventory area, in the application window. The InventoryDisplay will draw
 * the image of each of the items in the player's inventory.
 *
 * @author yangcarr
 */
public class InventoryDisplay extends JComponent implements MouseListener {

  // the width and height of an individual area within the inventory panel
  public static final int IMAGE_WIDTH = 410 / 5;
  public static final int IMAGE_HEIGHT = 110 / 2;

  private PickUpAbleStrategy item;
  private boolean isSelected; 

  /**
   * An instance of this JComponent needs to know what item it represents.
   * 
   * @param item
   *          that the area displays
   */
  public InventoryDisplay(PickUpAbleStrategy item) {
    this.item = item;
    addMouseListener(this);
  }

  // GETTERS
  /** Determines whether the user clicked on this component */
  public boolean isSelected() {
    return isSelected;
  }

  public PickUpAbleStrategy getItem() {
    return item;
  }

  public void setSelected(boolean isSelected) {
    this.isSelected = isSelected;
  }

  /**
   * Highlights the selected component when user clicks.
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    /*
     * System.out.println("item selected: " + item.getDescription());
     * //setBorder(BorderFactory.createLineBorder(Color.GREEN)); if
     * (AdventureGame.getSelectedItem() == null) { isSelected = true;
     * AdventureGame.setSelectedItem(this); } else { // de-select previous item, and
     * set selected item to be this component
     * 
     * }
     */

  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // ============== in game ===============
    // generate tooltip here
    // when user clicks on location on drawing area, get the location that the user
    // clicks on
    // if location null (or location item is null), then return
    // otherwise get the item at the location and display its actions as
    // ======================================

    // create the action menu
    // which displays the list of actions for the item
    // user selects one, calls another method to perform action on item
    // passes (Item item, String action)
    // in here, item.performAction
    AdventureGame.getSelectedItem().setSelected(false);
    AdventureGame.getSelectedItem().setBorder(BorderFactory.createEmptyBorder());
    AdventureGame.setSelectedItem(this);
    isSelected = true;

    JPopupMenu actionMenu = new JPopupMenu();
    List<String> itemActions = item.getActions(); // list of actions for the [selected?] item

    // go through the possible actions for the selected item
    for (String actions : itemActions) {
      JMenuItem action = new JMenuItem(actions);
      action.addActionListener(createItemListener(action.getText()));
      actionMenu.add(action);
    }

    actionMenu.show(this, e.getX(), e.getY());
  }

  /**
   * The action to be performed on this item. This method is only called when user
   * chooses an item from the inventory.
   *
   * @param action
   *          action to perform on item
   * @return the ActionListener set on the menu item
   */
  private ActionListener createItemListener(String action) {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String desc = item.performAction(Action.valueOf(action));
        GUI.getActionDisplay().setText(desc);

      }

    };
  }

  public void redrawInventory(AdventureGame game) {
    game.updateInventory();
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }
}
