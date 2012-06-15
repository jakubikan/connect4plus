package connect4.model;

import connect4.model.gameField.GameField;


public class Human extends PlayerAbstract {

	public Human(Coin playerCoin) {
		if (playerCoin == null)
			throw new IllegalArgumentException();
		this.playerCoin = playerCoin;
	}


	@Override
	public void surrender() {
		// TODO Auto-generated method stub

	}


	/* (non-Javadoc)
	 * @see connect4.model.Player#dropCoin(int, connect4.model.gameField.GameField)
	 */
	@Override
	public int dropCoin(int column, GameField g) {
		return g.dropCoin(column, this);
	}

}
