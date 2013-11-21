package connectfour.persistence.couchdb;

import connectfour.model.GameField;
import connectfour.model.Player;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

/**
 * User: Stefano Di Martino
 * Date: 20.11.13
 * Time: 13:36
 */
public class GameFieldCouchDb extends CouchDbDocument {

    @TypeDiscriminator
    public String id;

    private PlayerCouchDb[][] gameFieldCouchDb;

    private PlayerCouchDb playerCouchDb;
    private PlayerCouchDb opponentCouchDb;
    private PlayerCouchDb playerOnTurnCouchDb;
    private PlayerCouchDb playerWon;
    private int modCount = 0;

    private Player player;
    private Player opponent;
    private Player playerOnTurn;


    private boolean gameWon;


    public GameFieldCouchDb(Player player, Player opponent, Player[][] gameField, Player playerOnTurn, int modCount, Player playerWon, boolean gameWon) {
        this.playerCouchDb = CouchDbUtil.convertToPlayerCouchDb(player);
        this.opponentCouchDb = CouchDbUtil.convertToPlayerCouchDb(opponent);
        this.modCount = modCount;

        if (player == playerOnTurn) {
            this.playerOnTurnCouchDb = playerCouchDb;
        } else if (opponent == playerOnTurn) {
            this.playerOnTurnCouchDb = opponentCouchDb;
        } else {
            throw new IllegalStateException("Neither player nor oppent is on turn!");
        }

        this.gameWon = gameWon;
        this.playerOnTurnCouchDb = CouchDbUtil.convertToPlayerCouchDb(playerOnTurn);

        this.player = player;
        this.opponent = opponent;
        this.playerOnTurn = playerOnTurn;

        convertToGameFieldCouchDb(gameField);
    }

    private void convertToGameFieldCouchDb(Player[][] gameField) {
        for (int i = 0; i < GameField.DEFAULT_ROWS; i++) {
            for (int j = 0; j < GameField.DEFAULT_COLUMNS; j++) {

                if (player == gameField[i][j]) {
                    gameFieldCouchDb[i][j] = playerCouchDb;
                } else if (opponent == gameField[i][j]) {
                    gameFieldCouchDb[i][j] = opponentCouchDb;
                } else if (gameField[i][j] == null) {
                    gameFieldCouchDb[i][j] = null;
                } else {
                    throw new IllegalStateException("Can't convert GameField to GameFieldCouchDb!");
                }

            }
        }
    }

    public int getModCount() {
        return modCount;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameField(PlayerCouchDb[][] gameField) {
        this.gameFieldCouchDb = gameField;
    }

    public void setGameIsWon(boolean gameIsWon) {
        this.gameWon = gameIsWon;
    }

    public void setModCount(int modCount) {
        this.modCount = modCount;
    }

    public void setPlayerOnTurnCouchDb(PlayerCouchDb player) {
        this.playerOnTurnCouchDb = player;
    }
}
