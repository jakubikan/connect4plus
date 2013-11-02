package connectfour.model;

public class SaveGame {
	private GameField gameField;
	private Player player1;
	private Player player2;
	private String saveGameName;
	
	/**
	 * @param saveGameName Unique save game name, otherwise it will be overwritten!
	 * @param gameField gameField to save
	 * @param player1 player 1 to save
	 * @param player2 player 2 to save
	 */
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