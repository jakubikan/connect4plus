package connectfour.controller;

import connectfour.model.GameField;
import connectfour.model.PlayerAbstract;
import connectfour.util.observer.IObserverWithArguments;

public class Computer extends PlayerAbstract {

	private int doNextColumn = 3;
	private final int deepSearch;
	private boolean firstMove = true;
	private final int difficulty = 5;
	private IController controller;

	@Override
	public void surrender() {
	}

	@Override
	public int dropCoin(final int column) {
		setMove(column);
		return getMove();
	}

	public Computer(final IObserverWithArguments controllerObserver, IController controller) {
		super();
		this.addObserver(controllerObserver);
		deepSearch = difficulty;
		this.controller = controller;
	}

	@Override
	public int getMove() {
		/*
		 * Random r = new Random(); int random = r.nextInt() % 7; return random
		 * < 0 ? -random : random;
		 */
		if (firstMove && controller.getGameField().isEmpty()) {
			firstMove = false;
			return doNextColumn;
		}
		maxWert(difficulty);
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
			if (controller.getGameField().getPlayerOnTurn() != this) {
				getGameField().changePlayerTurn();
			}

			if (controller.getGameField().dropCoin(i) >= GameField.DEFAULT_ROWS) {
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
			if (newState.getWinner() != null) {
				zugWert = +1000000;
			}
			if (zugWert > ermittelt) {
				ermittelt = zugWert;
				if (restTiefe >= deepSearch
						|| (newState.getWinner() == this && restTiefe >= deepSearch)) {
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
			if (getGameField().getPlayerOnTurn() == this) {
				getGameField().changePlayerTurn();
			}
			if (getGameField().dropCoin(i) >= GameField.DEFAULT_ROWS) {
				setGameField(previousState);
				continue;

			}
			GameField newState = saveState();
			if (restTiefe <= 1) {
				zugWert = getGameField().evaluateScore();
			} else {
				zugWert = maxWert(restTiefe - 1);
			}

			if (newState.getWinner() != null) {
				zugWert = -1000000;
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
		}
		return state;

	}

	@Override
	public void update(final Object arg) {
		GameField gameField = (GameField) arg;
		this.setGameField(gameField);
		if (gameField.getPlayerOnTurn() == this) {
			int columnToDrop = this.getMove();
			this.notifyObservers(columnToDrop);
		}
	}
}