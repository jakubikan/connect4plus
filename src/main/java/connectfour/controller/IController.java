package connectfour.controller;

import connectfour.model.GameField;
import connectfour.model.Player;

import java.util.List;

public interface IController {
	 void newGame();
	
	 String getWinner();
    
     boolean gameHasStarted();
    
     boolean dropCoinWithSuccessFeedback(final int col);
    
     Player getPlayerOnTurn();
    
     boolean userHasWon();
    
     String getPlayerNameOnTurn();
    
     void setGameField(final GameField gameField);
    
     GameField getGameField();
    
     void undoStep();
    
     void redoStep();
    
     void setPlayer(final Player p);
    
     void setOpponend(final Player p);
    
    // Only for Support. This method sould not be used any more
    // DEPRECATED!!!!!
    // Since its bad to get an Array of Objects.
     Player[] getPlayers();
    
     Player getPlayerAt(final int row, final int col);
    
     Player getOpponend();
    
     Player getPlayer();
    
     void useState(final GameField state);
    
     /**
      * @param name Name must be unique, otherwise it will be overwritten.
      */
     void saveGame(String name);
     
     List<String> getAllSaveGameNames();
     
     void loadSaveGame(String saveGameName);
}
