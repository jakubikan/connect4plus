package connectfour.persistence.couchdb;

import com.google.inject.Inject;

import connectfour.controller.IController;
import connectfour.model.SaveGame;
import connectfour.persistence.ISaveGameDAO;
import connectfour.util.observer.IObserverWithArguments;
import org.ektorp.*;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Jakub Werner on 01/02/14.
 */
public class SaveGameCouchDbDAO implements ISaveGameDAO {

    private static final Logger log = Logger.getLogger(SaveGameCouchDbDAO.class.getName());

    private CouchDbConnector db = null;
    @Inject
    private IController  controller;


    public SaveGameCouchDbDAO() {
        openDB();
    }

    @Override
    public final void openDB() {
        HttpClient client = null;

        try {
            client = new StdHttpClient.Builder().url("http://lenny2.in.htwg-konstanz.de:5984").build();
        } catch (MalformedURLException e) {
            log.info(e.getMessage());
        }

        CouchDbInstance dbInstance = new StdCouchDbInstance(client);
        db = dbInstance.createConnector("connect4_db", true);
        db.createDatabaseIfNotExists();
    }

    @Override
    public void saveGame(SaveGame saveGame) {
        if (saveGameExists(saveGame.getSaveGameName())) {
            db.update(CouchDbUtil.convertSaveGame(saveGame));
        } else {
            db.create(CouchDbUtil.convertSaveGame(saveGame));
        }
    }

    @Override
    public SaveGame loadSaveGame(String saveGameName) {
        ViewQuery viewQuery = new ViewQuery().designDocId("_design/by_name").viewName("by_name").key(saveGameName).includeDocs(true);
        List<SaveGameCouchDb> rows = db.queryView(viewQuery, SaveGameCouchDb.class);
        if (!rows.isEmpty()) {
           SaveGameCouchDb p = rows.get(0);
           return CouchDbUtil.convertSaveGame(p, (IObserverWithArguments) controller);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean deleteSaveGameIfExists(String saveGameName) {
        SaveGameCouchDb sg = db.get(SaveGameCouchDb.class, saveGameName);
        if (sg != null) {
            db.delete(sg);
            return true;
        }
        return false;
    }

    @Override
    public void closeDB() {
    }

    @Override
    public boolean saveGameExists(String saveGameName) {
        try {
            return db.get(SaveGameCouchDb.class, saveGameName) != null;
        } catch (DocumentNotFoundException e) {
            System.out.printf("Docment with %s not found\n", saveGameName);
        }

        return false;
    }

    @Override
    public List<String> getAllSaveGames() {
        List<String>  saveGames = new ArrayList<String>();
        ViewQuery viewQuery = new ViewQuery().designDocId("_design/by_name").viewName("by_name").includeDocs(true);
        List<SaveGameCouchDb> rows = db.queryView(viewQuery, SaveGameCouchDb.class);
        for (SaveGameCouchDb s : rows) {
            saveGames.add(s.getSaveGameName());

        }
        return saveGames;
    }
}
