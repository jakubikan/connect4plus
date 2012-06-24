package connectfour.model;

import connectfour.model.gameField.GameField;

public class Computer extends PlayerAbstract {

	private int doNextColumn = 3;
	private final boolean firstMove = true;
	private final int deepSearch = 5;

	@Override
	public void surrender() {
	}

	@Override
	public int dropCoin(final int column) {
		setMove(column);
		return getMove();
	}

	public Computer() {
		super();
	}

	@Override
	public int getMove() {
		/*
		 * Random r = new Random(); int random = r.nextInt() % 7; return random
		 * < 0 ? -random : random;
		 */

		maxWert(7);
		return doNextColumn;

	}

	@Override
	public void setMove(final int column) {

	}

	private int maxWert(final int restTiefe) {
		int ermittelt = -Integer.MAX_VALUE;
		int zugWert;
		for (int i = 0; i < GameField.DEFAULT_COLUMNS; i++) {
			GameField previousState = saveState();
			if (getGameField().dropCoin(i) >= GameField.DEFAULT_ROWS - 1) {
				setGameField(previousState);
				continue;
			}

			if (restTiefe <= 1) {
				zugWert = getGameField().evaluateScore();
			} else {
				zugWert = minWert(restTiefe - 1);
			}
			GameField newState = saveState();
			setGameField(previousState);
			if (zugWert > ermittelt || newState.getWinner() == this) {
				ermittelt = zugWert;
				if (restTiefe >= deepSearch) {
					doNextColumn = i;
				}
			}

		}
		return ermittelt;
	}

	private int minWert(final int restTiefe) {
		int ermittelt = Integer.MAX_VALUE;
		int zugWert;
		for (int i = 0; i < GameField.DEFAULT_COLUMNS; i++) {
			GameField previousState = saveState();
			if (getGameField().dropCoin(i) >= GameField.DEFAULT_ROWS - 1) {
				setGameField(previousState);
				continue;

			}
			GameField newState = saveState();
			if (restTiefe <= 1) {
				zugWert = getGameField().evaluateScore();
			} else {
				zugWert = maxWert(restTiefe - 1);
			}
			setGameField(previousState);
			if (zugWert < ermittelt) {
				ermittelt = zugWert;
			}
		}
		return ermittelt;
	}

	private GameField saveState() {
		GameField state = null;
		try {
			state = getGameField().clone();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}
		return state;

	}
}