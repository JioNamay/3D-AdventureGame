package mapeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import gameworld.Location.Direction;
import gameworld.Room;
import gameworld.entities.Bookshelf;
import gameworld.entities.Cactus;
import gameworld.entities.Door;
import gameworld.entities.HeavyBook;
import gameworld.entities.Item;
import gameworld.entities.Key;
import gameworld.entities.Note;
import gameworld.entities.Potion;
import gameworld.entities.Rock;
import gameworld.entities.Sofa;
import gameworld.entities.Stick;
import gameworld.entities.Strategy;
import gameworld.entities.Table;
import gameworld.entities.TreasureChest;
import gameworld.entities.Tree;
import gameworld.entities.Wall;

/**
 * 
 * @author namayraph 300374356
 */
public class MapEditorGUI {

	private JFrame frame;
	private JLayeredPane boardPanel;
	private JPanel buttonPanel;
	private JButton rockButton;
	private JButton sofaButton;
	private JButton tableButton;
	private JButton treeButton;
	private JButton noteButton;
	private JButton fountainButton;
	private JButton cactusButton;
	private JButton bookshelfButton;
	private JButton treasureChestButton;
	private JButton wallBlockButton;
	private JButton doorButton;
	private JButton heavyBookButton;
	private JButton keyButton;
	private JButton potionButton;
	private JButton stickButton;
	private JButton deleteButton;
	private JButton rotateButton;
	private JLabel roomSelectLabel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem saveMenuItem;
	private JMenuItem loadMenuItem;

	private BufferedImage rockImage_top;

	private BufferedImage sofaImage_top_north;
	private BufferedImage sofaImage_top_east;
	private BufferedImage sofaImage_top_south;
	private BufferedImage sofaImage_top_west;

	private BufferedImage tableImage_top_north;
	private BufferedImage tableImage_top_east;
	private BufferedImage tableImage_top_south;
	private BufferedImage tableImage_top_west;

	private BufferedImage treeImage_top;

	private BufferedImage noteImage_top;

	private BufferedImage fountainImage_top;

	private BufferedImage cactusImage_top;

	private BufferedImage bookshelfImage_top_north;
	private BufferedImage bookshelfImage_top_east;
	private BufferedImage bookshelfImage_top_south;
	private BufferedImage bookshelfImage_top_west;

	private BufferedImage treasureChestImage_top_north;
	private BufferedImage treasureChestImage_top_east;
	private BufferedImage treasureChestImage_top_south;
	private BufferedImage treasureChestImage_top_west;

	private BufferedImage wallBlockImage_top;

	private BufferedImage doorImage_top_north;
	private BufferedImage doorImage_top_east;
	private BufferedImage doorImage_top_south;
	private BufferedImage doorImage_top_west;

	private BufferedImage heavyBookImage_top;

	private BufferedImage keyImage_top;

	private BufferedImage potionImage_top;

	private BufferedImage stickImage_top;

	private BufferedImage rockImage_iso;
	private BufferedImage sofaImage_iso;
	private BufferedImage tableImage_iso;
	private BufferedImage treeImage_iso;
	private BufferedImage noteImage_iso;
	private BufferedImage fountainImage_iso;
	private BufferedImage cactusImage_iso;
	private BufferedImage bookshelfImage_iso;
	private BufferedImage treasureChestImage_iso;
	private BufferedImage wallBlockImage_iso;
	private BufferedImage doorImage_iso;
	private BufferedImage heavyBookImage_iso;
	private BufferedImage keyImage_iso;
	private BufferedImage potionImage_iso;
	private BufferedImage stickImage_iso;

	protected String currentRoom = "Library";
	protected String selectedItem = " ";
	protected boolean deleteMode = false;
	protected boolean rotateMode = false;

	private int[][] libraryMap;
	private int[][] foyerMap;
	private int[][] courtyardMap;
	private int[][] studyMap;

	private int[][] roomMap;

	private String[] roomStrings = { "Library", "Foyer", "Courtyard", "Study" };

	private ArrayList<mapeditor.MapEditorGUI.BoardPanel.BoardPanelComponent> boardTiles;

	private Room libraryRoom;
	private Room foyerRoom;
	private Room courtyardRoom;
	private Room studyRoom;

	public MapEditorGUI() {

		boardTiles = new ArrayList<mapeditor.MapEditorGUI.BoardPanel.BoardPanelComponent>();

		initializeImages();

		roomMap = new int[7][7];
		libraryMap = new int[7][7];
		foyerMap = new int[7][7];
		courtyardMap = new int[7][7];
		studyMap = new int[7][7];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				roomMap[i][j] = 0;
				libraryMap[i][j] = 0;
				foyerMap[i][j] = 0;
				courtyardMap[i][j] = 0;
				studyMap[i][j] = 0;
			}
		}
		GUI();
	}

	public void GUI() {

		frame = new JFrame("Map Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setSize(700, 600);

		/* MENU BAR */
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createRooms();
			}

		});
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

		JComboBox roomList = new JComboBox(roomStrings);
		roomList.setSelectedIndex(0);
		roomList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String roomName = (String) cb.getSelectedItem();
				String previousRoom = currentRoom;
				currentRoom = roomName;
				if (previousRoom.equals("Library")) {
					if (currentRoom.equals("Library")) {

					} else if (currentRoom.equals("Foyer")) {
						libraryMap = roomMap;
						roomMap = foyerMap;
					} else if (currentRoom.equals("Courtyard")) {
						libraryMap = roomMap;
						roomMap = courtyardMap;
					} else if (currentRoom.equals("Study")) {
						libraryMap = roomMap;
						roomMap = studyMap;
					}
				} else if (previousRoom.equals("Foyer")) {
					if (currentRoom.equals("Library")) {
						foyerMap = roomMap;
						roomMap = libraryMap;
					} else if (currentRoom.equals("Foyer")) {

					} else if (currentRoom.equals("Courtyard")) {
						foyerMap = roomMap;
						roomMap = courtyardMap;
					} else if (currentRoom.equals("Study")) {
						foyerMap = roomMap;
						roomMap = studyMap;
					}
				} else if (previousRoom.equals("Courtyard")) {
					if (currentRoom.equals("Library")) {
						courtyardMap = roomMap;
						roomMap = libraryMap;
					} else if (currentRoom.equals("Foyer")) {
						courtyardMap = roomMap;
						roomMap = foyerMap;
					} else if (currentRoom.equals("Courtyard")) {

					} else if (currentRoom.equals("Study")) {
						courtyardMap = roomMap;
						roomMap = studyMap;
					}
				} else if (previousRoom.equals("Study")) {
					if (currentRoom.equals("Library")) {
						studyMap = roomMap;
						roomMap = libraryMap;
					} else if (currentRoom.equals("Foyer")) {
						studyMap = roomMap;
						roomMap = foyerMap;
					} else if (currentRoom.equals("Courtyard")) {
						studyMap = roomMap;
						roomMap = courtyardMap;
					} else if (currentRoom.equals("Study")) {

					}
				}
				boardPanel.repaint();
				// (currentRoom);
			}

		});

		roomSelectLabel.setBounds(300, 0, 500, 60);
		buttonPanel.add(roomSelectLabel);
		roomList.setBounds(427, 0, 170, 60);
		buttonPanel.add(roomList);
		/* ROOM SELECTION */

		rockButton = new JButton("[1] Rock (contains key)");
		rockButton.setBounds(300, 60, 170, 60);
		// rockButton.setOpaque(true);
		// rockButton.setIcon(new javax.swing.ImageIcon("textures/grass_texture.jpg"));
		rockButton.setToolTipText("Rock (contains key)");
		rockButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "rock";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(rockButton);

		sofaButton = new JButton("[2] Sofa");
		sofaButton.setBounds(470, 60, 170, 60);
		sofaButton.setToolTipText("Sofa");
		sofaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "sofa";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(sofaButton);

		tableButton = new JButton("[3] Table");
		tableButton.setBounds(300, 120, 170, 60);
		tableButton.setToolTipText("Table");
		tableButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "table";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(tableButton);

		treeButton = new JButton("[4] Tree");
		treeButton.setBounds(470, 120, 170, 60);
		treeButton.setToolTipText("Tree");
		treeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "tree";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(treeButton);

		fountainButton = new JButton("[5*] Fountain");
		fountainButton.setBounds(470, 180, 170, 60);
		fountainButton.setToolTipText("Fountain");
		fountainButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "fountain";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(fountainButton);

		cactusButton = new JButton("[6] Cactus");
		cactusButton.setBounds(300, 240, 170, 60);
		cactusButton.setToolTipText("Cactus");
		cactusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "cactus";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(cactusButton);

		bookshelfButton = new JButton("[7] Bookshelf");
		bookshelfButton.setBounds(470, 240, 170, 60);
		bookshelfButton.setToolTipText("Bookshelf");
		bookshelfButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "bookshelf";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(bookshelfButton);

		treasureChestButton = new JButton("[8] Treasure Chest");
		treasureChestButton.setBounds(300, 300, 170, 60);
		treasureChestButton.setToolTipText("Treasure Chest");
		treasureChestButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "treasure chest";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(treasureChestButton);

		wallBlockButton = new JButton("[9] Wall Block");
		wallBlockButton.setBounds(470, 300, 170, 60);
		wallBlockButton.setToolTipText("Wall Block");
		wallBlockButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "wall block";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(wallBlockButton);

		noteButton = new JButton("[10] Note");
		noteButton.setBounds(300, 360, 170, 60);
		noteButton.setToolTipText("Note");
		noteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "note";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(noteButton);

		doorButton = new JButton("[11] Door");
		doorButton.setBounds(470, 360, 170, 60);
		doorButton.setToolTipText("Door");
		doorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "door";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(doorButton);

		heavyBookButton = new JButton("[12] Heavy Book");
		heavyBookButton.setBounds(300, 420, 170, 60);
		heavyBookButton.setToolTipText("Heavy Book");
		heavyBookButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "heavy book";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(heavyBookButton);

		keyButton = new JButton("[13] Key");
		keyButton.setBounds(470, 420, 170, 60);
		keyButton.setToolTipText("Key");
		keyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "key";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(keyButton);

		potionButton = new JButton("[14] Potion");
		potionButton.setBounds(300, 480, 170, 60);
		potionButton.setToolTipText("Potion");
		potionButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "potion";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(potionButton);

		stickButton = new JButton("[15] Stick");
		stickButton.setBounds(470, 480, 170, 60);
		stickButton.setToolTipText("Stick");
		stickButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "stick";
				turnDeleteModeOff();
				turnRotateModeOff();
				// (selectedItem);
				boardPanel.repaint();
			}

		});
		buttonPanel.add(stickButton);

		deleteButton = new JButton("Delete Mode: Off");
		deleteButton.setBounds(40 * 7 / 2 - 170 / 2, 40 * 7 + 30, 170, 60);
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteMode = !deleteMode;
				if (deleteMode == true) {
					turnRotateModeOff();
					selectedItem = " ";
					deleteButton.setText("Delete Mode: On");
					boardPanel.repaint();
				} else {
					deleteButton.setText("Delete Mode: Off");
				}
			}

		});
		buttonPanel.add(deleteButton);

		rotateButton = new JButton("Rotate Mode: Off");
		rotateButton.setBounds(40 * 7 / 2 - 170 / 2, 40 * 7 + 30 + 60, 170, 60);
		rotateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rotateMode = !rotateMode;
				if (rotateMode == true) {
					turnDeleteModeOff();
					selectedItem = " ";
					rotateButton.setText("Rotate Mode: On");
					boardPanel.repaint();
				} else {
					rotateButton.setText("Rotate Mode: Off");
				}
			}

		});
		buttonPanel.add(rotateButton);

		frame.add(buttonPanel);
		frame.setVisible(true);
	}

	public void createRooms() {
		libraryRoom = new Room("Library");
		foyerRoom = new Room("Foyer");
		courtyardRoom = new Room("Courtyard");
		studyRoom = new Room("Study");
		for (int row = 0; row < 7; row++) {
			for (int col = 0; col < 7; col++) {
				if (libraryMap[row][col] != 0) {
					libraryRoom.addGameItem(row, col, new Item(getIntAsStrategy(roomMap[row][col])));
				}
				if (foyerMap[row][col] != 0) {
					foyerRoom.addGameItem(row, col, new Item(getIntAsStrategy(roomMap[row][col])));
				}
				if (courtyardMap[row][col] != 0) {
					courtyardRoom.addGameItem(row, col, new Item(getIntAsStrategy(roomMap[row][col])));
				}
				if (studyMap[row][col] != 0) {
					studyRoom.addGameItem(row, col, new Item(getIntAsStrategy(roomMap[row][col])));
				}
			}
		}
	}

	public void initializeImages() {
		try {

			rockImage_top = ImageIO.read(new File("textures/test_north.jpg"));

			sofaImage_top_north = ImageIO.read(new File("textures/test_north.jpg"));
			sofaImage_top_east = ImageIO.read(new File("textures/test_east.jpg"));
			sofaImage_top_south = ImageIO.read(new File("textures/test_south.jpg"));
			sofaImage_top_west = ImageIO.read(new File("textures/test_west.jpg"));

			tableImage_top_north = ImageIO.read(new File("textures/test_north.jpg"));
			tableImage_top_east = ImageIO.read(new File("textures/test_east.jpg"));
			tableImage_top_south = ImageIO.read(new File("textures/test_south.jpg"));
			tableImage_top_west = ImageIO.read(new File("textures/test_west.jpg"));

			treeImage_top = ImageIO.read(new File("textures/test_north.jpg"));

			noteImage_top = ImageIO.read(new File("textures/test_north.jpg"));

			fountainImage_top = ImageIO.read(new File("textures/test_north.jpg"));

			cactusImage_top = ImageIO.read(new File("textures/test_north.jpg"));

			bookshelfImage_top_north = ImageIO.read(new File("textures/test_north.jpg"));
			bookshelfImage_top_east = ImageIO.read(new File("textures/test_east.jpg"));
			bookshelfImage_top_south = ImageIO.read(new File("textures/test_south.jpg"));
			bookshelfImage_top_west = ImageIO.read(new File("textures/test_west.jpg"));

			treasureChestImage_top_north = ImageIO.read(new File("textures/test_north.jpg"));
			treasureChestImage_top_east = ImageIO.read(new File("textures/test_east.jpg"));
			treasureChestImage_top_south = ImageIO.read(new File("textures/test_south.jpg"));
			treasureChestImage_top_west = ImageIO.read(new File("textures/test_west.jpg"));

			wallBlockImage_top = ImageIO.read(new File("textures/test_north.jpg"));

			doorImage_top_north = ImageIO.read(new File("textures/test_north.jpg"));
			doorImage_top_east = ImageIO.read(new File("textures/test_east.jpg"));
			doorImage_top_south = ImageIO.read(new File("textures/test_south.jpg"));
			doorImage_top_west = ImageIO.read(new File("textures/test_west.jpg"));

			heavyBookImage_top = ImageIO.read(new File("textures/test_north.jpg"));
			keyImage_top = ImageIO.read(new File("textures/test_north.jpg"));
			potionImage_top = ImageIO.read(new File("textures/test_north.jpg"));
			stickImage_top = ImageIO.read(new File("textures/test_north.jpg"));

			rockImage_iso = ImageIO.read(new File("textures/test_north.jpg"));
			sofaImage_iso = ImageIO.read(new File("textures/test_north.jpg"));
			tableImage_iso = ImageIO.read(new File("textures/test_north.jpg"));
			treeImage_iso = ImageIO.read(new File("textures/test_north.jpg"));
			noteImage_iso = ImageIO.read(new File("textures/test_north.jpg"));
			fountainImage_iso = ImageIO.read(new File("textures/test_north.jpg"));
			cactusImage_iso = ImageIO.read(new File("textures/test_north.jpg"));
			bookshelfImage_iso = ImageIO.read(new File("textures/test_north.jpg"));
			treasureChestImage_iso = ImageIO.read(new File("textures/test_north.jpg"));
			wallBlockImage_iso = ImageIO.read(new File("textures/test_north.jpg"));
			doorImage_iso = ImageIO.read(new File("textures/test_north.jpg"));
			heavyBookImage_iso = ImageIO.read(new File("textures/test_north.jpg"));
			keyImage_iso = ImageIO.read(new File("textures/test_north.jpg"));
			potionImage_iso = ImageIO.read(new File("textures/test_north.jpg"));
			stickImage_iso = ImageIO.read(new File("textures/test_north.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void turnDeleteModeOff() {
		deleteMode = false;
		deleteButton.setText("Delete Mode: Off");
	}

	public void turnRotateModeOff() {
		rotateMode = false;
		rotateButton.setText("Rotate Mode: Off");
	}

	public int getItemAsInt(String item) {
		switch (item) {
		case "rock":
			return 1;
		case "sofa":
			return 2001;
		case "table":
			return 3001;
		case "tree":
			return 4;
		case "fountain":
			return 5;
		case "cactus":
			return 6;
		case "bookshelf":
			return 7001;
		case "treasure chest":
			return 8001;
		case "wall block":
			return 9;
		case "note":
			return 10;
		case "door":
			return 1101;
		case "heavy book":
			return 12;
		case "key":
			return 13;
		case "potion":
			return 14;
		case "stick":
			return 15;
		}
		return -1;
	}

	public Strategy getIntAsStrategy(int integer) {
		switch (integer) {
		case 1:
			return new Rock();

		case 2001: // north facing sofa
			return new Sofa();
		case 2002: // east facing sofa
			Sofa sofa_east = new Sofa();
			sofa_east.setDirection(Direction.EAST);
			return sofa_east;
		case 2003: // south facing sofa
			Sofa sofa_south = new Sofa();
			sofa_south.setDirection(Direction.SOUTH);
			return sofa_south;
		case 2004: // west facing sofa
			Sofa sofa_west = new Sofa();
			sofa_west.setDirection(Direction.WEST);
			return sofa_west;

		case 3001: // north facing table
			return new Table();
		case 3002: // east facing table
			Table table_east = new Table();
			table_east.setDirection(Direction.EAST);
			return table_east;
		case 3003: // south facing table
			Table table_south = new Table();
			table_south.setDirection(Direction.SOUTH);
			return table_south;
		case 3004: // west facing table
			Table table_west = new Table();
			table_west.setDirection(Direction.WEST);
			return table_west;

		case 4:
			return new Tree();

		// case 5:
		// return new Fountain();

		case 6:
			return new Cactus();

		case 7001: // north facing bookshelf
			return new Bookshelf();
		case 7002: // east facing bookshelf
			Bookshelf bookshelf_east = new Bookshelf();
			bookshelf_east.setDirection(Direction.EAST);
			return bookshelf_east;
		case 7003: // south facing bookshelf
			Bookshelf bookshelf_south = new Bookshelf();
			bookshelf_south.setDirection(Direction.SOUTH);
			return bookshelf_south;
		case 7004: // west facing bookshelf
			Bookshelf bookshelf_west = new Bookshelf();
			bookshelf_west.setDirection(Direction.WEST);
			return bookshelf_west;

		case 8001: // north facing treasure chest
			return new TreasureChest();
		case 8002: // east facing treasure chest
			TreasureChest treasureChest_east = new TreasureChest();
			treasureChest_east.setDirection(Direction.EAST);
			return treasureChest_east;
		case 8003: // south facing treasure chest
			TreasureChest treasureChest_south = new TreasureChest();
			treasureChest_south.setDirection(Direction.SOUTH);
			return treasureChest_south;
		case 8004: // west facing treasure chest
			TreasureChest treasureChest_west = new TreasureChest();
			treasureChest_west.setDirection(Direction.WEST);
			return treasureChest_west;

		case 9:
			return new Wall();

		case 10:
			return new Note();

		case 1101: // north facing door
			return new Door();
		case 1102: // east facing door
			Door door_east = new Door();
			door_east.setDirection(Direction.EAST);
			return door_east;
		case 1103: // south facing door
			Door door_south = new Door();
			door_south.setDirection(Direction.SOUTH);
			return door_south;
		case 1104: // west facing door
			Door door_west = new Door();
			door_west.setDirection(Direction.WEST);
			return door_west;
		case 12:
			return new HeavyBook();
		case 13:
			return new Key();
		case 14:
			return new Potion();
		case 15:
			return new Stick();
		}
		return null;
	}

	public int getRotatedInt(int integer) {
		switch (integer) {
		case 1:
			return 1;

		case 2001: // north facing sofa
			return 2002;
		case 2002: // east facing sofa
			return 2003;
		case 2003: // south facing sofa
			return 2004;
		case 2004: // west facing sofa
			return 2001;

		case 3001: // north facing table
			return 3002;
		case 3002: // east facing table
			return 3003;
		case 3003: // south facing table
			return 3004;
		case 3004: // west facing table
			return 3001;

		case 4:
			return 4;

		// case 5:
		// return new Fountain();

		case 6:
			return 6;

		case 7001: // north facing bookshelf
			return 7002;
		case 7002: // east facing bookshelf
			return 7003;
		case 7003: // south facing bookshelf
			return 7004;
		case 7004: // west facing bookshelf
			return 7001;

		case 8001: // north facing treasure chest
			return 8002;
		case 8002: // east facing treasure chest
			return 8003;
		case 8003: // south facing treasure chest
			return 8004;
		case 8004: // west facing treasure chest
			return 8001;

		case 9:
			return 9;

		case 10:
			return 10;

		case 1101: // north facing door
			return 1102;
		case 1102: // east facing door
			return 1103;
		case 1103: // south facing door
			return 1104;
		case 1104: // west facing door
			return 1101;
		case 12:
			return 12;
		case 13:
			return 13;
		case 14:
			return 14;
		case 15:
			return 15;
		}
		return -1;
	}

	public BufferedImage getIntAsImage(int integer) {
		switch (integer) {
		case 1:
			return rockImage_top;

		case 2001: // north facing sofa
			return sofaImage_top_north;
		case 2002: // east facing sofa
			return sofaImage_top_east;
		case 2003: // south facing sofa
			return sofaImage_top_south;
		case 2004: // west facing sofa
			return sofaImage_top_west;

		case 3001: // north facing table
			return tableImage_top_north;
		case 3002: // east facing table
			return tableImage_top_east;
		case 3003: // south facing table
			return tableImage_top_south;
		case 3004: // west facing table
			return tableImage_top_west;

		case 4:
			return treeImage_top;

		// case 5:
		// return new Fountain();

		case 6:
			return cactusImage_top;

		case 7001: // north facing bookshelf
			return bookshelfImage_top_north;
		case 7002: // east facing bookshelf
			return bookshelfImage_top_east;
		case 7003: // south facing bookshelf
			return bookshelfImage_top_south;
		case 7004: // west facing bookshelf
			return bookshelfImage_top_west;

		case 8001: // north facing treasure chest
			return treasureChestImage_top_north;
		case 8002: // east facing treasure chest
			return treasureChestImage_top_east;
		case 8003: // south facing treasure chest
			return treasureChestImage_top_south;
		case 8004: // west facing treasure chest
			return treasureChestImage_top_west;

		case 9:
			return wallBlockImage_top;

		case 10:
			return noteImage_top;

		case 1101: // north facing door
			return doorImage_top_north;
		case 1102: // east facing door
			return doorImage_top_east;
		case 1103: // south facing door
			return doorImage_top_south;
		case 1104: // west facing door
			return doorImage_top_west;

		case 12:
			return heavyBookImage_top;
		case 13:
			return keyImage_top;
		case 14:
			return potionImage_top;
		case 15:
			return stickImage_top;
		}
		return null;
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

			for (BoardPanelComponent tile : boardTiles) {
				tile.paintComponent(g);
			}

		}

		class BoardPanelComponent extends JLayeredPane implements MouseListener {

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
					g.fillRect(getBounds().x, getBounds().y, getWidth(), getHeight());
				} else if (selectedItem.equals(" ") && roomMap[x][y] == 0) {
					g.setColor(Color.WHITE);
					g.fillRect(getBounds().x, getBounds().y, getWidth(), getHeight());
				} else {
					g.drawImage(getIntAsImage(roomMap[x][y]), getBounds().x, getBounds().y, 40, 40, this);
				}

				g.setColor(Color.BLACK);
				g.drawRect(getBounds().x, getBounds().y, getWidth(), getHeight());
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (!selectedItem.equals(" ")) {
					if (selectedItem.equals("door")) {

					} else {
						roomMap[x][y] = getItemAsInt(selectedItem);
						selectedItem = " ";
						System.out.println(Arrays.deepToString(roomMap).replace("], ", "]\n"));
						System.out.println("");
						boardPanel.repaint();
					}
				}

				if (deleteMode == true) {
					roomMap[x][y] = 0;
					System.out.println(Arrays.deepToString(roomMap).replace("], ", "]\n"));
					System.out.println("");
					boardPanel.repaint();
				}

				if (rotateMode == true && roomMap[x][y] != 0) {
					roomMap[x][y] = getRotatedInt(roomMap[x][y]);
					System.out.println(Arrays.deepToString(roomMap).replace("], ", "]\n"));
					System.out.println("");
					boardPanel.repaint();
				}

				if (selectedItem.equals(" ") && rotateMode == false && deleteMode == false && (roomMap[x][y] == 1101
						|| roomMap[x][y] == 1102 || roomMap[x][y] == 1103 || roomMap[x][y] == 1104)) {

					String[] doorToRoomStrings = new String[3];
					int index = 0;
					for (String roomName : roomStrings) {
						if (!roomName.equals(currentRoom)) {
							doorToRoomStrings[index++] = roomName;
						}
					}

					JOptionPane optionPane = new JOptionPane("This door leads to:", JOptionPane.QUESTION_MESSAGE,
							JOptionPane.DEFAULT_OPTION, null, doorToRoomStrings, null);

					JDialog dialog = optionPane.createDialog(null, "Door to Room Selection");
					dialog.setLocationRelativeTo(frame);
					dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
					dialog.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							JOptionPane.showMessageDialog(dialog, "Select a room!");
						}
					});
					dialog.setModal(true);
					dialog.setVisible(true);

					if (optionPane.getValue().equals("Library")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 110101;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 110101;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 110101;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 110101;
						}
					} else if (optionPane.getValue().equals("Foyer")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 110102;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 110102;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 110102;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 110102;
						}
					} else if (optionPane.getValue().equals("Courtyard")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 110103;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 110103;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 110103;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 110103;
						}
					} else if (optionPane.getValue().equals("Study")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 110104;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 110104;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 110104;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 110104;
						}
					}
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