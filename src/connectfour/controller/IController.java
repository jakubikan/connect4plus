package connectfour.controller;

import connectfour.model.GameField;
import connectfour.model.Human;
import connectfour.model.Player;

public interface IController {
	public void newGame();
	
	public String getWinner();
    
    public boolean gameHasStarted();
    
    public boolean dropCoinWithSuccessFeedback(final int col);
    
    public Player getPlayerOnTurn();
    
    public boolean userHasWon();
    
    public String getPlayerNameOnTurn();
    
    /**
     * @param gameField
     *            the gameField to set
     */
    public void setGameField(final GameField gameField);
    
    /**
     * @return the gameField
     */
    public GameField getGameField();
    
    public void undoStep();
    
    /**
	 * 
	 */
    public void redoStep();
    
    public void setPlayer(final Human p);
    
    public void setOpponend(final Player p);
    
    // Only for Support. This method sould not be used any more
    // DEPRECATED!!!!!
    // Since its bad to get an Array of Objects.
    public Player[] getPlayers();
    
    public Player getPlayerAt(final int row, final int col);
    
    /**
     * @return
     */
    public Player getOpponend();
    
    /**
     * @return
     */
    public Human getPlayer();
    
    /**
     * @param state
     */
    public void useState(final GameField state);
}
