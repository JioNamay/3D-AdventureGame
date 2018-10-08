package mapeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public abstract class MapEditorGUI {

	private JFrame frame;
	private JPanel boardPanel;
	private JPanel buttonPanel;
	private JButton entityButton;
	// private JButton
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem saveMenuItem;
	private JMenuItem loadMenuItem;

	public MapEditorGUI() {
		GUI();
	}

	public void GUI() {

		frame = new JFrame("Map Editor");

		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		saveMenuItem = new JMenuItem("Save");
		loadMenuItem = new JMenuItem("Load");
		fileMenu.add(saveMenuItem);
		fileMenu.add(loadMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);

		frame.setLayout(new BorderLayout());
		frame.setSize(500, 450);
		boardPanel = new BoardPanel();
		frame.add(boardPanel, BorderLayout.CENTER);
		entityButton = new JButton("Entity");
		buttonPanel = new JPanel();
		buttonPanel.add(entityButton);
		frame.add(buttonPanel, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	private class BoardPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4409893495609306741L;

		public void paintComponent(Graphics g) {

			g.setColor(Color.white);
			g.fillRect(0, 0, 10 * 32, 10 * 32);
			g.setColor(Color.gray);

			for (int i = 0; i < 10; i++) {
				g.drawLine(i * 32, 0, i * 32, 10 * 32);
			}

			for (int j = 0; j < 10; j++) {
				g.drawLine(0, j * 32, 10 * 32, j * 32);
			}

			g.setColor(Color.black);
			g.drawLine(0, 0, 10 * 32, 0);
			g.drawLine(0, 0, 0, 10 * 32);
			g.drawLine(10 * 32, 0, 10 * 32, 10 * 32);
			g.drawLine(0, 10 * 32, 10 * 32, 10 * 32);
		}

	}
}