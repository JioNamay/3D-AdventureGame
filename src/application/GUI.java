package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import renderer.Board;

/**
 * Class provides the graphical display of the GameWorld.
 *
 * @author yangcarr
 */
public abstract class GUI{
	/*
	 * changes
- got rid of controller, from within Adventure Game
- had AdventureGame extend GUI as the main 'controller' of the entire game world
- commented out the previus renderer to pass graphics object to renderer
- added methods to allow persistance to save/load games
- renamed 'midInfo' to 'rendererPanel -- this is the JPanel for renderer's use
- added constant field for screen size
	 */

	protected abstract void onStart(); // loads a GameWorld (new or saved)
	protected abstract void redraw(Graphics g); // T RECONSIDER
	protected abstract void loadGame(); // BENETTE RECONSIDER
	protected abstract void saveGame(); // BENETTE RECONSIDER


	public static final int FRAME_SIZE = 900;
	public static final int DRAWING_SIZE = 600;
	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	// board field, called within the redraw method(?)

	public GUI() {
		initialise();
	}

	protected JFrame frame;
	protected JPanel container; // global container to hold all the components in frame
	protected Board board;
	protected JPanel playerInfo;
	protected JComponent drawing; // the canvas to display the rendered world
	protected Graphics graphics;
	protected static JTextArea examinedItem, playerStats;
	protected JTextArea something;

	/**
	 * Sets up the GUI window: the menubars, the canvas for drawing the game, the
	 * items player is holding, and the various actions the player can perform.
	 */
	public void initialise() {
		frame = new JFrame("Adventure Game");
		frame.setPreferredSize(new Dimension(FRAME_SIZE, FRAME_SIZE));

		// container hold items that flow from top to bottom
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));

		setMenuBar();

		JPanel rendererPanel = new JPanel();
		rendererPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

		// RENDERER:
		/*JPanel boardPanel = new JPanel();
		boardPanel.setPreferredSize(new Dimension(DRAWING_SIZE, DRAWING_SIZE));
		boardPanel.setBounds(0, 0, DRAWING_SIZE, DRAWING_SIZE);
		boardPanel.setVisible(true);
		this.board = new Board(this, boardPanel);
		rendererPanel.add(boardPanel, BorderLayout.LINE_START);*/

		drawing = new JComponent() {
			protected void paintComponent(Graphics g) {
				graphics = g;
				redraw(graphics);		// render the board
			}
		};
		drawing.setPreferredSize(new Dimension(DRAWING_SIZE, DRAWING_SIZE));
		drawing.setVisible(true);
		drawing.repaint();
		rendererPanel.add(drawing);

		rendererPanel.add(Box.createRigidArea(new Dimension(10, 0))); // spacing between drawing and info
		JPanel descriptions = new JPanel(new GridLayout(3, 1, 0, 10)); // 3 rows, 1 column
		// descriptions.setBackground(Color.blue); // test

		// display description of examined item
		examinedItem = new JTextArea("display examined item's info here", 10, 20);
		examinedItem.setEditable(false);
		examinedItem.setLineWrap(true);
		descriptions.add(examinedItem);

		// display player stats
		playerStats = new JTextArea("display player stats here", 10, 20);
		playerStats.setEditable(false);
		playerStats.setLineWrap(true);
		descriptions.add(playerStats);

		// display something.....
		something = new JTextArea("???", 10, 20);
		something.setEditable(false);
		something.setLineWrap(true);
		descriptions.add(something);

		rendererPanel.add(descriptions);
		container.add(rendererPanel);

		// area at the bottom to display items and action buttons
		playerInfo = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
		playerInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		playerInfo.setPreferredSize(new Dimension(FRAME_SIZE - 10, 155));
		// playerInfo.setBackground(Color.GREEN); // test

		// JPanel inventory = new JPanel(new GridLayout(2, 5)); // player can hold max
		// 10 items(?)
		// inventory.setPreferredSize(new Dimension(FRAME_SIZE+50/2, 110)));
		JComponent inventoryDrawing = new JComponent() {
			protected void paintComponent(Graphics g) {
				// draw every item of player's inventory here?

				// test:
				int width = 80;
				int height = 50;
				g.setColor(Color.RED);
				g.fillRect(0, 0, 200, 50);
				g.drawRect(0, 0, 400, 100);
				// g.setColor(Color.WHITE); // test
				// g.fillRect(0, 0, 400, 110); // test
				// draw outlines of grid for testing purposes

				// in actuality, draw the items that player is carrying
				// each image would be 50x160(?) -- to fit within the panel
			}
		};
		inventoryDrawing.setPreferredSize(new Dimension(410, 110));
		inventoryDrawing.setVisible(true);
		// playerInfo.add(inventory);
		playerInfo.add(inventoryDrawing);
		container.add(playerInfo);

		// buttons for navigation
		setNavigationButtons();
		// buttons for actions
		setActionButtons();

		// add everything to the frame
		frame.add(container);
		frame.pack();
		// TODO: resize frame here!!!
		frame.setVisible(true);
	}

	/**
	 * Creates the menu bar for the application window. This has the options: HELP
	 * -> synopsis of game GAME -> load a saved game, save current game, load a new
	 * game EDIT -> change the layout of the current map (?) QUIT -> exit the
	 * game(?)
	 */
	private void setMenuBar() {
		JMenuBar mb = new JMenuBar();

		// HELP: pop-up dialog with game information and instructions
		JMenu help = new JMenu("Help");
		help.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String str = "GAME INFO HERE"; // <-- CHANGE THIS LATER
				JOptionPane.showMessageDialog(frame, str, "Game info", JOptionPane.INFORMATION_MESSAGE);
				;
			}
		});

		// QUIT: exits the game -- gets confirmation from user to leave
		JMenu quit = new JMenu("Quit");
		quit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int ans = JOptionPane.showConfirmDialog(frame, "Are you sure you want to leave?");
				if (ans == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});

		// GAME: Options to save, load, generate new game
		JMenu game = new JMenu("Game");

		JMenuItem load = new JMenuItem("Load"); // load
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// loadGame(); // BENNETTE RECONSIDER
			}

		});

		JMenuItem save = new JMenuItem("Save"); // save
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// saveGame(); // BENNETTE RECONSIDER
			}

		});

		JMenuItem newGame = new JMenuItem("New"); // new game
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onStart();
			}

		});

		game.add(load);
		game.add(save);
		game.add(newGame);

		mb.add(help);
		mb.add(game);
		mb.add(quit);
		frame.setJMenuBar(mb);
	}

	/**
	 *
	 */
	private void setNavigationButtons() {
		// vertical spacing between components
		playerInfo.add(Box.createRigidArea(new Dimension(10, 0)));

		JButton west = new JButton("\u2190");
		west.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// move player west
				// redraw player position (redraw the world)
			}
		});

		JButton east = new JButton("\u2192");
		east.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// move player east
				// redraw player position
			}
		});

		JButton north = new JButton("\u2191");
		north.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// move player north
				// redraw player position
			}
		});

		JButton south = new JButton("\u2193");
		south.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// move player south
				// redraw player position
			}
		});

		JPanel navigation = new JPanel(new GridLayout(2, 3));
		navigation.setMaximumSize(new Dimension(150, 60));
		navigation.add(new JButton()); // blank area
		navigation.add(north);
		navigation.add(new JButton()); // blank area
		navigation.add(west);
		navigation.add(south);
		navigation.add(east);
		playerInfo.add(navigation);
	}

	private void setActionButtons() {
		JButton take = new JButton("Take Item");
		take.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// player collects item
				// redraw
			}
		});

		JButton examine = new JButton("Examine Item");
		examine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// short description of the item is shown
				// redraw(?)
			}
		});

		JButton drop = new JButton("Drop Item");
		take.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// player drops the item (item is placed at current player's position)
				// remove item from inventory when player drops it
				// redraw
			}
		});

		JButton look = new JButton("Look into room");
		look.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// short description of location (current location?)
				// redraw(?)
			}
		});

		JButton use = new JButton("Use Item");
		use.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// player uses item
				// redraw(?)
			}
		});

		JButton attack = new JButton("Attack");
		take.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// player attacks with current selected item
				// redraw
			}
		});

		playerInfo.add(Box.createRigidArea(new Dimension(10, 0)));
		JPanel actions = new JPanel(new GridLayout(3, 2));
		actions.setMaximumSize(new Dimension(240, 90)); // does anything?

		actions.add(take);
		actions.add(examine);
		actions.add(drop);
		actions.add(look);
		actions.add(use);
		actions.add(attack);
		playerInfo.add(actions);
	}

	/**
	 * Returns the JTextArea to display the description of examined
	 * items or rooms.
	 */
	public static JTextArea getExaminedItemDisplay() {
		return examinedItem;
	}

	/**
	 * Returns the display area that holds player's information,
	 * like health and money.
	 */
	public static JTextArea getPlayerStatDisplay() {
		return playerStats;
	}

	// method to return the last JTextArea
}
