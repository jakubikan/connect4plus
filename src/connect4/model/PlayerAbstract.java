package connect4.model;


public abstract class PlayerAbstract implements Player {
	
	protected String name;
	protected GameField gameField;
	protected Coin playerCoin;

	public GameField getGameField() {
		gameField = GameField.getInstance();
		return gameField;
		
	}

}
