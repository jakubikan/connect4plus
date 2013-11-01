package connectfour.controller;

import connectfour.model.GameField;
import connectfour.model.Player;

public interface IController {
	public void newGame();
	
	public String getWinner();
    
    public boolean gameHasStarted();
    
    public boolean dropCoinWithSuccessFeedback(final int col);
    
    public Player getPlayerOnTurn();
    
    public boolean userHasWon();
    
    public String getPlayerNameOnTurn();
    
    public void setGameField(final GameField gameField);
    
    public GameField getGameField();
    
    public void undoStep();
    
    public void redoStep();
    
    public void setPlayer(final Player p);
    
    public void setOpponend(final Player p);
    
    // Only for Support. This method sould not be used any more
    // DEPRECATED!!!!!
    // Since its bad to get an Array of Objects.
    public Player[] getPlayers();
    
    public Player getPlayerAt(final int row, final int col);
    
    public Player getOpponend();
    
    public Player getPlayer();
    
    public void useState(final GameField state);
}
