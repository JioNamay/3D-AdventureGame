package Application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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



/**
 * Class provides the graphical display of the GameWorld.
 * @author yangcarr
 */
public abstract class GUI {

	// protected abstract void redraw(); 	// T RECONSIDER
	// protected abstract void editMap();		// JIO RECONSIDER
	// protected abstract void loadGame();		// BENETTE RECONSIDER
	// protected abstract void saveGame();		// BENETTE RECONSIDER
	protected abstract void onStart();		// loads a GameWorld (new or saved)

	public static final int FRAME_SIZE = 800;
	public static final int DRAWING_SIZE = 600;

	public GUI() {
		initialise();
	}

	protected JFrame frame;
	protected JPanel container;		// global container to hold all the components in frame
	protected JComponent drawing;
	protected JPanel playerInfo;

	/**
	 * Sets up the GUI window: the menubars, the canvas for drawing the game,
	 * the items player is holding, and the various actions the player can
	 * perform.
	 */
	public void initialise() {
		frame = new JFrame("Adventure Game");
		frame.setPreferredSize(new Dimension(FRAME_SIZE, FRAME_SIZE));

		// container hold items that flow from top to bottom
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
		container.add(Box.createVerticalGlue());

		setMenuBar();

		// canvas to render the game world on
		drawing = new JComponent() {
			protected void paintComponent(Graphics g) {
				g.setColor(Color.GRAY);
				g.fillRect(0, 0, DRAWING_SIZE, DRAWING_SIZE);
				// redraw();
			}
		};
		drawing.setPreferredSize(new Dimension(DRAWING_SIZE, DRAWING_SIZE));
		drawing.setVisible(true);
		container.add(drawing);

		// area at the bottom to display items and action buttons
		playerInfo = new JPanel();
		playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.LINE_AXIS));
		playerInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		int spacing = 10;		// spacing between components

		JPanel inventory = new JPanel(new GridLayout(2, 5));		// player can hold max 10 items(?)
		JComponent inventoryDrawing = new JComponent() {
			protected void paintComponent(Graphics g) {
				int width = (FRAME_SIZE/2) / 5;
				int height = 100/2;
				// draw outlines of grid for testing purposes
				g.drawRect(0, 0, FRAME_SIZE/2, 100);


				// in actuality, draw the items that player is carrying
				// each image would be 50x160(?) -- to fit within the panel
			}
		};
		//inventory.setPreferredSize(new Dimension(FRAME_SIZE/2, 100));
		inventoryDrawing.setVisible(true);
		inventory.add(inventoryDrawing);
		playerInfo.add(inventory);
		container.add(playerInfo);

		// buttons for navigation
		setNavigationButtons();


		// add everything to the frame
		frame.add(container);
		frame.pack();
		// resize frame here
		frame.setVisible(true);
	}

	/**
	 * Creates the menu bar for the application window. This has the options:
	 * 		HELP -> synopsis of game
	 * 		GAME -> load a saved game, save current game, load a new game
	 * 		EDIT -> change the layout of the current map (?)
	 * 		QUIT -> exit the game(?)
	 */
	private void setMenuBar() {
		JMenuBar mb = new JMenuBar();

		// HELP: pop-up dialog with game information and instructions
		JMenu help = new JMenu("Help");
		help.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String str = "GAME INFO HERE";		// <-- CHANGE THIS LATER
				JOptionPane.showMessageDialog(frame, str, "Game info", JOptionPane.INFORMATION_MESSAGE);;
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

		JMenuItem load = new JMenuItem("Load");		// load
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// loadGame();
			}

		});

		JMenuItem save = new JMenuItem("Save");		// save
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// saveGame();
			}

		});

		JMenuItem newGame = new JMenuItem("New");		// new game
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onStart();
			}

		});

		game.add(load);
		game.add(save);
		game.add(newGame);

		// EDIT: edit the game world map
		JMenu edit = new JMenu("Edit");
		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// editMap();
			}
		});

		mb.add(help);
		mb.add(game);
		mb.add(edit);
		mb.add(quit);
		frame.setJMenuBar(mb);
	}

	/**
	 *
	 */
	private void setNavigationButtons() {
		JButton west = new JButton("\u2190");
		west.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// move player west
				// redraw player position
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

		JPanel navigation = new JPanel();
		navigation.setMaximumSize(new Dimension(150, 60));
		navigation.setLayout(new GridLayout(2, 3));
		navigation.add(new JButton());		// blank area
		navigation.add(north);
		navigation.add(new JButton());		// blank area
		navigation.add(west);
		navigation.add(south);
		navigation.add(east);
		playerInfo.add(navigation);
		container.add(playerInfo);
	}
}
