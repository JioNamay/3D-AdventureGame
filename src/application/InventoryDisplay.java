package application;

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
 * @author yangcarr
 */
public class InventoryDisplay extends JComponent implements MouseListener {
	// the width and height of an individual area within the inventory panel
	public static final int IMAGE_WIDTH = 410/5;
	public static final int IMAGE_HEIGHT = 110/2;

	private PickUpAbleStrategy item;

	/**
	 * An instance of this JComponent needs to know what item it represents.
	 * @param item
	 */
	public InventoryDisplay() {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
