package connectfour.persistence.couchdb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import connectfour.model.GameField;
import connectfour.model.Player;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

/**
 * Created by Jakub Werner on 01/02/14.
 */
public class GameFieldCouchDb extends CouchDbDocument {

    @TypeDiscriminator
    private String id;
    @JsonProperty("game_field")
    private PlayerCouchDb[][] gameFieldCouchDb;
    @JsonProperty("player")
    private PlayerCouchDb playerCouchDb;
    @JsonProperty("opponent")
    private PlayerCouchDb opponentCouchDb;
    @JsonProperty("player_on_turn")
    private PlayerCouchDb playerOnTurnCouchDb;
    @JsonProperty("player_won")
    private PlayerCouchDb playerWon;
    @JsonProperty("mod_count")
    private int modCount = 0;
    @JsonProperty("game_won")
    private boolean gameWon;


    @JsonIgnore
    public GameFieldCouchDb(Player player, Player opponent, Player[][] gameField, Player playerOnTurn, int modCount, Player playerWon, boolean gameWon) {
        this.playerCouchDb = CouchDbUtil.convertPlayer(player);
        this.opponentCouchDb = CouchDbUtil.convertPlayer(opponent);
        this.modCount = modCount;

        if (player == playerOnTurn) {
            this.playerOnTurnCouchDb = playerCouchDb;
        } else if (opponent == playerOnTurn) {
            this.playerOnTurnCouchDb = opponentCouchDb;
        } else {
            throw new IllegalStateException("Neither player nor oppent is on turn!");
        }

        this.gameWon = gameWon;
        this.playerOnTurnCouchDb = CouchDbUtil.convertPlayer(playerOnTurn);
        gameFieldCouchDb = CouchDbUtil.convertGameFieldMatrix(gameField, player, opponent, playerCouchDb, opponentCouchDb);
    }

    public GameFieldCouchDb(@JsonProperty("player") PlayerCouchDb player,
                            @JsonProperty("opponent") PlayerCouchDb opponent,
                            @JsonProperty("game_field") PlayerCouchDb[][] gameField,
                            @JsonProperty("player_on_turn") PlayerCouchDb playerOnTurn,
                            @JsonProperty("mod_count") int modCount,
                            @JsonProperty("player_won") PlayerCouchDb playerWon,
                            @JsonProperty("game_won") boolean gameWon) {
        this.playerCouchDb = player;
        this.opponentCouchDb = opponent;
        this.gameFieldCouchDb = gameField;
        this.playerOnTurnCouchDb = playerCouchDb;
        this.modCount = modCount;
        this.playerWon = playerWon;
        this.gameWon = gameWon;

    }

    public PlayerCouchDb[][] getGameFieldCouchDb() {
        return gameFieldCouchDb;
    }

    public PlayerCouchDb getPlayerCouchDb() {
        return playerCouchDb;
    }

    public PlayerCouchDb getOpponentCouchDb() {
        return opponentCouchDb;
    }

    public PlayerCouchDb getPlayerOnTurnCouchDb() {
        return playerOnTurnCouchDb;
    }

    public void setPlayerOnTurnCouchDb(PlayerCouchDb player) {
        this.playerOnTurnCouchDb = player;
    }

    public PlayerCouchDb getPlayerWon() {
        return playerWon;
    }

    public int getModCount() {
        return modCount;
    }

    public void setModCount(int modCount) {
        this.modCount = modCount;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameField(PlayerCouchDb[][] gameField) {
        System.arraycopy(gameField,0,gameFieldCouchDb,0,gameField.length);
    }

    public void setGameIsWon(boolean gameIsWon) {
        this.gameWon = gameIsWon;
    }
}
