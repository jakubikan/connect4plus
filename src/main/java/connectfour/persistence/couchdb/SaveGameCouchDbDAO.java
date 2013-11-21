package connectfour.persistence.couchdb;

import connectfour.model.SaveGame;
import connectfour.persistence.ISaveGameDAO;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import java.net.MalformedURLException;
import java.util.List;

/**
 * User: Stefano Di Martino
 * Date: 20.11.13
 * Time: 12:58
 */
public class SaveGameCouchDbDAO implements ISaveGameDAO {
    private CouchDbConnector db = null;

    public SaveGameCouchDbDAO() {
        openDB();
    }

    @Override
    public void openDB() {
        HttpClient client = null;

        try {
            client = new StdHttpClient.Builder().url("http://lenny2.in.htwg-konstanz.de:5984").build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        CouchDbInstance dbInstance = new StdCouchDbInstance(client);
        db = dbInstance.createConnector("connect4_db", true);
    }

    @Override
    public void saveGame(SaveGame saveGame) {
    }

    @Override
    public SaveGame loadSaveGame(String saveGameName) {
        return null;
    }

    @Override
    public boolean deleteSaveGameIfExists(String saveGameName) {
        return false;
    }

    @Override
    public void closeDB() {
    }

    @Override
    public boolean saveGameExists(String saveGameName) {
        return false;
    }

    @Override
    public List<String> getAllSaveGames() {
        return null;
    }
}
