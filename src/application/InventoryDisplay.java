package application;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import gameworld.entities.PickUpAbleStrategy;
import adventuregame.AdventureGame;

/**
 * This class specifies the individual JComponents that are to be placed within the inventory
 * area, in the application window. The InventoryDisplay will draw the image of each of the
 * items in the player's inventory.
 *
 * @author yangcarr
 */
public class InventoryDisplay extends JComponent implements MouseListener {
//public class InventoryDisplay extends JComponent {
	// the width and height of an individual area within the inventory panel
	public static final int IMAGE_WIDTH = 410/5;
	public static final int IMAGE_HEIGHT = 110/2;

	private PickUpAbleStrategy item;
	private boolean isSelected;

	/**
	 * An instance of this JComponent needs to know what item it represents.
	 * @param item that the area displays
	 */
	public InventoryDisplay(PickUpAbleStrategy item) {
		this.item = item;
		addMouseListener(this);
	}

	// GETTERS
	/** Determines whether the user clicked on this component */
	public boolean isSelected() { return isSelected; }
	public PickUpAbleStrategy getItem() { return item; }

	public void setSelected(boolean isSelected) { this.isSelected = isSelected; }

	/**
	 * Highlights the selected component when user clicks.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		/*System.out.println("item selected: " + item.getDescription());
		//setBorder(BorderFactory.createLineBorder(Color.GREEN));
		if (AdventureGame.getSelectedItem() == null) {
			isSelected = true;
			AdventureGame.setSelectedItem(this);
		}
		else {	// de-select previous item, and set selected item to be this component

		}*/

		AdventureGame.getSelectedItem().setSelected(false);
		AdventureGame.getSelectedItem().setBorder(BorderFactory.createEmptyBorder());
		AdventureGame.setSelectedItem(this);
		isSelected = true;
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
