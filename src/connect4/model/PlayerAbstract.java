package connect4.model;


public abstract class PlayerAbstract implements Player {

	protected String name;
	protected Coin playerCoin;

	/*
	 * (non-Javadoc)
	 * @see connect4.model.Player#getCoin()
	 */
	@Override
	public Coin getCoin() {
		// TODO Auto-generated method stub
		return playerCoin;
	}

	/*
	 * (non-Javadoc)
	 * @see connect4.model.Player#setCoin(connect4.model.Coin)
	 */
	@Override
	public void setCoin(final Coin c) {
		// TODO Auto-generated method stub
		playerCoin = c;

	}

	@Override
	public String getName() {
		return name;

	}

	@Override
	public void setName(final String string) {
		// TODO Auto-generated method stub
		name = string;

	}

}
