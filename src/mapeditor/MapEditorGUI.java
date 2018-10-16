package mapeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * The Class MapEditorGUI.
 *
 * @author namayraph 300374356
 */
public class MapEditorGUI {

	/** The frame. */
	private JFrame frame;

	/** The board panel. */
	private JLayeredPane boardPanel;

	/** The button panel. */
	private JPanel buttonPanel;

	/** The rock button. */
	private JButton rockButton;

	/** The sofa button. */
	private JButton sofaButton;

	/** The table button. */
	private JButton tableButton;

	/** The tree button. */
	private JButton treeButton;

	/** The note button. */
	private JButton noteButton;

	/** The fountain button. */
	private JButton fountainButton;

	/** The cactus button. */
	private JButton cactusButton;

	/** The bookshelf button. */
	private JButton bookshelfButton;

	/** The treasure chest button. */
	private JButton treasureChestButton;

	/** The wall block button. */
	private JButton wallBlockButton;

	/** The door button. */
	private JButton doorButton;

	/** The heavy book button. */
	private JButton heavyBookButton;

	/** The key button. */
	private JButton keyButton;

	/** The potion button. */
	private JButton potionButton;

	/** The stick button. */
	private JButton stickButton;

	/** The delete button. */
	private JButton deleteButton;

	/** The rotate button. */
	private JButton rotateButton;

	/** The room select label. */
	private JLabel roomSelectLabel;

	/** The menu bar. */
	private JMenuBar menuBar;

	/** The file menu. */
	private JMenu fileMenu;

	/** The save menu item. */
	private JMenuItem saveMenuItem;

	/** The rock image top. */
	private BufferedImage rockImage_top;

	/** The sofa image top north. */
	private BufferedImage sofaImage_top_north;

	/** The sofa image top east. */
	private BufferedImage sofaImage_top_east;

	/** The sofa image top south. */
	private BufferedImage sofaImage_top_south;

	/** The sofa image top west. */
	private BufferedImage sofaImage_top_west;

	/** The table image top north. */
	private BufferedImage tableImage_top_north;

	/** The table image top east. */
	private BufferedImage tableImage_top_east;

	/** The table image top south. */
	private BufferedImage tableImage_top_south;

	/** The table image top west. */
	private BufferedImage tableImage_top_west;

	/** The tree image top. */
	private BufferedImage treeImage_top;

	/** The note image top. */
	private BufferedImage noteImage_top;

	/** The fountain image top. */
	private BufferedImage fountainImage_top;

	/** The cactus image top. */
	private BufferedImage cactusImage_top;

	/** The bookshelf image top north. */
	private BufferedImage bookshelfImage_top_north;

	/** The bookshelf image top east. */
	private BufferedImage bookshelfImage_top_east;

	/** The bookshelf image top south. */
	private BufferedImage bookshelfImage_top_south;

	/** The bookshelf image top west. */
	private BufferedImage bookshelfImage_top_west;

	/** The treasure chest image top north. */
	private BufferedImage treasureChestImage_top_north;

	/** The treasure chest image top east. */
	private BufferedImage treasureChestImage_top_east;

	/** The treasure chest image top south. */
	private BufferedImage treasureChestImage_top_south;

	/** The treasure chest image top west. */
	private BufferedImage treasureChestImage_top_west;

	/** The wall block image top. */
	private BufferedImage wallBlockImage_top;

	/** The door image top north. */
	private BufferedImage doorImage_top_north;

	/** The door image top east. */
	private BufferedImage doorImage_top_east;

	/** The door image top south. */
	private BufferedImage doorImage_top_south;

	/** The door image top west. */
	private BufferedImage doorImage_top_west;

	/** The heavy book image top. */
	private BufferedImage heavyBookImage_top;

	/** The key image top. */
	private BufferedImage keyImage_top;

	/** The potion image top. */
	private BufferedImage potionImage_top;

	/** The stick image top. */
	private BufferedImage stickImage_top;

	/** The current room. */
	protected String currentRoom = "Library";

	/** The selected item. */
	protected String selectedItem = " ";

	/** The delete mode. */
	protected boolean deleteMode = false;

	/** The rotate mode. */
	protected boolean rotateMode = false;

	/** The library map. */
	private int[][] libraryMap;

	/** The foyer map. */
	private int[][] foyerMap;

	/** The courtyard map. */
	private int[][] courtyardMap;

	/** The study map. */
	private int[][] studyMap;

	/** The room map. */
	private int[][] roomMap;

	/** The room strings. */
	private String[] roomStrings = { "Library", "Foyer", "Courtyard", "Study" };

	/** The board tiles. */
	private ArrayList<mapeditor.MapEditorGUI.BoardPanel.BoardPanelTile> boardTiles;

	/**
	 * Instantiates a new map editor GUI.
	 */
	public MapEditorGUI() {

		boardTiles = new ArrayList<mapeditor.MapEditorGUI.BoardPanel.BoardPanelTile>();

		initializeImages();

		initializeMaps();

		GUI();

	}

	/**
	 * Gui.
	 */
	public void GUI() {

		setupFrame();

		setupMenuBar();

		setupBoardPanel();

		/* BUTTON PANEL */
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		/* BUTTON PANEL */

		setupRoomSelection();

		addRockButton();

		addSofaButton();

		addTableButton();

		addTreeButton();

		addFountainButton();

		addCactusButton();

		addBookshelfButton();

		addTreasureChestButton();

		addWallBlockButton();

		addNoteButton();

		addDoorButton();

		addHeavyBookButton();

		addKeyButton();

		addPotionButton();

		addStickButton();

		addDeleteButton();

		addRotateButton();

		frame.add(buttonPanel);
		frame.setVisible(true);

	}

	/**
	 * Setup frame.
	 */
	public void setupFrame() {

		frame = new JFrame("Map Editor");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent we) {
				int dialogResult = JOptionPane.showConfirmDialog(frame, "Save map as XML file before exiting?");
				if (dialogResult == JOptionPane.YES_OPTION) {
					exportToXML();
					frame.setVisible(false);
					frame.dispose();
				} else if (dialogResult == JOptionPane.NO_OPTION) {
					frame.setVisible(false);
					frame.dispose();
				}
			}

		});

		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setSize(670, 600);

	}

	/**
	 * Setup menu bar.
	 */
	public void setupMenuBar() {

		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exportToXML();
			}

		});

		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);

	}

	/**
	 * Setup board panel.
	 */
	public void setupBoardPanel() {

		boardPanel = new BoardPanel();
		boardPanel.setBounds(0, 0, 280, 280);
		((BoardPanel) boardPanel).addTiles();
		frame.add(boardPanel);

	}

	/**
	 * Setup room selection. If the user decides to edit another room, the current
	 * room layout will be stored and the GUI map will reflect the selected room.
	 */
	public void setupRoomSelection() {

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
						// not changing rooms
					} else if (currentRoom.equals("Foyer")) {
						libraryMap = roomMap; // store previous room layout
						roomMap = foyerMap; // now use the layout of the selected room
					} else if (currentRoom.equals("Courtyard")) {
						libraryMap = roomMap; // store previous room layout
						roomMap = courtyardMap; // now use the layout of the selected room
					} else if (currentRoom.equals("Study")) {
						libraryMap = roomMap; // store previous room layout
						roomMap = studyMap; // now use the layout of the selected room
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

			}

		});

		roomSelectLabel.setBounds(320, 0, 500, 60);
		buttonPanel.add(roomSelectLabel);
		roomList.setBounds(447, 0, 170, 60);
		buttonPanel.add(roomList);

	}

	/**
	 * Adds the rock button.
	 */
	public void addRockButton() {

		rockButton = new JButton();
		rockButton.setBounds(300, 60, 170, 60);
		rockButton.setToolTipText("Rock (contains key)");
		rockButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "rock";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		rockButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(new ImageIcon(rockImage_top.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Rock", SwingConstants.CENTER);
		rockButton.add(imageLabel, BorderLayout.WEST);
		rockButton.add(textLabel);
		buttonPanel.add(rockButton);

	}

	/**
	 * Adds the sofa button.
	 */
	public void addSofaButton() {

		sofaButton = new JButton();
		sofaButton.setBounds(470, 60, 170, 60);
		sofaButton.setToolTipText("Sofa");
		sofaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "sofa";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		sofaButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(
				new ImageIcon(sofaImage_top_north.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Sofa", SwingConstants.CENTER);
		sofaButton.add(imageLabel, BorderLayout.WEST);
		sofaButton.add(textLabel);
		buttonPanel.add(sofaButton);

	}

	/**
	 * Adds the table button.
	 */
	public void addTableButton() {

		tableButton = new JButton();
		tableButton.setBounds(300, 120, 170, 60);
		tableButton.setToolTipText("Table");
		tableButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "table";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		tableButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(
				new ImageIcon(tableImage_top_north.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Table", SwingConstants.CENTER);
		tableButton.add(imageLabel, BorderLayout.WEST);
		tableButton.add(textLabel);
		buttonPanel.add(tableButton);

	}

	/**
	 * Adds the tree button.
	 */
	public void addTreeButton() {

		treeButton = new JButton();
		treeButton.setBounds(470, 120, 170, 60);
		treeButton.setToolTipText("Tree");
		treeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "tree";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		treeButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(new ImageIcon(treeImage_top.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Tree", SwingConstants.CENTER);
		treeButton.add(imageLabel, BorderLayout.WEST);
		treeButton.add(textLabel);
		buttonPanel.add(treeButton);

	}

	/**
	 * Adds the fountain button.
	 */
	public void addFountainButton() {

		fountainButton = new JButton();
		fountainButton.setBounds(300, 180, 170, 60);
		fountainButton.setToolTipText("Fountain");
		fountainButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "fountain";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		fountainButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(new ImageIcon(fountainImage_top.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Fountain", SwingConstants.CENTER);
		fountainButton.add(imageLabel, BorderLayout.WEST);
		fountainButton.add(textLabel);
		buttonPanel.add(fountainButton);

	}

	/**
	 * Adds the cactus button.
	 */
	public void addCactusButton() {

		cactusButton = new JButton();
		cactusButton.setBounds(470, 180, 170, 60);
		cactusButton.setToolTipText("Cactus");
		cactusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "cactus";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		cactusButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(new ImageIcon(cactusImage_top.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Cactus", SwingConstants.CENTER);
		cactusButton.add(imageLabel, BorderLayout.WEST);
		cactusButton.add(textLabel);
		buttonPanel.add(cactusButton);

	}

	/**
	 * Adds the bookshelf button.
	 */
	public void addBookshelfButton() {

		bookshelfButton = new JButton();
		bookshelfButton.setBounds(300, 240, 170, 60);
		bookshelfButton.setToolTipText("Bookshelf");
		bookshelfButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "bookshelf";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		bookshelfButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(
				new ImageIcon(bookshelfImage_top_north.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Bookshelf", SwingConstants.CENTER);
		bookshelfButton.add(imageLabel, BorderLayout.WEST);
		bookshelfButton.add(textLabel);
		buttonPanel.add(bookshelfButton);

	}

	/**
	 * Adds the treasure chest button.
	 */
	public void addTreasureChestButton() {

		treasureChestButton = new JButton();
		treasureChestButton.setBounds(470, 240, 170, 60);
		treasureChestButton.setToolTipText("Treasure Chest");
		treasureChestButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "treasure chest";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		treasureChestButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(
				new ImageIcon(treasureChestImage_top_north.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Treasure Chest", SwingConstants.CENTER);
		treasureChestButton.add(imageLabel, BorderLayout.WEST);
		treasureChestButton.add(textLabel);
		buttonPanel.add(treasureChestButton);

	}

	/**
	 * Adds the wall block button.
	 */
	public void addWallBlockButton() {

		wallBlockButton = new JButton();
		wallBlockButton.setBounds(300, 300, 170, 60);
		wallBlockButton.setToolTipText("Wall Block");
		wallBlockButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "wall block";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		wallBlockButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(new ImageIcon(wallBlockImage_top.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Wall Block", SwingConstants.CENTER);
		wallBlockButton.add(imageLabel, BorderLayout.WEST);
		wallBlockButton.add(textLabel);
		buttonPanel.add(wallBlockButton);

	}

	/**
	 * Adds the note button.
	 */
	public void addNoteButton() {

		noteButton = new JButton();
		noteButton.setBounds(470, 300, 170, 60);
		noteButton.setToolTipText("Note");
		noteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "note";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		noteButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(new ImageIcon(noteImage_top.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Note", SwingConstants.CENTER);
		noteButton.add(imageLabel, BorderLayout.WEST);
		noteButton.add(textLabel);
		buttonPanel.add(noteButton);

	}

	/**
	 * Adds the door button.
	 */
	public void addDoorButton() {

		doorButton = new JButton();
		doorButton.setBounds(300, 360, 170, 60);
		doorButton.setToolTipText("Door");
		doorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "door";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		doorButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(
				new ImageIcon(doorImage_top_north.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Door", SwingConstants.CENTER);
		doorButton.add(imageLabel, BorderLayout.WEST);
		doorButton.add(textLabel);
		buttonPanel.add(doorButton);

	}

	/**
	 * Adds the heavy book button.
	 */
	public void addHeavyBookButton() {

		heavyBookButton = new JButton();
		heavyBookButton.setBounds(470, 360, 170, 60);
		heavyBookButton.setToolTipText("Heavy Book");
		heavyBookButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "heavy book";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		heavyBookButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(new ImageIcon(heavyBookImage_top.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Heavy Book", SwingConstants.CENTER);
		heavyBookButton.add(imageLabel, BorderLayout.WEST);
		heavyBookButton.add(textLabel);
		buttonPanel.add(heavyBookButton);

	}

	/**
	 * Adds the key button.
	 */
	public void addKeyButton() {

		keyButton = new JButton();
		keyButton.setBounds(300, 420, 170, 60);
		keyButton.setToolTipText("Key");
		keyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "key";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		keyButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(new ImageIcon(keyImage_top.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Key", SwingConstants.CENTER);
		keyButton.add(imageLabel, BorderLayout.WEST);
		keyButton.add(textLabel);
		buttonPanel.add(keyButton);

	}

	/**
	 * Adds the potion button.
	 */
	public void addPotionButton() {

		potionButton = new JButton();
		potionButton.setBounds(470, 420, 170, 60);
		potionButton.setToolTipText("Potion");
		potionButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "potion";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		potionButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(new ImageIcon(potionImage_top.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Potion", SwingConstants.CENTER);
		potionButton.add(imageLabel, BorderLayout.WEST);
		potionButton.add(textLabel);
		buttonPanel.add(potionButton);

	}

	/**
	 * Adds the stick button.
	 */
	public void addStickButton() {

		stickButton = new JButton();
		stickButton.setBounds(keyButton.getX() + keyButton.getWidth() / 2, 480, 170, 60);
		stickButton.setToolTipText("Stick");
		stickButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItem = "stick";
				turnDeleteModeOff();
				turnRotateModeOff();
				boardPanel.repaint();
			}

		});
		stickButton.setLayout(new BorderLayout());
		JLabel imageLabel = new JLabel(new ImageIcon(stickImage_top.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		JLabel textLabel = new JLabel("Stick", SwingConstants.CENTER);
		stickButton.add(imageLabel, BorderLayout.WEST);
		stickButton.add(textLabel);
		buttonPanel.add(stickButton);

	}

	/**
	 * Adds the delete button.
	 */
	public void addDeleteButton() {

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

	}

	/**
	 * Adds the rotate button.
	 */
	public void addRotateButton() {

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

	}

	/**
	 * Export to XML.
	 */
	public void exportToXML() {

		if (currentRoom.equals("Library")) {
			libraryMap = roomMap;
		} else if (currentRoom.equals("Foyer")) {
			foyerMap = roomMap;
		} else if (currentRoom.equals("Courtyard")) {
			courtyardMap = roomMap;
		} else if (currentRoom.equals("Study")) {
			studyMap = roomMap;
		}

		XMLOutputFactory factory = XMLOutputFactory.newInstance();

		try {

			XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter("gamemap.xml"));

			writer.writeStartDocument();

			writer.writeStartElement("gamemap");

			writer.writeStartElement("library");
			for (int row = 0; row < 7; row++) {
				for (int col = 0; col < 7; col++) {
					writer.writeStartElement("item");
					writer.writeAttribute("itemID", "" + libraryMap[row][col]);
					writer.writeAttribute("row", "" + row);
					writer.writeAttribute("col", "" + col);
					writer.writeEndElement();
				}
			}
			writer.writeEndElement();

			writer.writeStartElement("foyer");
			for (int row = 0; row < 7; row++) {
				for (int col = 0; col < 7; col++) {
					writer.writeStartElement("item");
					writer.writeAttribute("itemID", "" + foyerMap[row][col]);
					writer.writeAttribute("row", "" + row);
					writer.writeAttribute("col", "" + col);
					writer.writeEndElement();
				}
			}
			writer.writeEndElement();

			writer.writeStartElement("courtyard");
			for (int row = 0; row < 7; row++) {
				for (int col = 0; col < 7; col++) {
					writer.writeStartElement("item");
					writer.writeAttribute("itemID", "" + courtyardMap[row][col]);
					writer.writeAttribute("row", "" + row);
					writer.writeAttribute("col", "" + col);
					writer.writeEndElement();
				}
			}
			writer.writeEndElement();

			writer.writeStartElement("study");
			for (int row = 0; row < 7; row++) {
				for (int col = 0; col < 7; col++) {
					writer.writeStartElement("item");
					writer.writeAttribute("itemID", "" + studyMap[row][col]);
					writer.writeAttribute("row", "" + row);
					writer.writeAttribute("col", "" + col);
					writer.writeEndElement();
				}
			}
			writer.writeEndElement();

			writer.writeEndDocument();

			writer.flush();
			writer.close();

			JOptionPane.showMessageDialog(frame, "Map successfully exported as 'gamemap.xml'");

		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Initialize images.
	 */
	public void initializeImages() {
		try {

			rockImage_top = ImageIO.read(new File("images/rock.png"));

			sofaImage_top_north = ImageIO.read(new File("images/sofa.png"));
			sofaImage_top_east = ImageIO.read(new File("images/sofa_east.png"));
			sofaImage_top_south = ImageIO.read(new File("images/sofa_south.png"));
			sofaImage_top_west = ImageIO.read(new File("images/sofa_west.png"));

			tableImage_top_north = ImageIO.read(new File("images/table.png"));
			tableImage_top_east = ImageIO.read(new File("images/table_east.png"));
			tableImage_top_south = ImageIO.read(new File("images/table_south.png"));
			tableImage_top_west = ImageIO.read(new File("images/table_west.png"));

			treeImage_top = ImageIO.read(new File("images/tree.png"));

			noteImage_top = ImageIO.read(new File("images/note.png"));

			fountainImage_top = ImageIO.read(new File("images/fountain.png"));

			cactusImage_top = ImageIO.read(new File("images/cactus.png"));

			bookshelfImage_top_north = ImageIO.read(new File("images/bookshelf.png"));
			bookshelfImage_top_east = ImageIO.read(new File("images/bookshelf_east.png"));
			bookshelfImage_top_south = ImageIO.read(new File("images/bookshelf_south.png"));
			bookshelfImage_top_west = ImageIO.read(new File("images/bookshelf_west.png"));

			treasureChestImage_top_north = ImageIO.read(new File("images/chest_closed.png"));
			treasureChestImage_top_east = ImageIO.read(new File("images/chest_east.png"));
			treasureChestImage_top_south = ImageIO.read(new File("images/chest_south.png"));
			treasureChestImage_top_west = ImageIO.read(new File("images/chest_west.png"));

			wallBlockImage_top = ImageIO.read(new File("images/wall.png"));

			doorImage_top_north = ImageIO.read(new File("images/door.png"));
			doorImage_top_east = ImageIO.read(new File("images/door_east.png"));
			doorImage_top_south = ImageIO.read(new File("images/door_south.png"));
			doorImage_top_west = ImageIO.read(new File("images/door_west.png"));

			heavyBookImage_top = ImageIO.read(new File("images/book.png"));
			keyImage_top = ImageIO.read(new File("images/key.png"));
			potionImage_top = ImageIO.read(new File("images/potion.png"));
			stickImage_top = ImageIO.read(new File("images/stick.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize maps and sets all cells to 0.
	 */
	public void initializeMaps() {

		roomMap = new int[7][7];
		libraryMap = new int[7][7];
		foyerMap = new int[7][7];
		courtyardMap = new int[7][7];
		studyMap = new int[7][7];
		for (int row = 0; row < 7; row++) {
			for (int col = 0; col < 7; col++) {
				roomMap[row][col] = 0;
				libraryMap[row][col] = 0;
				foyerMap[row][col] = 0;
				courtyardMap[row][col] = 0;
				studyMap[row][col] = 0;
			}
		}

	}

	/**
	 * Turn delete mode off.
	 */
	public void turnDeleteModeOff() {
		deleteMode = false;
		deleteButton.setText("Delete Mode: Off");
	}

	/**
	 * Turn rotate mode off.
	 */
	public void turnRotateModeOff() {
		rotateMode = false;
		rotateButton.setText("Rotate Mode: Off");
	}

	/**
	 * Gets the item ID.
	 *
	 * @param item the item
	 * @param x    the x
	 * @param y    the y
	 * @return the item as int
	 */
	public int getItemAsInt(String item, int x, int y) {

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
			if (x == 0 && y == 3) { // north
				return 1101;
			} else if (x == 3 && y == 6) { // east
				return 1102;
			} else if (x == 6 && y == 3) { // south
				return 1103;
			} else if (x == 3 && y == 0) { // west
				return 1104;
			}
		case "heavy book":
			return 12;
		case "key":
			return 13;
		case "potion":
			return 14;
		case "stick":
			return 15;
		}
		return -1; // item does not exist

	}

	/**
	 * Gets the item ID if the item were to be rotated 90 degrees clockwise.
	 *
	 * @param integer the integer
	 * @return the rotated int
	 */
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

		case 5:
			return 5;

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
		case 12:
			return 12;
		case 13:
			return 13;
		case 14:
			return 14;
		case 15:
			return 15;
		}

		return integer; // item cannot be rotated
	}

	/**
	 * Gets the item ID as an image.
	 *
	 * @param integer the integer
	 * @return the int as image
	 */
	public BufferedImage getIntAsImage(int integer) {

		if (integer == 1) {
			return rockImage_top;

		} else if (integer == 2001) { // north facing sofa
			return sofaImage_top_north;
		} else if (integer == 2002) { // east facing sofa
			return sofaImage_top_east;
		} else if (integer == 2003) { // south facing sofa
			return sofaImage_top_south;
		} else if (integer == 2004) { // west facing sofa
			return sofaImage_top_west;

		} else if (integer == 3001) { // north facing table
			return tableImage_top_north;
		} else if (integer == 3002) { // east facing table
			return tableImage_top_east;
		} else if (integer == 3003) { // south facing table
			return tableImage_top_south;
		} else if (integer == 3004) { // west facing table
			return tableImage_top_west;

		} else if (integer == 4) {
			return treeImage_top;

		} else if (integer == 5) {
			return fountainImage_top;

		} else if (integer == 6) {
			return cactusImage_top;

		} else if (integer == 7001) { // north facing bookshelf
			return bookshelfImage_top_north;
		} else if (integer == 7002) { // east facing bookshelf
			return bookshelfImage_top_east;
		} else if (integer == 7003) { // south facing bookshelf
			return bookshelfImage_top_south;
		} else if (integer == 7004) { // west facing bookshelf
			return bookshelfImage_top_west;

		} else if (integer == 8001) { // north facing treasure chest
			return treasureChestImage_top_north;
		} else if (integer == 8002) { // east facing treasure chest
			return treasureChestImage_top_east;
		} else if (integer == 8003) { // south facing treasure chest
			return treasureChestImage_top_south;
		} else if (integer == 8004) { // west facing treasure chest
			return treasureChestImage_top_west;

		} else if (integer == 9) {
			return wallBlockImage_top;

		} else if (integer == 10) {
			return noteImage_top;

			// all possible north door IDs
		} else if (integer == 1101 || integer == 11010101 || integer == 11010102 || integer == 11010103
				|| integer == 11010104 || integer == 11010105 || integer == 11010201 || integer == 11010202
				|| integer == 11010203 || integer == 11010204 || integer == 11010205 || integer == 11010301
				|| integer == 11010302 || integer == 11010303 || integer == 11010304 || integer == 11010305
				|| integer == 11010401 || integer == 11010402 || integer == 11010403 || integer == 11010404
				|| integer == 11010405) {
			return doorImage_top_north;

			// all possible east door IDs
		} else if (integer == 1102 || integer == 11020101 || integer == 11020102 || integer == 11020103
				|| integer == 11020104 || integer == 11020105 || integer == 11020201 || integer == 11020202
				|| integer == 11020203 || integer == 11020204 || integer == 11020205 || integer == 11020301
				|| integer == 11020302 || integer == 11020303 || integer == 11020304 || integer == 11020305
				|| integer == 11020401 || integer == 11020402 || integer == 11020403 || integer == 11020404
				|| integer == 11020405) {
			return doorImage_top_east;

			// all possible south door IDs
		} else if (integer == 1103 || integer == 11030101 || integer == 11030102 || integer == 11030103
				|| integer == 11030104 || integer == 11030105 || integer == 11030201 || integer == 11030202
				|| integer == 11030203 || integer == 11030204 || integer == 11030205 || integer == 11030301
				|| integer == 11030302 || integer == 11030303 || integer == 11030304 || integer == 11030305
				|| integer == 11030401 || integer == 11030402 || integer == 11030403 || integer == 11030404
				|| integer == 11030405) {
			return doorImage_top_south;

			// all possible west door IDs
		} else if (integer == 1104 || integer == 11040101 || integer == 11040102 || integer == 11040103
				|| integer == 11040104 || integer == 11040105 || integer == 11040201 || integer == 11040202
				|| integer == 11040203 || integer == 11040204 || integer == 11040205 || integer == 11040301
				|| integer == 11040302 || integer == 11040303 || integer == 11040304 || integer == 11040305
				|| integer == 11040401 || integer == 11040402 || integer == 11040403 || integer == 11040404
				|| integer == 11040405) {
			return doorImage_top_west;

		} else if (integer == 12) {
			return heavyBookImage_top;
		} else if (integer == 13) {
			return keyImage_top;
		} else if (integer == 14) {
			return potionImage_top;
		} else if (integer == 15) {
			return stickImage_top;
		}

		return null;
	}

	/**
	 * The BoardPanel class, which represents the main board.
	 */
	private class BoardPanel extends JLayeredPane {

		/**
		 * Adds the tiles.
		 */
		public void addTiles() {
			for (int row = 0; row < 7; row++) {
				for (int col = 0; col < 7; col++) {
					BoardPanelTile bpt = new BoardPanelTile(new Rectangle(col * 40, row * 40, 40, 40), row, col);
					boardPanel.add(bpt);
					boardTiles.add(bpt);
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		public void paintComponent(Graphics g) {

			for (BoardPanelTile tile : boardTiles) { // paints all tiles on the board
				tile.paintComponent(g);
			}

		}

		/**
		 * The BoardPanelTile class, which represents a tile on the BoardPanel.
		 */
		class BoardPanelTile extends JLayeredPane implements MouseListener {

			/** The x. */
			public int x;

			/** The y. */
			public int y;

			/**
			 * Instantiates a new board panel tile.
			 *
			 * @param bounds the bounds
			 * @param x      the x
			 * @param y      the y
			 */
			public BoardPanelTile(Rectangle bounds, int x, int y) {
				this.x = x;
				this.y = y;
				setBounds(bounds);
				addMouseListener(this);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
			 */
			public void paintComponent(Graphics g) {

				// a door item is selected
				if (!selectedItem.equals(" ") && selectedItem.equals("door")) {

					if (roomMap[x][y] == 0) { // highlight only the places where the door can be placed
						if (((x == 3 && (y == 0 || y == 6)) || y == 3 && (x == 0 || x == 6))) {
							g.setColor(Color.CYAN);
						} else {
							g.setColor(Color.WHITE);
						}
						g.fillRect(getBounds().x, getBounds().y, getWidth(), getHeight());
					} else {
						// tile is occupied
						g.drawImage(getIntAsImage(roomMap[x][y]), getBounds().x, getBounds().y, 40, 40, this);
					}

					// a non-door item is selected and the tile is empty
				} else if (!selectedItem.equals(" ") && roomMap[x][y] == 0) {
					g.setColor(Color.CYAN);
					g.fillRect(getBounds().x, getBounds().y, getWidth(), getHeight());

					// no item is selected and the tile is empty
				} else if (selectedItem.equals(" ") && roomMap[x][y] == 0) {
					g.setColor(Color.WHITE);
					g.fillRect(getBounds().x, getBounds().y, getWidth(), getHeight());

				} else { // tile is occupied
					g.drawImage(getIntAsImage(roomMap[x][y]), getBounds().x, getBounds().y, 40, 40, this);
				}

				g.setColor(Color.BLACK);
				// draws the tile border to form a "grid"
				g.drawRect(getBounds().x, getBounds().y, getWidth(), getHeight());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseClicked(MouseEvent e) {

				if (!selectedItem.equals(" ")) {

					if (selectedItem.equals("door")) {

						// the only tiles where the door can be placed
						if (((x == 3 && (y == 0 || y == 6)) || y == 3 && (x == 0 || x == 6))) {

							roomMap[x][y] = getItemAsInt(selectedItem, x, y);
							selectedItem = " ";
							boardPanel.repaint();

						} else { // user clicked where a door cannot be placed

							JOptionPane.showMessageDialog(frame, "The door must be placed in a highlighted area!");

						}

					} else {

						roomMap[x][y] = getItemAsInt(selectedItem, x, y);
						selectedItem = " ";
						boardPanel.repaint();

					}

				}

				if (deleteMode == true) {
					// delete the tile
					roomMap[x][y] = 0;
					boardPanel.repaint();
				}

				if (rotateMode == true && roomMap[x][y] != 0) {
					// rotate the tile
					roomMap[x][y] = getRotatedInt(roomMap[x][y]);
					boardPanel.repaint();
				}

				// is a door tile
				if (selectedItem.equals(" ") && rotateMode == false && deleteMode == false && (roomMap[x][y] == 1101
						|| roomMap[x][y] == 1102 || roomMap[x][y] == 1103 || roomMap[x][y] == 1104)) {

					// user clicks where they can place a door
					if (((x == 3 && (y == 0 || y == 6)) || y == 3 && (x == 0 || x == 6))) {

						// prompt and force user to select where the door leads
						forceDoorRoomSelection();

					}

				}

			}

			/**
			 * Forces the user to select where the door leads.
			 */
			public void forceDoorRoomSelection() {

				String[] doorToRoomStrings = new String[4];
				int index = 0;
				for (String roomName : roomStrings) {
					if (!roomName.equals(currentRoom)) {
						doorToRoomStrings[index++] = roomName;
					}
				}
				doorToRoomStrings[index] = "Win Room";

				JOptionPane optionPane = new JOptionPane("This door leads to:", JOptionPane.QUESTION_MESSAGE,
						JOptionPane.DEFAULT_OPTION, null, doorToRoomStrings, null);

				JDialog dialog = optionPane.createDialog(frame, "Door to Room Selection");
				dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				dialog.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) {
						JOptionPane.showMessageDialog(dialog, "Select a room!");
					}
				});
				dialog.setModal(true);
				dialog.setVisible(true);

				if (optionPane.getValue().equals("Library")) {
					if (currentRoom.equals("Library")) {
						// can't set door from library to library
					} else if (currentRoom.equals("Foyer")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010201;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020201;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030201;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040201;
						}
					} else if (currentRoom.equals("Courtyard")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010301;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020301;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030301;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040301;
						}
					} else if (currentRoom.equals("Study")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010401;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020401;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030401;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040401;
						}
					}
				} else if (optionPane.getValue().equals("Foyer")) {
					if (currentRoom.equals("Library")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010102;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020102;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030102;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040102;
						}
					} else if (currentRoom.equals("Foyer")) {
						// can't set door from foyer to foyer
					} else if (currentRoom.equals("Courtyard")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010302;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020302;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030302;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040302;
						}
					} else if (currentRoom.equals("Study")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010402;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020402;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030402;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040402;
						}
					}
				} else if (optionPane.getValue().equals("Courtyard")) {
					if (currentRoom.equals("Library")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010103;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020103;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030103;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040103;
						}
					} else if (currentRoom.equals("Foyer")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010203;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020203;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030203;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040203;
						}
					} else if (currentRoom.equals("Courtyard")) {
						// can't set room from courtyard to courtyard
					} else if (currentRoom.equals("Study")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010403;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020403;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030403;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040403;
						}
					}
				} else if (optionPane.getValue().equals("Study")) {
					if (currentRoom.equals("Library")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010104;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020104;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030104;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040104;
						}
					} else if (currentRoom.equals("Foyer")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010204;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020204;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030204;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040204;
						}
					} else if (currentRoom.equals("Courtyard")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010304;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020304;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030304;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040304;
						}
					} else if (currentRoom.equals("Study")) {
						// can't set room from study to study
					}
				} else if (optionPane.getValue().equals("Win Room")) {
					if (currentRoom.equals("Library")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010105;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020105;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030105;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040105;
						}
					} else if (currentRoom.equals("Foyer")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010205;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020205;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030205;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040205;
						}
					} else if (currentRoom.equals("Courtyard")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010305;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020305;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030305;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040305;
						}
					} else if (currentRoom.equals("Study")) {
						if (roomMap[x][y] == 1101) { // north
							roomMap[x][y] = 11010405;
						} else if (roomMap[x][y] == 1102) { // east
							roomMap[x][y] = 11020405;
						} else if (roomMap[x][y] == 1103) { // south
							roomMap[x][y] = 11030405;
						} else if (roomMap[x][y] == 1104) { // west
							roomMap[x][y] = 11040405;
						}
					}
				}
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
			 */
			@Override
			public void mousePressed(MouseEvent e) {

			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseReleased(MouseEvent e) {

			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseEntered(MouseEvent e) {

			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseExited(MouseEvent e) {

			}

		}

	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new MapEditorGUI();
	}

}