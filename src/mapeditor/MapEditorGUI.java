package mapeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MapEditorGUI {

	private JFrame frame;
	private JPanel boardPanel;
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

	public MapEditorGUI() {
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

		/* BOARD */
		boardPanel = new BoardPanel();
		boardPanel.setBounds(0, 0, 280, 280);
		frame.add(boardPanel, BorderLayout.CENTER);
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
			}

		});

		roomSelectLabel.setBounds(300, 0, 500, 60);
		buttonPanel.add(roomSelectLabel);
		petList.setBounds(427, 0, 170, 60);
		buttonPanel.add(petList);
		/* ROOM SELECTION */

		rockButton = new JButton("Rock (contains key)");
		rockButton.setBounds(300, 60, 170, 60);
		buttonPanel.add(rockButton);

		sofaButton = new JButton("Sofa");
		sofaButton.setBounds(470, 60, 170, 60);
		buttonPanel.add(sofaButton);

		tableButton = new JButton("Table");
		tableButton.setBounds(300, 120, 170, 60);
		buttonPanel.add(tableButton);

		treeButton = new JButton("Tree");
		treeButton.setBounds(470, 120, 170, 60);
		buttonPanel.add(treeButton);

		noteButton = new JButton("Note");
		noteButton.setBounds(470, 120, 170, 60);
		buttonPanel.add(noteButton);

		bedButton = new JButton("Bed");
		bedButton.setBounds(300, 180, 170, 60);
		buttonPanel.add(bedButton);

		fountainButton = new JButton("Fountain");
		fountainButton.setBounds(470, 180, 170, 60);
		buttonPanel.add(fountainButton);

		cactusButton = new JButton("Cactus");
		cactusButton.setBounds(300, 240, 170, 60);
		buttonPanel.add(cactusButton);

		bookshelfButton = new JButton("Bookshelf");
		bookshelfButton.setBounds(470, 240, 170, 60);
		buttonPanel.add(bookshelfButton);

		treasureChestButton = new JButton("Treasure Chest");
		treasureChestButton.setBounds(300, 300, 170, 60);
		buttonPanel.add(treasureChestButton);

		wallBlockButton = new JButton("Wall Block");
		wallBlockButton.setBounds(470, 300, 170, 60);
		buttonPanel.add(wallBlockButton);

		doorButton = new JButton("Door");
		doorButton.setBounds(385, 360, 170, 60);
		buttonPanel.add(doorButton);

		frame.add(buttonPanel);
		frame.setVisible(true);
	}

	private class BoardPanel extends JPanel {

		public void paintComponent(Graphics g) {

			g.setColor(Color.white);
			g.fillRect(0, 0, 7 * 40, 7 * 40);
			g.setColor(Color.gray);

			for (int i = 0; i < 7; i++) {
				g.drawLine(i * 40, 0, i * 40, 7 * 40);
			}

			for (int j = 0; j < 7; j++) {
				g.drawLine(0, j * 40, 7 * 40, j * 40);
			}

			g.setColor(Color.black);
			g.drawLine(0, 0, 7 * 40, 0);
			g.drawLine(0, 0, 0, 7 * 40);
			g.drawLine(7 * 40, 0, 7 * 40, 7 * 40);
			g.drawLine(0, 7 * 40, 7 * 40, 7 * 40);
		}

	}
}