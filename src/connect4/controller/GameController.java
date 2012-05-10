package connect4.controller;

import java.awt.Color;

import connect4.model.Coin;
import connect4.model.GameField;
import connect4.model.Human;
import connect4.model.Player;
import connect4.ui.UI;
import connect4.ui.tui.TUI;

public class GameController {

	private GameField gameField;
	private Player[] player = new Player[2];
	private static GameController instance;
	private UI ui;

	public static String newline = System.getProperty("line.separator");

	private GameController() {
		this.init();
	}

	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}

		return instance;
	}

	public UI getUI() {
		return this.ui;
	}

	public void newGame() {
		this.createPlayer();
		this.ui = new TUI();
	}

	private void init() {
		this.gameField = GameField.getInstance();
	}

	public Player getPlayerNamerOnTurn() {
		return null;
	}

	public void dropCoin(int col, Player player) {
		this.gameField.dropCoin(col, player);
	}

	public void undoStep() {
	}

	public Player[] getPlayers() {
		return this.player;
	}

	public Player getPlayerAt(int row, int col) {
		return this.gameField.getPlayerAt(row, col);
	}

	private void createPlayer() {
		player[0] = new Human(new Coin(Color.YELLOW));
		player[1] = new Human(new Coin(Color.RED));
	}

}
