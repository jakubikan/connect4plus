package connect4.model;

import java.util.Arrays;

public class GameField {

	public static final int DEFAULT_GAME_WIDTH = 7;
	public static final int DEFAULT_GAME_HEIGHT = 6;

	private Player[][] gameField;

	private static GameField instance;

	public GameField() {
		super();
		gameField = new Player[DEFAULT_GAME_WIDTH][DEFAULT_GAME_HEIGHT];

	}

	public int dropCoin(int column, Player p) {
		int row = 0;

		row = dropCoin(row, column, p);

		return row;
	}

	private int dropCoin(int row, int column, Player p) {
		int row1 = row;

		if (row >= DEFAULT_GAME_HEIGHT || column >= DEFAULT_GAME_WIDTH) {
			throw new IllegalArgumentException();
		}

		if (gameField[column][row1] == null) {
			gameField[column][row1] = p;
		} else {
			row1 = dropCoin(++row1, column, p);
		}
		System.out.println(gameField);

		return row1;
	}

	public static synchronized GameField getInstance() {

		if (instance == null) {
			instance = new GameField();
		}
		return instance;
	}

	public Player getPlayerAt(int row, int column) {

		System.out.println(gameField[column][row]);
		return gameField[column][row];
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Arrays.toString(gameField);

	}

	public void newGame() {
		gameField = new Player[DEFAULT_GAME_WIDTH][DEFAULT_GAME_HEIGHT];
	}
}
