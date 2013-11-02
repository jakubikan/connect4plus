package connectfour.model;

public class SaveGame {
	private GameField gameField;
	private Player player1;
	private Player player2;
	
	public SaveGame(GameField gameField, Player player1, Player player2) {
		this.gameField = gameField;
		this.player1 = player1;
		this.player2 = player2;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Player getPlayer1() {
		return player1;
	}

	public GameField getGameField() {
		return gameField;
	}
}