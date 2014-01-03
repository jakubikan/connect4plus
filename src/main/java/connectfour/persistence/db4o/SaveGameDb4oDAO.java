package connectfour.persistence.db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.query.Predicate;
import com.google.inject.Singleton;
import connectfour.model.SaveGame;
import connectfour.persistence.ISaveGameDAO;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class SaveGameDb4oDAO implements ISaveGameDAO {
	private ObjectContainer db;

    /**
     * Opens the database by default.
     */
	public SaveGameDb4oDAO() {
            openDB();
    }

    @Override
    public void openDB() {
        try {
            db = Db4oEmbedded.openFile("savegame.data");
        } catch (DatabaseFileLockedException e) {
            throw new DatabaseFileLockedException("Database Locked");

        }

    }

    @Override
	public void saveGame(SaveGame saveGame) {
		// Player's gamefield shouldn't be saved!
		saveGame.getPlayer1().setGameField(null);
		saveGame.getPlayer2().setGameField(null);

		deleteSaveGameIfExists(saveGame.getSaveGameName());

		db.store(saveGame);
	}

	@Override
	public boolean deleteSaveGameIfExists(final String saveGameName) {
        SaveGame sg = loadSaveGame(saveGameName);
        if (sg != null) {
			db.delete(sg);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean saveGameExists(final String saveGameName) {
		return loadSaveGame(saveGameName) != null;
	}

	@Override
	public SaveGame loadSaveGame(final String saveGameName) {
		List<SaveGame> listISaveGame = db.query(new Predicate<SaveGame>() {
			private static final long serialVersionUID = 1L;

			public boolean match(SaveGame sg) {
				return sg.getSaveGameName().equals(saveGameName);
			}
		});

		if (listISaveGame.size() > 0) {
			return listISaveGame.get(0);
		}
		return null;
	}

	@Override
	public List<String> getAllSaveGames() {
		Iterator<SaveGame> it = db.query(SaveGame.class).iterator();
		List<String> allSaveGames = new LinkedList<String>();
		
		while(it.hasNext()) {
			SaveGame sg = it.next();
			allSaveGames.add(sg.getSaveGameName());
		}
		
		return allSaveGames;
	}
	
	@Override
	public void closeDB() {
		db.close();
	}
}