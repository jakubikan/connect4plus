package connectfour.model;

import java.util.Random;

public class GameField implements Cloneable {

	public static final int DEFAULT_COLUMNS = 7;
	public static final int DEFAULT_ROWS = 6;

	private Player[][] gameField;

	private Player player;
	private Player opponent;
	private Player playerOnTurn;
	private int modCount = 0;


	private Player playerWon;

	private boolean gameWon;


	public GameField(Player player, Player opponent) {
		super();
		gameField = new Player[DEFAULT_ROWS][DEFAULT_COLUMNS];
		playerWon = null;
		gameWon = false;
        this.player = player;
        this.opponent = opponent;
		initPlayerOnTurn();
	}

    public int getModCount() {
        return modCount;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameField(Player[][] gameField) {
        System.arraycopy(gameField,0,this.gameField,0,gameField.length);
    }

    public void setGameIsWon(boolean gameIsWon) {
        this.gameWon = gameIsWon;
    }

    public void setModCount(int modCount) {
        this.modCount = modCount;
    }

    public void setPlayerOnTurn(Player player) {
        this.playerOnTurn = player;
    }

	private void initPlayerOnTurn() {
		Random r = new Random();
		int rInt = r.nextInt();
		if (rInt % 2 == 0) {
			playerOnTurn = player;
		} else {
			playerOnTurn = opponent;
		}

	}


	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int i = DEFAULT_ROWS - 1; i >= 0; --i) {
			for (int j = 0; j < DEFAULT_COLUMNS; ++j) {
				Player actualPlayer = gameField[i][j];
				if (actualPlayer != null
						&& actualPlayer.equals(player)) {
					b.append("[o]");
				} else if (actualPlayer != null
						&& actualPlayer.equals(opponent)) {
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
		p[1] = opponent;
		return p;
	}

	public Player getPlayerOnTurn() {
		return playerOnTurn;
	}

	public void setPlayer(final Player p) {
		player = p;

	}

	public void setOpponent(final Player p) {
		opponent = p;

	}

	public Player getOpponent() {
		return opponent;
	}

	public Player getPlayer() {
		return player;
	}

	public int dropCoin(final int column) {
		int row = dropCoin(column, playerOnTurn);

		modCount++;
		return row;
	}

	private boolean removeLastLineIfFull() {
		if (lastLineFull()) {

            System.arraycopy(gameField, 1, gameField, 0, gameField.length - 1);
			gameField[DEFAULT_ROWS - 1] = new Player[DEFAULT_COLUMNS];
			return true;
		}
		return false;

	}

	private boolean lastLineFull() {
		boolean result = true;
		for (Player element : gameField[0]) {
			result &= element != null;

		}
		return result;
	}

	public int dropCoin(final int column, final Player p) {
		int row = 0;
		row = dropCoin(row, column, p);
		gameWon = hasWon(p);

		if (lastLineFull() && !gameWon) {
			removeLastLineIfFull();
			row--;
		}
		if (gameWon) {
			playerWon = p;
		}
		return row;
	}

    public Player[][] getCopyOfGamefield()  throws CloneNotSupportedException{
        return this.clone().gameField;
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

	public boolean isEmpty() {
		return modCount == 0;

	}

	public Player getWinner() {
		return playerWon;
	}

	private boolean hasWon(final Player playerToCheck) {
		boolean ret = false;
		ret |= checkHorizontal(playerToCheck);
		ret |= checkVertical(playerToCheck);
		ret |= checkLeftRightDiagonal(playerToCheck);
		ret |= checkLeftRigthDiagonal(playerToCheck);

		return ret;

	}

	private boolean checkLeftRigthDiagonal(final Player playerToCheck) {
		for (int row = DEFAULT_ROWS - 1; row >= 3; row--) {
			for (int col = 0; col < DEFAULT_COLUMNS - 3; col++) {
				if (gameField[row][col] != null
                        && gameField[row][col].equals(playerToCheck)
						&& gameField[row][col].equals(gameField[row - 1][col + 1])
						&& gameField[row][col].equals(gameField[row - 2][col + 2])
						&& gameField[row][col].equals(gameField[row - 3][col + 3])) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkLeftRightDiagonal(final Player playerToCheck) {
		for (int row = 0; row < DEFAULT_ROWS - 3; row++) {
			for (int col = 0; col < DEFAULT_COLUMNS - 3; col++) {
				if (gameField[row][col] != null
                        && gameField[row][col].equals(playerToCheck)
						&& gameField[row][col].equals(gameField[row + 1][col + 1])
						&& gameField[row][col].equals(gameField[row + 2][col + 2])
						&& gameField[row][col].equals(gameField[row + 3][col + 3])) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkVertical(final Player playerToCheck) {
		for (int col = 0; col < DEFAULT_COLUMNS; col++) {
			for (int row = 0; row < DEFAULT_ROWS - 3; row++) {
				if (gameField[row][col] != null
                        && gameField[row][col].equals(playerToCheck)
						&& gameField[row][col].equals(gameField[row + 1][col])
						&& gameField[row][col].equals(gameField[row + 2][col])
						&& gameField[row][col].equals(gameField[row + 3][col])) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkHorizontal(final Player playerToCheck) {
		for (int row = 0; row < DEFAULT_ROWS; row++) {
			for (int col = 0; col < DEFAULT_COLUMNS - 3; col++) {
				if (gameField[row][col] != null
                        && gameField[row][col].equals(playerToCheck)
						&& gameField[row][col].equals(gameField[row][col + 1])
						&& gameField[row][col].equals(gameField[row][col + 2])
						&& gameField[row][col].equals(gameField[row][col + 3])) {
					return true;
				}
			}
		}
		return false;
	}

	public void changePlayerTurn() {
		if (playerOnTurn.equals(opponent)) {
			playerOnTurn = player;
		} else if (playerOnTurn.equals(player)) {
			playerOnTurn = opponent;
		}
	}

	public int evaluateScore() {

		Player opponand = playerOnTurn.equals(player) ? opponent
				: player;
		int plScore = evaluatePlayerScore(playerOnTurn);
		int opScore = evaluatePlayerScore(opponand);

		return plScore - opScore;

	}

	private void evaluateVertical(final Player playerToCheck,
			final int counters[]) {
		int count = 0;
		for (int row = 0; row < DEFAULT_ROWS; row++) {
			count = 0;
			for (int col = 0; col < DEFAULT_COLUMNS - 1; col++) {
				if (gameField[row][col] != null
                        && gameField[row][col].equals(playerToCheck)
						&& gameField[row][col].equals(gameField[row][col + 1])) {
					count++;
				}
			}
			counters[count]++;
		}
	}

	private void evaluateHorizontal(final Player playerToCheck,
			final int counters[]) {
		int count = 0;

		for (int col = 0; col < DEFAULT_COLUMNS; col++) {
			count = 0;
			for (int row = 0; row < DEFAULT_ROWS - 1; row++) {
				if (gameField[row][col] != null &&
                        gameField[row][col].equals(playerToCheck)
						&& gameField[row][col].equals(gameField[row + 1][col])) {
					count++;
				}
			}
			counters[count]++;
		}
	}

	private void evaluateDiagonalFromLeftToRight(
			final Player playerToCheck, final int counters[]) {
		int count = 0;
		for (int row = 0; row < DEFAULT_ROWS - 1; row++) {
			count = 0;
			for (int col = 0; col < DEFAULT_COLUMNS - 1; col++) {
				if (gameField[row][col] != null &&
                        gameField[row][col].equals(playerToCheck)
						&& gameField[row][col].equals(gameField[row + 1][col + 1])) {
					count++;
				}
			}
			counters[count]++;
		}
	}

	private void evaluateDiagonalFromRigthToLeft(
			final Player playerToCheck, final int counters[]) {
		int count = 0;
		for (int row = 0; row < DEFAULT_ROWS - 1; row++) {
			count = 0;
			for (int col = 0; col < DEFAULT_COLUMNS - 1; col++) {
				if (gameField[row][col] != null &&
                        gameField[row][col].equals(playerToCheck)
						&& gameField[row][col].equals(gameField[row + 1][col + 1])) {
					count++;
				}
			}
			counters[count]++;
		}
	}


	public int evaluatePlayerScore(final Player playerToCheck) {

		int counters[] = new int[10];

		evaluateVertical(playerToCheck, counters);
		evaluateHorizontal(playerToCheck, counters);
		evaluateDiagonalFromLeftToRight(playerToCheck, counters);
		evaluateDiagonalFromRigthToLeft(playerToCheck, counters);

		int result = 0;
		int scoreMulitplier = 1;
		for (int counter : counters) {
			result += counter * scoreMulitplier;
			scoreMulitplier *= 2;
		}

		return result;
	}

	@Override
	public GameField clone() throws CloneNotSupportedException {
		GameField gf = (GameField) super.clone();
		gf.gameField = this.gameField.clone();
		for (int i = 0; i < DEFAULT_ROWS; i++) {
			gf.gameField[i] = this.gameField[i].clone();
		}

		return gf;

	}

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        GameField gameField = (GameField) o;

        if (gameWon != gameField.gameWon) { return false; }
        if (modCount != gameField.modCount) { return false; }
        if (opponent != null ? !opponent.equals(gameField.opponent) : gameField.opponent != null) { return false; }
        if (player != null ? !player.equals(gameField.player) : gameField.player != null) { return false; }
        if (playerOnTurn != null ? !playerOnTurn.equals(gameField.playerOnTurn) : gameField.playerOnTurn != null) { return false; }
        if (playerWon != null ? !playerWon.equals(gameField.playerWon) : gameField.playerWon != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (opponent != null ? opponent.hashCode() : 0);
        result = 31 * result + (playerOnTurn != null ? playerOnTurn.hashCode() : 0);
        result = 31 * result + modCount;
        result = 31 * result + (playerWon != null ? playerWon.hashCode() : 0);
        result = 31 * result + (gameWon ? 1 : 0);
        return result;
    }
}