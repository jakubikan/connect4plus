package connectfour.model;

public class SaveGame {
	private GameField gameField;
	private Player player1;
	private Player player2;
	
	public SaveGame(GameField gameField, Player player1, Player player2) {
		this.gameField = gameField;
		this.player1 = player1;
		this.player2 = player2;
		
		// Local gamefield for evaluating gamestrategy shouldn't be saved!
		this.player1.setGameField(null);
		this.player2.setGameField(null);
	}
}