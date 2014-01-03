package connectfour.persistence.couchdb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import connectfour.model.GameField;
import connectfour.model.Player;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

import java.util.logging.Logger;

/**
 * Created by Jakub Werner on 01/02/14.
 */
public class SaveGameCouchDb extends CouchDbDocument {

    private static final Logger log = Logger.getLogger(SaveGameCouchDb.class.getName());

    @TypeDiscriminator
    private String id;

    @JsonProperty("save_game_name")
    private String saveGameName;
    @JsonProperty("gamefield")
    private GameFieldCouchDb gameFieldCouchdb;
    @JsonProperty("player")
    private PlayerCouchDb player;
    @JsonProperty("opponent")
    private PlayerCouchDb opponent;

    @JsonIgnore
    public SaveGameCouchDb( String saveGameName, GameField gameField,  Player player1,  Player player2) {
        try {
            gameFieldCouchdb = CouchDbUtil.convertGameField(gameField);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        this.player = CouchDbUtil.convertPlayer(player1);
        this.opponent = CouchDbUtil.convertPlayer(player2);
        this.saveGameName = saveGameName;
    }
    public SaveGameCouchDb(@JsonProperty("save_game_name") String saveGameName, @JsonProperty("gamefield") GameFieldCouchDb gameField, @JsonProperty("player") PlayerCouchDb player1, @JsonProperty("opponent") PlayerCouchDb player2) {
        gameFieldCouchdb = gameField;
        this.player = player1;
        this.opponent = player2;
        this.saveGameName = saveGameName;

    }



    public String getSaveGameName() {
        return saveGameName;
    }

    public GameFieldCouchDb getGameFieldCouchdb() {
        return gameFieldCouchdb;
    }

    public PlayerCouchDb getPlayer() {
        return player;
    }

    public PlayerCouchDb getOpponent() {
        return opponent;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
