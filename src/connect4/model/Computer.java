package connect4.model;

import java.util.Random;

import connect4.model.gameField.GameField;

public class Computer extends PlayerAbstract {

	@Override
	public void surrender() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see connect4.model.Player#dropCoin(int)
	 */
	@Override
	public int dropCoin(final int column, final GameField g) {
		Random r = new Random();
		int random = r.nextInt() % 7;
		return g.dropCoin(random < 0 ? -random : random, this);
	}

}
