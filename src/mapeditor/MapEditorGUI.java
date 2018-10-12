package mapeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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
	private JLabel roomSelectLabel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem saveMenuItem;
	private JMenuItem loadMenuItem;

	String roomSelected = "Library";
	String selectedItem = " ";

	private char[][] map;

	private ArrayList<mapeditor.MapEditorGUI.BoardPanel.BoardPanelComponent> boardTiles;

	public MapEditorGUI() {
		boardTiles = new ArrayList<mapeditor.MapEditorGUI.BoardPanel.BoardPanelComponent>();

		map = new char[7][7];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				map[i][j] = ' ';
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

		rockButton = new JButton("Rock (contains key)");
		rockButton.setBounds(300, 60, 170, 60);
		rockButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "rock";
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(rockButton);

		sofaButton = new JButton("Sofa");
		sofaButton.setBounds(470, 60, 170, 60);
		sofaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "sofa";
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(sofaButton);

		tableButton = new JButton("Table");
		tableButton.setBounds(300, 120, 170, 60);
		tableButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "table";
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(tableButton);

		treeButton = new JButton("Tree");
		treeButton.setBounds(470, 120, 170, 60);
		treeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "tree";
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(treeButton);

		noteButton = new JButton("Note");
		noteButton.setBounds(470, 120, 170, 60);
		noteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "note";
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(noteButton);

		bedButton = new JButton("Bed");
		bedButton.setBounds(300, 180, 170, 60);
		bedButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "bed";
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(bedButton);

		fountainButton = new JButton("Fountain");
		fountainButton.setBounds(470, 180, 170, 60);
		fountainButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "fountain";
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(fountainButton);

		cactusButton = new JButton("Cactus");
		cactusButton.setBounds(300, 240, 170, 60);
		cactusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "cactus";
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(cactusButton);

		bookshelfButton = new JButton("Bookshelf");
		bookshelfButton.setBounds(470, 240, 170, 60);
		bookshelfButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "bookshelf";
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(bookshelfButton);

		treasureChestButton = new JButton("Treasure Chest");
		treasureChestButton.setBounds(300, 300, 170, 60);
		treasureChestButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "treasure chest";
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(treasureChestButton);

		wallBlockButton = new JButton("Wall Block");
		wallBlockButton.setBounds(470, 300, 170, 60);
		wallBlockButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "wall block";
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(wallBlockButton);

		doorButton = new JButton("Door");
		doorButton.setBounds(385, 360, 170, 60);
		doorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "door";
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(doorButton);

		frame.add(buttonPanel);
		frame.setVisible(true);
		// boardPanel.setBac
	}

	private class BoardPanel extends JLayeredPane {

		public void addTiles() {
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 7; j++) {
					BoardPanelComponent bpc = new BoardPanelComponent(new Rectangle(j * 40, i * 40, 40, 40));
					// bpc.setMinimumSize(new Dimension(40, 40));
					// bpc.setPreferredSize(new Dimension(, 40));
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

		class BoardPanelComponent extends JLayeredPane implements MouseMotionListener, MouseListener {

			private String item = " ";

			public BoardPanelComponent(Rectangle bounds) {
				setBounds(bounds);
				addMouseListener(this);
//				boardPanel.addMouseListener(this);
//				frame.addMouseListener(this);
			}

			public void paintComponent(Graphics g) {
				if (!selectedItem.equals(" ") && item.equals(" ")) {
					g.setColor(Color.CYAN);
				} else {
					g.setColor(Color.WHITE);
				}
				g.fillRect(getBounds().x, getBounds().y, getWidth(), getHeight());
				g.setColor(Color.BLACK);
				g.drawRect(getBounds().x, getBounds().y, getWidth(), getHeight());
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("click");
				item = selectedItem;
				repaint();
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