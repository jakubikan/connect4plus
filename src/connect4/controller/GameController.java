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
	private Player[] player;
	private static GameController instance;
	private UI ui;
	private boolean bGameHasStarted;

	public static String newline = System.getProperty("line.separator");

	private GameController() {
		this.gameField = GameField.getInstance();
		this.player = new Player[2];
		this.bGameHasStarted = false;
	}

	public void newGame() {
		this.createPlayer();
		this.ui = new TUI();
		this.bGameHasStarted = true;
	}

	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}

		return instance;
	}

	public boolean gameHasStarted() {
		return this.bGameHasStarted;
	}

	public UI getUI() {
		return this.ui;
	}

	public Player getPlayerNamerOnTurn() {
		return null;
	}

	public int dropCoin(int col, Player player) {
		return this.gameField.dropCoin(col, player);
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
