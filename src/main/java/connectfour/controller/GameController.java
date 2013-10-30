package connectfour.controller;

import javax.swing.undo.UndoManager;

import com.google.inject.Singleton;

import connectfour.model.GameField;
import connectfour.model.Human;
import connectfour.model.Player;
import connectfour.util.observer.IObserverWithArguments;
import connectfour.util.observer.ObservableWithArguments;

@Singleton
public final class GameController extends ObservableWithArguments implements IObserverWithArguments, IController {
    
    private GameField gameField;
//    private static GameController instance;
    private boolean bGameHasStarted;
    
    private final UndoManager undoManager = new UndoManager();
    
    public GameController() {
        this.undoManager.discardAllEdits();
        this.gameField = new GameField(this);
        this.bGameHasStarted = false;
    }
    
    @Override
    public void newGame() {
        this.bGameHasStarted = true;
        gameField = new GameField(this);
        this.addObserver(gameField.getOpponend());
        this.notifyObservers();
        this.notifyObservers(gameField);
    }
    
    @Override
    public String getWinner() {
        Player winner = gameField.getWinner();
        if (winner != null) {
            return winner.getName();
        } else {
            return "";
        }
    }
    
    @Override
    public boolean gameHasStarted() {
        return this.bGameHasStarted;
    }
    
    @Override
    public boolean dropCoinWithSuccessFeedback(final int col) {
        boolean success = false;
        
        if (!userHasWon()) {
            try {
                GameField previousState = null;
                
                try {
                    previousState = gameField.clone();
                } catch (CloneNotSupportedException e1) {}
                
                Player p = gameField.getPlayerOnTurn();
                p.setGameField(gameField);
                
                //                int move = p.dropCoin(col);
                //                int row = gameField.dropCoin(move);
                
                int row = gameField.dropCoin(col);
                
                gameField.changePlayerTurn(); // Change only on success the
                                              // players turnk
                if (row >= GameField.DEFAULT_ROWS) {
                    success = false;
                    useState(previousState);
                    return success;
                    
                }
                success = true;
                
                GameField newState = null;
                try {
                    newState = gameField.clone();
                } catch (CloneNotSupportedException e) {}
                
                String undoInfo = String.format("Undoing %s Player Move", getPlayerOnTurn()
                                                        .getName());
                GameFieldEdit edit = new GameFieldEdit(this, previousState, newState, undoInfo);
                undoManager.addEdit(edit);
                
                this.notifyObservers();
                this.notifyObservers(gameField);
            } catch (IllegalArgumentException e) {}
        }
        return success;
    }
    
    @Override
    public Player getPlayerOnTurn() {
        return gameField.getPlayerOnTurn();
    }
    
    @Override
    public boolean userHasWon() {
        Player winner = gameField.getWinner();
        
        if (winner == null) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String getPlayerNameOnTurn() {
        Player player = gameField.getPlayerOnTurn();
        return player.getName();
        
    }
    
    @Override
    public void setGameField(final GameField gameField) {
        this.gameField = gameField;
    }
    
    @Override
    public GameField getGameField() {
        return this.gameField;
    }
    
    @Override
    public void undoStep() {
        if (undoManager.canUndo()) {
            undoManager.undo();
        } else {
            return;
        }
    }
    
    @Override
    public void redoStep() {
        if (undoManager.canRedo()) {
            undoManager.redo();
        } else {
            return;
        }
    }
    
    @Override
    public void setPlayer(final Human p) {
        gameField.setPlayer(p);
        
    }
    
    @Override
    public void setOpponend(final Player p) {
        gameField.setOpponend(p);
    }
    
    // Only for Support. This method sould not be used any more
    // DEPRECATED!!!!!
    // Since its bad to get an Array of Objects.
    @Override
    public Player[] getPlayers() {
        return gameField.getPlayers();
    }
    
    @Override
    public Player getPlayerAt(final int row, final int col) {
        return gameField.getPlayerAt(row, col);
    }
    
    @Override
    public Player getOpponend() {
        return gameField.getOpponend();
    }
    
    @Override
    public Human getPlayer() {
        return gameField.getPlayer();
    }
    
    @Override
    public void useState(final GameField state) {
        gameField = state;
    }
    
    @Override
	public void update(Object arg) {
        int columnToDrop = (Integer) arg;
        this.dropCoinWithSuccessFeedback(columnToDrop);
    }
}