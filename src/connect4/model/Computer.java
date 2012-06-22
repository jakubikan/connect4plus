package connect4.model;

import java.util.Random;

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
	public int dropCoin(final int column) {
		setMove(column);
		return getMove();
	}

	public Computer() {
		super();
	}

	@Override
	public int getMove() {
		Random r = new Random();
		int random = r.nextInt() % 7;
		return random < 0 ? -random : random;

	}

	@Override
	public void setMove(final int column) {

	}

}
