package connectfour.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import connectfour.model.*;
import connectfour.persistence.ISaveGameDAO;
import connectfour.util.observer.IObserverWithArguments;
import connectfour.util.observer.ObservableWithArguments;

import javax.swing.undo.UndoManager;
import java.util.List;

@Singleton
public final class GameController extends ObservableWithArguments implements IObserverWithArguments, IController {
    private GameField gameField;
    private boolean bGameHasStarted;

    private UndoManager undoManager = new UndoManager();

    @Inject
    private ISaveGameDAO saveGameDAO;
    
    public GameController() {
        this.undoManager.discardAllEdits();
        Player p1 = new Human("Hugo");
        Player opponent = new Computer(this, "Boesewicht");
        this.gameField = new GameField(p1, opponent);
        this.bGameHasStarted = false;
    }
    
    @Override
    public void newGame() {
        this.bGameHasStarted = true;
        Player p1 = new Human("Hugo");
        Player opponent = new Computer(this, "Boesewicht");
        undoManager.discardAllEdits();
        gameField = new GameField(p1, opponent);
        this.addObserver(gameField.getOpponent());
        this.notifyObservers();
        this.notifyObservers(gameField);
    }
    
    @Override
    public void saveGame(String name) {
        SaveGame sg = new SaveGame(name, getGameField(), getPlayer(), getOpponend());

    	saveGameDAO.saveGame(sg);
    }
    
    @Override
    public List<String> getAllSaveGameNames() {
    	return saveGameDAO.getAllSaveGames();
    }
    
    @Override
    public void loadSaveGame(String saveGameName) {
    	SaveGame sg = saveGameDAO.loadSaveGame(saveGameName);
    	this.setGameField(sg.getGameField());
    	this.setPlayer(sg.getPlayer1());
    	this.setOpponend(sg.getPlayer2());
        this.undoManager = new UndoManager();

    	this.removeAllObservers();
    	this.bGameHasStarted = true;
    	this.addObserver(gameField.getOpponent());

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
        boolean success = true;
        
        if (!userHasWon()) {
            try {
                GameField previousState = null;
                
                try {
                    previousState = gameField.clone();
                } catch (CloneNotSupportedException e1) {}
                
                Player p = gameField.getPlayerOnTurn();

                p.setGameField(gameField);
                
                int row = gameField.dropCoin(col);
                
                gameField.changePlayerTurn(); // Change only on success the players turn
                if (row >= GameField.DEFAULT_ROWS) {
                    success = false;
                    useState(previousState);
                    return success;
                }
                
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

        return winner != null;

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
        }
    }
    
    @Override
    public void redoStep() {
        if (undoManager.canRedo()) {
            undoManager.redo();
        }
    }
    
    @Override
    public void setPlayer(final Player p) {
        gameField.setPlayer(p);
    }
    
    @Override
    public void setOpponend(final Player p) {
        gameField.setOpponent(p);
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
        return gameField.getOpponent();
    }
    
    @Override
    public Player getPlayer() {
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