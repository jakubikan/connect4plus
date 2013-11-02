package connectfour.model;

public class SaveGame {
	private GameField gameField;
	private Player player1;
	private Player player2;
	private String saveGameName;
	
	public SaveGame(String saveGameName, GameField gameField, Player player1, Player player2) {
		this.gameField = gameField;
		this.player1 = player1;
		this.player2 = player2;
		this.saveGameName = saveGameName;
	}

	public String getSaveGameName() {
		return saveGameName;
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