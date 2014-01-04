package connectfour.persistence.db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectContainer;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.query.Predicate;
import com.google.inject.Singleton;
import connectfour.model.SaveGame;
import connectfour.persistence.ISaveGameDAO;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Singleton
public class SaveGameDb4oDAO implements ISaveGameDAO {
    private static final Logger log = Logger.getLogger(SaveGameDb4oDAO.class.getName());

	private ObjectContainer db;

    /**
     * Opens the database by default.
     */
	public SaveGameDb4oDAO() {
    }

    @Override
    public final void openDB() {
        try {
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"savegame.data");
        } catch (DatabaseFileLockedException e) {
            log.info(e.getMessage());
            throw new DatabaseFileLockedException("Database Locked");

        }

    }

    @Override
	public void saveGame(SaveGame saveGame) {
		// Player's gamefield shouldn't be saved!
		saveGame.getPlayer1().setGameField(null);
		saveGame.getPlayer2().setGameField(null);


		if (deleteSaveGameIfExists(saveGame.getSaveGameName())) {
            log.info("Game correctly deleted");
        } else {
            log.info("Savegame did not exist.");
        }


        openDB();
		db.store(saveGame);
        closeDB();
	}

	@Override
	public boolean deleteSaveGameIfExists(final String saveGameName) {
        openDB();
        List<SaveGame> listISaveGame = db.query(new Predicate<SaveGame>() {
            private static final long serialVersionUID = 1L;

            public boolean match(SaveGame sg) {
                return sg.getSaveGameName().equals(saveGameName);
            }
        });

        SaveGame saveGame = null;
        if (listISaveGame.size() > 0) {
            saveGame = listISaveGame.get(0);
        }
        if (saveGame != null) {
            try {
                db.delete(saveGame);
                db.commit();
            } catch (Exception e) {
                log.info(e.getMessage());
            } finally {
                closeDB();
            }
			return true;
		}
        closeDB();
		return false;
	}
	
	@Override
	public boolean saveGameExists(final String saveGameName) {
		return loadSaveGame(saveGameName) != null;
	}

	@Override
	public SaveGame loadSaveGame(final String saveGameName) {
        openDB();
		List<SaveGame> listISaveGame = db.query(new Predicate<SaveGame>() {
			private static final long serialVersionUID = 1L;

			public boolean match(SaveGame sg) {
				return sg.getSaveGameName().equals(saveGameName);
			}
		});

        SaveGame saveGame = null;
		if (listISaveGame.size() > 0) {
			saveGame = listISaveGame.get(0);
		}
        closeDB();

		return saveGame;
	}

	@Override
	public List<String> getAllSaveGames() {
        openDB();
		Iterator<SaveGame> it = db.query(SaveGame.class).iterator();
		List<String> allSaveGames = new LinkedList<String>();
		
		while(it.hasNext()) {
			SaveGame sg = it.next();
			allSaveGames.add(sg.getSaveGameName());
		}

        closeDB();
		return allSaveGames;
	}
	
	@Override
	public void closeDB() {
        try {
		db.close();
        } catch (Exception e) {
            log.info("Something went went while closing");

        }
	}
}