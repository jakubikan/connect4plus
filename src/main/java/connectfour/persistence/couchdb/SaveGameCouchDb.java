package connectfour.persistence.couchdb;

import connectfour.model.GameField;
import connectfour.model.Player;
import connectfour.persistence.hibernate.HibernateUtil;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

/**
 * User: Stefano Di Martino
 * Date: 20.11.13
 * Time: 13:32
 */
public class SaveGameCouchDb extends CouchDbDocument {
    @TypeDiscriminator
    public String id;

    private GameField gameField;
    private Player player1;
    private Player player2;
    private String saveGameName;

    private GameFieldCouchDb gameFieldCouchdb;
    private PlayerCouchDb player1Couchdb;
    private PlayerCouchDb player2Couchdb;

    public SaveGameCouchDb(String saveGameName, GameField gameField, Player player1, Player player2) {
        this.gameField = gameField;
        this.player1Couchdb = CouchDbUtil.convertToPlayerCouchDb(player1);
        this.player2Couchdb = CouchDbUtil.convertToPlayerCouchDb(player2);
        this.saveGameName = saveGameName;
    }


}
