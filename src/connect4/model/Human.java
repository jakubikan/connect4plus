package connect4.model;

public class Human extends PlayerAbstract {

	int move = 0;

	public Human(final Coin playerCoin) {
		if (playerCoin == null) {
			throw new IllegalArgumentException();
		}
		this.playerCoin = playerCoin;
	}

	@Override
	public void surrender() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see connect4.model.Player#dropCoin(int,
	 * connect4.model.gameField.GameField)
	 */
	@Override
	public int dropCoin(final int column) {
		setMove(column);
		return getMove();
	}

	@Override
	public int getMove() {
		return move;
	}

	@Override
	public void setMove(final int column) {
		move = column;

	}

}
