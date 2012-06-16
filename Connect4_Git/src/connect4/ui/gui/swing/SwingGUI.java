package connect4.ui.gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import connect4.controller.GameController;
import connect4.model.Player;
import connect4.model.gameField.GameField;
import connect4.ui.UI;
import connect4.ui.gui.swing.events.ArrowManager;
import connect4.ui.gui.swing.widgets.ArrowCell;
import connect4.ui.gui.swing.widgets.GUICoin;
import connect4.util.observer.IObserver;

/**
* @author:  Stefano Di Martino
* @created: May 27, 2012
*/

@SuppressWarnings("serial")
public class SwingGUI extends JFrame implements UI, IObserver {
	// UI Stuff
	private final JPanel cellWrapper = new JPanel();
	private final GUICoin coinCells[][] = new GUICoin[GameField.DEFAULT_ROWS][GameField.DEFAULT_COLUMNS];
	private final ArrowCell arrowCells[] = new ArrowCell[GameField.DEFAULT_COLUMNS];
	private final StatusDisplay statusDisplay = new StatusDisplay();

	// Game Stuff
	private GameController controller = GameController.getInstance();;
	private Player[] players = controller.getPlayers();

	private void init() {
		for (int row = 0; row < GameField.DEFAULT_ROWS; row++) {
			for (int col = 0; col < GameField.DEFAULT_COLUMNS; col++) {
				coinCells[row][col] = new GUICoin(col);
			}
		}

		for (int col = 0; col < GameField.DEFAULT_COLUMNS; col++) {
			arrowCells[col] = new ArrowCell(col);
		}

		ArrowManager.getInstance().setArrowCells(this.arrowCells);
	}

	private void addCells() {
		for (int col = 0; col < GameField.DEFAULT_COLUMNS; col++) {
			cellWrapper.add(arrowCells[col]);
		}

		for (int row = GameField.DEFAULT_ROWS - 1; row >= 0; row--) {
			for (int col = 0; col < GameField.DEFAULT_COLUMNS; col++) {
				cellWrapper.add(coinCells[row][col]);
			}
		}
	}

	public SwingGUI() {
		this.init();

		final JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);

		// Layout setzen
		cellWrapper.setLayout(new GridLayout(7, 7));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.LINE_AXIS));

		this.addCells();

		cellWrapper.setPreferredSize(new Dimension(400, 300));
		statusDisplay.setPreferredSize(new Dimension(100, 100));

		contentPane.add(cellWrapper);
		contentPane.add(statusDisplay);

		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 0));

		this.setContentPane(contentPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setVisible(true);
		

		this.createBufferStrategy(2);
	}

	@Override
	public void drawGameField() {
		statusDisplay.update();

		for (int currentRow = 0; currentRow < GameField.DEFAULT_ROWS; currentRow++) {
			for (int currentColumn = 0; currentColumn < GameField.DEFAULT_COLUMNS; currentColumn++) {
				Player player = this.controller.getPlayerAt(currentRow,
						currentColumn);

				if (player == null) {
					// Do nothing
				} else if (player == players[0]) {
					coinCells[currentRow][currentColumn].setColor(Color.RED);
				} else if (player == players[1]) {
					coinCells[currentRow][currentColumn].setColor(Color.YELLOW);
				} else {
					JOptionPane.showMessageDialog(this,
							"Fehler beim Einfügen der Münze in Zeile "
									+ currentRow + " und Spalte "
									+ currentColumn + "!");
				}
			}
		}
		this.repaint();
	}

	@Override
	public void update() {
		this.drawGameField();
	}
}