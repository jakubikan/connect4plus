package connectfour.model;

import connectfour.model.gameField.GameField;

public class Computer extends PlayerAbstract {

	private int doNextColumn = 3;

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

		maxWert(6);
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
			if (getGameField().dropCoin(i) >= GameField.DEFAULT_ROWS) {
				setGameField(previousState);
				continue;
			}

			if (restTiefe <= 1) {
				zugWert = getGameField().evaluatePlayerScore(this);
			} else {
				zugWert = minWert(restTiefe - 1);
			}
			GameField newState = saveState();
			setGameField(previousState);
			System.out.printf(
					"Zugwert: %d, Ermittelt %d, Spalte:%d\n",
					zugWert, ermittelt, i);
			if (zugWert > ermittelt || newState.getWinner() == this) {
				ermittelt = zugWert;
				if (restTiefe <= 3) {
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
			if (getGameField().dropCoin(i) >= GameField.DEFAULT_ROWS) {
				setGameField(previousState);
				continue;

			}
			GameField newState = saveState();
			if (restTiefe <= 1) {
				zugWert = getGameField().evaluatePlayerScore(this);
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