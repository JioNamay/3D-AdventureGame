package renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import application.GUI;
import gameworld.entities.Item;
import gameworld.entities.Potion;
import gameworld.Location;

/**
 * Package: Renderer Class: Board
 *
 * @author manaentawe
 */
public class Board {
	public static int SIZE;
	public static int OFFSET;

	public static final int LOCATION_ROWS = 8;
	public static final int LOCATION_COLS = 8;
	public static int CELL_SIZE;

	private JPanel parentPanel;
	private GUI frame;

	private Location[][] locations;
	private Location clickedCell;
	public List<Item> items = new ArrayList<Item>();	// list of entity -> edit: yangcarr

	public Board(GUI frame, JPanel parentPanel) {
		this.parentPanel = parentPanel;
		this.frame = frame;
		initSize();

		int randRow = 0 + (int) (Math.random() * (LOCATION_ROWS));
		int randCol = 0 + (int) (Math.random() * (LOCATION_COLS));

		// add new Entity(new Location(randRow, randCol), new Potion()); -> edit: yangcarr
		// items.add(new Potion(new Location(randRow, randCol)));

		loadBoard();
		drawBoard();
	}

	private void initSize() {
		Board.SIZE = this.parentPanel.getWidth();
		Board.CELL_SIZE = SIZE / (LOCATION_ROWS + 2) / 2;
		Board.OFFSET = CELL_SIZE;
	}

	private void loadBoard() {
		this.locations = new Location[LOCATION_ROWS][LOCATION_COLS];

		for (int row = 0; row < LOCATION_ROWS; row++) {
			for (int col = 0; col < LOCATION_COLS; col++) {
				this.locations[row][col] = new Location(row, col);
			}
		}
	}

	private void findClickedCell(MouseEvent e) {

		// CHECK 2-D

		for (int row = 0; row < LOCATION_ROWS; row++) {
			for (int col = 0; col < LOCATION_COLS; col++) {
				int left = OFFSET + (col * CELL_SIZE);
				int right = OFFSET + (col * CELL_SIZE) + CELL_SIZE;
				int top = OFFSET + (row * CELL_SIZE);
				int bottom = OFFSET + (row * CELL_SIZE) + CELL_SIZE;

				if (left < e.getX() && e.getX() < right && top < e.getY() && e.getY() < bottom) {
					clickedCell = locations[row][col];
					System.out.println("ROW[" + row + "], COL[" + col + "]");
				}
			}
		}

		// CHECK ISOMETRIC

		for (int row = 0; row < LOCATION_ROWS; row++) {
			for (int col = 0; col < LOCATION_COLS; col++) {
				int x = col;
				int y = row;

				int x1 = (int) (OFFSET * 3 + (CELL_SIZE * 2) + (SIZE / 2) + (x * CELL_SIZE / 2) - (y * CELL_SIZE / 2));
				int y1 = (int) (OFFSET + (x * CELL_SIZE / 2) + (y * CELL_SIZE / 2));

				x = col + 1;
				y = row;

				int x2 = (int) (OFFSET * 3 + (CELL_SIZE * 2) + (SIZE / 2) + (x * CELL_SIZE / 2) - (y * CELL_SIZE / 2));
				int y2 = (int) (OFFSET + (x * CELL_SIZE / 2) + (y * CELL_SIZE / 2));

				x = col + 1;
				y = row + 1;

				int x3 = (int) (OFFSET * 3 + (CELL_SIZE * 2) + (SIZE / 2) + (x * CELL_SIZE / 2) - (y * CELL_SIZE / 2));
				int y3 = (int) (OFFSET + (x * CELL_SIZE / 2) + (y * CELL_SIZE / 2));

				x = col;
				y = row + 1;

				int x4 = (int) (OFFSET * 3 + (CELL_SIZE * 2) + (SIZE / 2) + (x * CELL_SIZE / 2) - (y * CELL_SIZE / 2));
				int y4 = (int) (OFFSET + (x * CELL_SIZE / 2) + (y * CELL_SIZE / 2));

				Polygon p = new Polygon();
				p.addPoint(x1, y1);
				p.addPoint(x2, y2);
				p.addPoint(x3, y3);
				p.addPoint(x4, y4);

				if (p.contains(e.getX(), e.getY())) {
					clickedCell = locations[row][col];
				}
			}
		}
	}

	private void drawBoard() {
		@SuppressWarnings("serial")
		JComponent drawing = new JComponent() {

			// public String clickText;
			//
			// public void setText(String s) {
			// this.clickText = s;
			// }

			protected void paintComponent(Graphics g) {
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, SIZE, SIZE);

				if (clickedCell != null) {
					int row = clickedCell.getRow();
					int col = clickedCell.getCol();

					g.setColor(Color.LIGHT_GRAY);
					g.fillRect((int) (OFFSET + col * CELL_SIZE), (int) (OFFSET + row * CELL_SIZE), CELL_SIZE,
							CELL_SIZE);
					g.drawString("'CLICK' LOCATION: ROW = " + row + ", COL = " + col, (int) (OFFSET),
							(int) (OFFSET + 50 + (LOCATION_ROWS * CELL_SIZE)));
				}

				g.setColor(Color.BLUE);
				g.drawString("'2-D'", (int) (OFFSET), (int) (OFFSET - 12.5));

				for (int row = 0; row < LOCATION_ROWS; row++) {
					for (int col = 0; col < LOCATION_COLS; col++) {
						g.setColor(Color.BLUE);
						g.drawRect((int) (OFFSET + col * CELL_SIZE), (int) (OFFSET + row * CELL_SIZE), CELL_SIZE,
								CELL_SIZE);
					}
				}

				for (Item i : items) {
					int row = i.getLocation().getRow();
					int col = i.getLocation().getCol();
					g.setColor(Color.BLUE);
					g.fillRect((int) (OFFSET + col * CELL_SIZE), (int) (OFFSET + row * CELL_SIZE), CELL_SIZE,
							CELL_SIZE);
					g.drawString("'CHEST' LOCATION: ROW = " + row + ", COL = " + col, (int) (OFFSET),
							(int) (OFFSET + 25 + (LOCATION_ROWS * CELL_SIZE)));
				}

				g.setColor(Color.RED);
				g.drawString("'ISOMETRIC'", (int) (OFFSET * 5 + (SIZE / 2) - CELL_SIZE * 4), (int) (OFFSET - 12.5));

				for (int row = 0; row < LOCATION_ROWS; row++) {
					for (int col = 0; col < LOCATION_COLS; col++) {
						int x = col;
						int y = row;

						int x1 = (int) (OFFSET * 3 + (CELL_SIZE * 2) + (SIZE / 2) + (x * CELL_SIZE / 2)
								- (y * CELL_SIZE / 2));
						int y1 = (int) (OFFSET + (x * CELL_SIZE / 2) + (y * CELL_SIZE / 2));

						x = col + 1;
						y = row;

						int x2 = (int) (OFFSET * 3 + (CELL_SIZE * 2) + (SIZE / 2) + (x * CELL_SIZE / 2)
								- (y * CELL_SIZE / 2));
						int y2 = (int) (OFFSET + (x * CELL_SIZE / 2) + (y * CELL_SIZE / 2));

						x = col + 1;
						y = row + 1;

						int x3 = (int) (OFFSET * 3 + (CELL_SIZE * 2) + (SIZE / 2) + (x * CELL_SIZE / 2)
								- (y * CELL_SIZE / 2));
						int y3 = (int) (OFFSET + (x * CELL_SIZE / 2) + (y * CELL_SIZE / 2));

						x = col;
						y = row + 1;

						int x4 = (int) (OFFSET * 3 + (CELL_SIZE * 2) + (SIZE / 2) + (x * CELL_SIZE / 2)
								- (y * CELL_SIZE / 2));
						int y4 = (int) (OFFSET + (x * CELL_SIZE / 2) + (y * CELL_SIZE / 2));

						Polygon p = new Polygon();
						p.addPoint(x1, y1);
						p.addPoint(x2, y2);
						p.addPoint(x3, y3);
						p.addPoint(x4, y4);

						if (clickedCell != null) {
							if (row == clickedCell.getRow() && col == clickedCell.getCol()) {
								g.setColor(Color.LIGHT_GRAY);
								g.fillPolygon(p);
							}
						}

						g.setColor(Color.RED);
						g.drawPolygon(p);
					}
				}

				for (Item i : items) {
					int row = i.getLocation().getRow();
					int col = i.getLocation().getCol();

					int x = col;
					int y = row;

					int x1 = (int) (OFFSET * 3 + (CELL_SIZE * 2) + (SIZE / 2) + (x * CELL_SIZE / 2)
							- (y * CELL_SIZE / 2));
					int y1 = (int) (OFFSET + (x * CELL_SIZE / 2) + (y * CELL_SIZE / 2));

					x = col + 1;
					y = row;

					int x2 = (int) (OFFSET * 3 + (CELL_SIZE * 2) + (SIZE / 2) + (x * CELL_SIZE / 2)
							- (y * CELL_SIZE / 2));
					int y2 = (int) (OFFSET + (x * CELL_SIZE / 2) + (y * CELL_SIZE / 2));

					x = col + 1;
					y = row + 1;

					int x3 = (int) (OFFSET * 3 + (CELL_SIZE * 2) + (SIZE / 2) + (x * CELL_SIZE / 2)
							- (y * CELL_SIZE / 2));
					int y3 = (int) (OFFSET + (x * CELL_SIZE / 2) + (y * CELL_SIZE / 2));

					x = col;
					y = row + 1;

					int x4 = (int) (OFFSET * 3 + (CELL_SIZE * 2) + (SIZE / 2) + (x * CELL_SIZE / 2)
							- (y * CELL_SIZE / 2));
					int y4 = (int) (OFFSET + (x * CELL_SIZE / 2) + (y * CELL_SIZE / 2));

					g.setColor(Color.RED);
					Polygon p = new Polygon();
					p.addPoint(x1, y1);
					p.addPoint(x2, y2);
					p.addPoint(x3, y3);
					p.addPoint(x4, y4);
					g.fillPolygon(p);
				}

			}
		};

		drawing.setPreferredSize(new Dimension(SIZE, SIZE));
		drawing.setVisible(true);

		drawing.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				findClickedCell(e);
				drawing.repaint();
			}
		});

		//

		// JPanel tooltips = new JPanel();
		//
		// JPanel tooltip = new JPanel();
		// tooltip.setToolTipText(/* i.getClass().getName() */ "HELLO");
		//
		// // tooltip.setBounds(OFFSET + col * CELL_SIZE, OFFSET + row * CELL_SIZE,
		// // CELL_SIZE, CELL_SIZE);
		//
		// tooltip.setPreferredSize(new Dimension(SIZE / 2, SIZE / 2));
		// tooltip.setBounds(0, 0, SIZE / 2, SIZE / 2);
		// tooltips.add(tooltip);
		//
		// for (Item i : items) {
		// int row = i.getLocation().getRow();
		// int col = i.getLocation().getCol();
		//
		// }
		//
		// tooltips.setPreferredSize(new Dimension(SIZE, SIZE));
		// tooltips.setVisible(true);

		//

		this.parentPanel.removeAll();
		this.parentPanel.add(drawing);
	}
}

