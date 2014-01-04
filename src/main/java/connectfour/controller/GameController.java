package connectfour.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import connectfour.model.*;
import connectfour.persistence.ISaveGameDAO;
import connectfour.util.observer.IObserver;
import connectfour.util.observer.IObserverWithArguments;
import connectfour.util.observer.ObservableWithArguments;

import javax.swing.undo.UndoManager;
import java.util.List;
import java.util.logging.Logger;

@Singleton
public final class GameController extends ObservableWithArguments implements IObserverWithArguments, IController {


    private static final Logger log = Logger.getLogger(GameController.class.getName());

    private GameField gameField;
    private boolean bGameHasStarted;


    @Inject
    private HighScoreController scoreController;

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
    public void newGame(Player player, Player opponent) {
        this.bGameHasStarted = true;
        undoManager.discardAllEdits();
        gameField = new GameField(player, opponent);
        // in Player vs player there is no undo
        undoManager.die();
        this.notifyObservers();
        this.notifyObservers(gameField);

    }


    @Override
    public void saveGame(String name) {
        SaveGame sg = null;
        try {
            sg = new SaveGame(name, getGameField().clone(), getPlayer(), getOpponend());
        } catch (CloneNotSupportedException e) {
            log.info("clone not supported in saveGame");
        }

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


                // Sending highscore if previouse state has not won, and newState haswon
                // this fixes that multiple submitions will be done on sending highscores.
                if (!previousState.isGameWon() && newState.isGameWon()){
                    scoreController.sendHighScore("connect4plus",getWinner(), getGameField().evaluatePlayerScore(getGameField().getWinner()));
                }


                String undoInfo = String.format("Undoing %s Player Move", getPlayerOnTurn()
                                                        .getName());
                GameFieldEdit edit = new GameFieldEdit(this, previousState, newState, undoInfo);
                undoManager.addEdit(edit);

                this.notifyObservers();
                this.notifyObservers(gameField);
            } catch (IllegalArgumentException e) {
                log.info(e.getMessage());

            }
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