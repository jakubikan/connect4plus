package connect4.model.gameField;

import java.util.Random;

import connect4.model.Computer;
import connect4.model.Human;
import connect4.model.Player;

public class GameField implements Cloneable {

	public static final int DEFAULT_COLUMNS = 7;
	public static final int DEFAULT_ROWS = 6;

	private Player[][] gameField;

	private Human player;
	private Player opponend;
	private Player playerOnTurn;

	private Player playerWon;
	private boolean gameWon = false;

	public GameField() {
		super();
		gameField = new Player[DEFAULT_ROWS][DEFAULT_COLUMNS];
		playerWon = null;
		gameWon = false;
		player = new Human(new Coin(Color.RED));
		player.setName("Hugo");
		opponend = new Computer();
		opponend.setName("Boesewicht");
		initPlayerOnTurn();
	}

	private void initPlayerOnTurn() {
		Random r = new Random();
		int rInt = r.nextInt();
		if (rInt % 2 == 0) {
			playerOnTurn = player;
		} else {
			playerOnTurn = opponend;
		}

	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int i = DEFAULT_ROWS - 1; i >= 0; --i) {
			for (int j = 0; j < DEFAULT_COLUMNS; ++j) {
				Player actualPlayer = gameField[i][j];
				if (actualPlayer == player) {
					b.append("[o]");
				} else if (actualPlayer == opponend) {
					b.append("[x]");
				} else {
					b.append("[-]");
				}
			}
			b.append("\n");
		}

		return b.toString();
	}

	// Only for Support. This method sould not be used any more
	// DEPRECATED!!!!!
	// Since its bad to get an Array of Objects.
	public Player[] getPlayers() {
		Player[] p = new Player[2];
		p[0] = player;
		p[1] = opponend;
		return p;
	}

	public Player getPlayerOnTurn() {
		return playerOnTurn;
	}

	public void setPlayer(final Human p) {
		player = p;

	}

	public void setOpponend(final Player p) {
		opponend = p;

	}

	public Player getOpponend() {
		return opponend;
	}

	public Human getPlayer() {
		return player;
	}

	// This dropCoin function delegates to the
	// player.dropCoin function
	// The Player therefore calls the dropCoin(colum, player)
	// function
	public int dropCoin(final int column) {
		int row = 0;
		row = dropCoin(column, playerOnTurn);
		return row;
	}

	public int dropCoin(final int column, final Player p) {
		int row = 0;
		row = dropCoin(row, column, p);
		gameWon = hasWon(p, row, column);
		if (gameWon) {
			playerWon = p;
		}
		return row;
	}

	private int dropCoin(final int row, final int column,
			final Player p) {
		int row1 = row;

		if (row >= DEFAULT_COLUMNS - 1) {
			return row1;
		}

		if (column >= DEFAULT_COLUMNS) {
			throw new IllegalArgumentException();
		}

		if (gameField[row1][column] == null) {
			gameField[row1][column] = p;
		} else {
			row1 = dropCoin(++row1, column, p);
		}

		return row1;
	}

	public Player getPlayerAt(final int row, final int column) {
		return gameField[row][column];
	}

	public Player getWinner() {
		return playerWon;
	}

	private boolean hasWon(final Player playerToCheck,
			final int rowToCheck, final int columnToCheck) {
		// see if there are 4 disks in a row, horizontal, vertical or diagonal
		// horizontal rows
		for (int row = 0; row < DEFAULT_ROWS; row++) {
			for (int col = 0; col < DEFAULT_COLUMNS - 3; col++) {
				if (gameField[row][col] == playerToCheck
						&& gameField[row][col] == gameField[row][col + 1]
						&& gameField[row][col] == gameField[row][col + 2]
						&& gameField[row][col] == gameField[row][col + 3]) {
					return true;
				}
			}
		}
		// vertical columns
		for (int col = 0; col < DEFAULT_COLUMNS; col++) {
			for (int row = 0; row < DEFAULT_ROWS - 3; row++) {
				if (gameField[row][col] == playerToCheck
						&& gameField[row][col] == gameField[row + 1][col]
						&& gameField[row][col] == gameField[row + 2][col]
						&& gameField[row][col] == gameField[row + 3][col]) {
					return true;
				}
			}
		}
		// diagonal lower left to upper right
		for (int row = 0; row < DEFAULT_ROWS - 3; row++) {
			for (int col = 0; col < DEFAULT_COLUMNS - 3; col++) {
				if (gameField[row][col] == playerToCheck
						&& gameField[row][col] == gameField[row + 1][col + 1]
						&& gameField[row][col] == gameField[row + 2][col + 2]
						&& gameField[row][col] == gameField[row + 3][col + 3]) {
					return true;
				}
			}
		}
		// diagonal upper left to lower right
		for (int row = DEFAULT_ROWS - 1; row >= 3; row--) {
			for (int col = 0; col < DEFAULT_COLUMNS - 3; col++) {
				if (gameField[row][col] == playerToCheck
						&& gameField[row][col] == gameField[row - 1][col + 1]
						&& gameField[row][col] == gameField[row - 2][col + 2]
						&& gameField[row][col] == gameField[row - 3][col + 3]) {
					return true;
				}
			}
		}

		return false;

	}

	public void changePlayerTurn() {
		if (playerOnTurn == opponend) {
			playerOnTurn = player;
		} else if (playerOnTurn == player) {
			playerOnTurn = opponend;
		}
	}

	public int evaluatePlayerScore() {

		int[] scoreField = new int[10];
		Player playerToCheck = getPlayerOnTurn();

		int numMatches;

		// check horizontally
		for (int y = 0; y < DEFAULT_ROWS; y++) {
			numMatches = 0;
			for (int x = 0; x + 1 < DEFAULT_COLUMNS; x++) {
				if ((gameField[y][x] == gameField[y][x + 1])
						&& ((gameField[y][x] == playerToCheck ? 1 : 0) > 0)) {
					numMatches++;
				}
				scoreField[numMatches]++;
			}
		}

		// check vertically
		for (int x = 0; x < DEFAULT_COLUMNS; x++) {
			numMatches = 0;
			for (int y = 0; y + 1 < DEFAULT_ROWS; y++) {
				if ((gameField[y][x] == gameField[y + 1][x])
						&& ((gameField[y][x] == playerToCheck ? 1 : 0) > 0)) {
					numMatches++;
				}
				scoreField[numMatches]++;
			}
		}

		int result = 0;
		result += 32 * scoreField[4];
		result += 17 * scoreField[3];
		result += 4 * scoreField[2];
		result += 1 * scoreField[1];

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public GameField clone() throws CloneNotSupportedException {
		GameField gf = (GameField) super.clone();
		gf.gameField = this.gameField.clone();
		for (int i = 0; i < DEFAULT_ROWS; i++) {
			gf.gameField[i] = this.gameField[i].clone();
		}
		return gf;
	}
}
