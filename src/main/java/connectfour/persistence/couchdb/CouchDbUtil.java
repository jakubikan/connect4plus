package connectfour.persistence.couchdb;

import connectfour.model.Computer;
import connectfour.model.Player;

/**
 * User: Stefano Di Martino
 * Date: 20.11.13
 * Time: 13:54
 */
public class CouchDbUtil {

    public static PlayerCouchDb convertToPlayerCouchDb(Player player) {
        PlayerCouchDb playerCouchDb = new PlayerCouchDb(player.getName(), player instanceof Computer);

        return playerCouchDb;
    }
}
