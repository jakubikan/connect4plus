package connect4.model.gameField;

import java.awt.Color;
import java.util.Random;

import connect4.model.Coin;
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

		if (row >= DEFAULT_ROWS || column >= DEFAULT_COLUMNS) {
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

		// Check horizontal
		for (int rows = 0; rows < DEFAULT_ROWS; rows++) {
			for (int columns = 0; columns < DEFAULT_COLUMNS - 3; columns++) {
				int checkCount = (gameField[rows][columns] == playerToCheck) ? 1
						: 0;
				checkCount += (gameField[rows][columns + 1] == playerToCheck) ? 1
						: 0;
				checkCount += (gameField[rows][columns + 2] == playerToCheck) ? 1
						: 0;
				checkCount += (gameField[rows][columns + 3] == playerToCheck) ? 1
						: 0;
				if (checkCount == 4) {
					return true;
				}
			}
		}
		// Check vertical
		for (int rows = 0; rows < DEFAULT_ROWS - 3; rows++) {
			for (int columns = 0; columns < DEFAULT_COLUMNS; columns++) {
				int checkCount = (gameField[rows][columns] == playerToCheck) ? 1
						: 0;
				checkCount += (gameField[rows + 1][columns] == playerToCheck) ? 1
						: 0;
				checkCount += (gameField[rows + 2][columns] == playerToCheck) ? 1
						: 0;
				checkCount += (gameField[rows + 3][columns] == playerToCheck) ? 1
						: 0;
				if (checkCount == 4) {
					return true;
				}
			}
		}
		// Check diagonal /
		for (int rows = 0; rows < DEFAULT_ROWS - 3; rows++) {
			for (int columns = 0; columns < DEFAULT_COLUMNS - 3; columns++) {
				int checkCount = (gameField[columns][rows] == playerToCheck) ? 1
						: 0;
				checkCount += (gameField[rows + 1][columns + 1] == playerToCheck) ? 1
						: 0;
				checkCount += (gameField[rows + 2][columns + 2] == playerToCheck) ? 1
						: 0;
				checkCount += (gameField[rows + 3][columns + 3] == playerToCheck) ? 1
						: 0;
				if (checkCount == 4) {
					return true;
				}
			}
		}
		// Check Diagonal \
		for (int rows = 0; rows < DEFAULT_ROWS - 3; rows++) {
			for (int columns = 3; columns < DEFAULT_COLUMNS; columns++) {
				int checkCount = (gameField[rows][columns] == playerToCheck) ? 1
						: 0;
				checkCount += (gameField[rows + 1][columns - 1] == playerToCheck) ? 1
						: 0;
				checkCount += (gameField[rows + 2][columns - 2] == playerToCheck) ? 1
						: 0;
				checkCount += (gameField[rows + 3][columns - 3] == playerToCheck) ? 1
						: 0;
				if (checkCount == 4) {
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
