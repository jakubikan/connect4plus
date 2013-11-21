package connectfour.persistence.couchdb;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

/**
 * User: Stefano Di Martino
 * Date: 20.11.13
 * Time: 13:34
 */
public class PlayerCouchDb extends CouchDbDocument {
    @TypeDiscriminator
    public String id;

    private String name;
    private boolean isComputer;

    public PlayerCouchDb(String name, boolean isComputer) {
        this.name = name;
        this.isComputer = isComputer;
    }

}