package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import gameworld.entities.Item;
import gameworld.entities.PickUpAbleStrategy;
import gameworld.entities.Inventory;


/**
 * This class specifies the individual JComponents that are to be placed within the inventory
 * area, in the application window. The InventoryDisplay will draw the image of each of the
 * items in the player's inventory.
 *
 * @author Carrie
 */
//public class InventoryDisplay extends JComponent implements MouseListener {
public class InventoryDisplay extends JComponent {
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
	}
	
	// GETTERS
	/** Determines whether the user clicked on this component */
	public boolean isSelected() { return isSelected; }
	public PickUpAbleStrategy getItem() { return item; }
	
	/**
	 * Highlights the selected inventory item.
	 */
	public void highlight() {
		setBackground(Color.GREEN);
	}

	/**
	 * Determines whether a mouse click is on this JComponent.
	 * The x and y coordinates of the mouse are 5 less, because the image is drawn 
	 * 5 less than the actual size of the display area.
	 */
	/*@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouse click before x and y");
		int x = e.getX();
		int y = e.getY();
		System.out.println("mouse x: " + x + ", mouse y: " + y);
		isSelected = this.contains(x, y);
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

	}*/
}
