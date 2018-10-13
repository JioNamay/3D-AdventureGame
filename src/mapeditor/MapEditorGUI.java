package mapeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import gameworld.tiles.Assets;

public class MapEditorGUI {

	private JFrame frame;
	private JLayeredPane boardPanel;
	private JPanel buttonPanel;
	private JButton rockButton;
	private JButton sofaButton;
	private JButton tableButton;
	private JButton treeButton;
	private JButton noteButton;
	private JButton bedButton;
	private JButton fountainButton;
	private JButton cactusButton;
	private JButton bookshelfButton;
	private JButton treasureChestButton;
	private JButton wallBlockButton;
	private JButton doorButton;
	private JButton deleteButton;
	private JLabel roomSelectLabel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem saveMenuItem;
	private JMenuItem loadMenuItem;

	protected String roomSelected = "Library";
	protected String selectedItem = " ";
	protected boolean deleteMode = false;

	private int[][] roomMap;

	private mapeditor.MapEditorGUI.BoardPanel.BoardPanelComponent pressedOn;
	private mapeditor.MapEditorGUI.BoardPanel.BoardPanelComponent releasedOn;

	private ArrayList<mapeditor.MapEditorGUI.BoardPanel.BoardPanelComponent> boardTiles;

	public MapEditorGUI() {
		boardTiles = new ArrayList<mapeditor.MapEditorGUI.BoardPanel.BoardPanelComponent>();

		roomMap = new int[7][7];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				roomMap[i][j] = 0;
			}
		}
		GUI();
	}

	public void GUI() {

		frame = new JFrame("Map Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setSize(700, 500);

		/* MENU BAR */
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		saveMenuItem = new JMenuItem("Save");
		loadMenuItem = new JMenuItem("Load");
		fileMenu.add(saveMenuItem);
		fileMenu.add(loadMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		/* MENU BAR */

		/* BOARD PANEL */
		boardPanel = new BoardPanel();
		boardPanel.setBounds(0, 0, 280, 280);
		// boardPanel.setPreferredSize(new Dimension(280, 280));
		((BoardPanel) boardPanel).addTiles();
		frame.add(boardPanel/* , BorderLayout.CENTER */);
//		boardPanel.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//				selectedItem = "";
//				boardPanel.repaint();
//			}
//		});
		/* BOARD */

		/* BUTTON PANEL */
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		/* BUTTON PANEL */

		/* ROOM SELECTION */
		roomSelectLabel = new JLabel("Select room to edit:");

		String[] roomStrings = { "Library", "Guest Bedroom", "Foyer", "Courtyard", "Master Bedroom", "Kitchen" };

		JComboBox petList = new JComboBox(roomStrings);
		petList.setSelectedIndex(0);
		petList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String roomName = (String) cb.getSelectedItem();
				roomSelected = roomName;
				// (roomSelected);
			}

		});

		roomSelectLabel.setBounds(300, 0, 500, 60);
		buttonPanel.add(roomSelectLabel);
		petList.setBounds(427, 0, 170, 60);
		buttonPanel.add(petList);
		/* ROOM SELECTION */

		rockButton = new JButton("[1] Rock (contains key)");
		rockButton.setBounds(300, 60, 170, 60);
		rockButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "rock";
				turnDeleteModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(rockButton);

		sofaButton = new JButton("[2] Sofa");
		sofaButton.setBounds(470, 60, 170, 60);
		sofaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "sofa";
				turnDeleteModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(sofaButton);

		tableButton = new JButton("[3] Table");
		tableButton.setBounds(300, 120, 170, 60);
		tableButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "table";
				turnDeleteModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(tableButton);

		treeButton = new JButton("[4] Tree");
		treeButton.setBounds(470, 120, 170, 60);
		treeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "tree";
				turnDeleteModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(treeButton);

		bedButton = new JButton("[5] Bed");
		bedButton.setBounds(300, 180, 170, 60);
		bedButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "bed";
				turnDeleteModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(bedButton);

		fountainButton = new JButton("[6] Fountain");
		fountainButton.setBounds(470, 180, 170, 60);
		fountainButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "fountain";
				turnDeleteModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(fountainButton);

		cactusButton = new JButton("[7] Cactus");
		cactusButton.setBounds(300, 240, 170, 60);
		cactusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "cactus";
				turnDeleteModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(cactusButton);

		bookshelfButton = new JButton("[8] Bookshelf");
		bookshelfButton.setBounds(470, 240, 170, 60);
		bookshelfButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "bookshelf";
				turnDeleteModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(bookshelfButton);

		treasureChestButton = new JButton("[9] Treasure Chest");
		treasureChestButton.setBounds(300, 300, 170, 60);
		treasureChestButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "treasure chest";
				turnDeleteModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(treasureChestButton);

		wallBlockButton = new JButton("[10] Wall Block");
		wallBlockButton.setBounds(470, 300, 170, 60);
		wallBlockButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "wall block";
				turnDeleteModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(wallBlockButton);

		noteButton = new JButton("[11] Note");
		noteButton.setBounds(300, 360, 170, 60);
		noteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "note";
				turnDeleteModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(noteButton);

		doorButton = new JButton("[12] Door");
		doorButton.setBounds(470, 360, 170, 60);
		doorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "door";
				turnDeleteModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(doorButton);

		deleteButton = new JButton("Delete Mode: Off");
		deleteButton.setBounds(40 * 7 / 2 - 170 / 2, 40 * 7 + 30, 170, 60);
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteMode = !deleteMode;
				if (deleteMode == true) {
					selectedItem = " ";
					deleteButton.setText("Delete Mode: On");
					boardPanel.repaint();
				} else {
					deleteButton.setText("Delete Mode: Off");
				}
			}

		});
		buttonPanel.add(deleteButton);

		frame.add(buttonPanel);
		frame.setVisible(true);
	}

	public void turnDeleteModeOff() {
		deleteMode = false;
		deleteButton.setText("Delete Mode: Off");
	}

	public int getItemAsInt(String item) {
		switch (item) {
		case "rock":
			return 1;
		case "sofa":
			return 2;
		case "table":
			return 3;
		case "tree":
			return 4;
		case "bed":
			return 5;
		case "fountain":
			return 6;
		case "cactus":
			return 7;
		case "bookshelf":
			return 8;
		case "treasure chest":
			return 9;
		case "wall block":
			return 10;
		case "note":
			return 11;
		case "door":
			return 12;
		}
		return -1;
	}

	private class BoardPanel extends JLayeredPane {

		public void addTiles() {
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 7; j++) {
					BoardPanelComponent bpc = new BoardPanelComponent(new Rectangle(j * 40, i * 40, 40, 40), i, j);
					boardPanel.add(bpc);
					boardTiles.add(bpc);
				}
			}
		}

		public void paintComponent(Graphics g) {
//
////			if (selectedItem == "") {
////				g.setColor(Color.WHITE);
////			} else {
////				g.setColor(Color.CYAN);
////			}
////			g.fillRect(0, 0, 7 * 40, 7 * 40);
////			g.setColor(Color.GRAY);
////
////			for (int i = 0; i < 7; i++) {
////				g.drawLine(i * 40, 0, i * 40, 7 * 40);
////			}
////
////			for (int j = 0; j < 7; j++) {
////				g.drawLine(0, j * 40, 7 * 40, j * 40);
////			}
////
////			g.setColor(Color.BLACK);
////			g.drawLine(0, 0, 7 * 40, 0);
////			g.drawLine(0, 0, 0, 7 * 40);
////			g.drawLine(7 * 40, 0, 7 * 40, 7 * 40);
////			g.drawLine(0, 7 * 40, 7 * 40, 7 * 40);
//
//			map[0][0] = 'R';
//			map[0][1] = 'R';
//			map[0][2] = 'R';

			for (BoardPanelComponent tile : boardTiles) {
				tile.paintComponent(g);
			}

//			for (int i = 0; i < 7; i++) {
//				for (int j = 0; j < 7; j++) {
//					g.setColor(Color.WHITE);
//					if (selectedItem != "" && map[i][j] == ' ') {
//						g.setColor(Color.CYAN);
//					}
//					g.fillRect(j * 40, i * 40, 40, 40);
//					g.setColor(Color.BLACK);
//					g.drawRect(j * 40, i * 40, 40, 40);
//				}
//			}
		}

		class BoardPanelComponent extends JLayeredPane implements MouseListener {

			private String item = " ";
			public int x;
			public int y;

			public BoardPanelComponent(Rectangle bounds, int x, int y) {
				this.x = x;
				this.y = y;
				setBounds(bounds);
				addMouseListener(this);
			}

			public void paintComponent(Graphics g) {

				if (!selectedItem.equals(" ") && roomMap[x][y] == 0) {
					g.setColor(Color.CYAN);
				} else if (selectedItem.equals(" ") && roomMap[x][y] == 0) {
					g.setColor(Color.WHITE);
				} else if (selectedItem.equals(" ")) {
					g.setColor(Color.RED);
				}

				g.fillRect(getBounds().x, getBounds().y, getWidth(), getHeight());
				g.setColor(Color.BLACK);
				g.drawRect(getBounds().x, getBounds().y, getWidth(), getHeight());
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (!selectedItem.equals(" ")) {
					item = selectedItem;
					roomMap[x][y] = getItemAsInt(selectedItem);
					selectedItem = " ";
					System.out.println(Arrays.deepToString(roomMap).replace("], ", "]\n"));
					System.out.println("");
					boardPanel.repaint();
				}

				if (deleteMode == true) {
					roomMap[x][y] = 0;
					System.out.println(Arrays.deepToString(roomMap).replace("], ", "]\n"));
					System.out.println("");
					boardPanel.repaint();
				}

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

	}
}