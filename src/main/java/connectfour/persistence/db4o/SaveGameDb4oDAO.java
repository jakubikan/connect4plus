package connectfour.persistence.db4o;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import connectfour.model.SaveGame;
import connectfour.persistence.ISaveGameDAO;

public class SaveGameDb4oDAO implements ISaveGameDAO {
	private ObjectContainer db;

	public SaveGameDb4oDAO() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"savegame.data");
	}

	@Override
	public void openDB() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"savegame.data");
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
		if (saveGameExists(saveGameName)) {
			SaveGame sg = loadSaveGame(saveGameName);
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
		List<SaveGame> listSaveGame = db.query(new Predicate<SaveGame>() {
			private static final long serialVersionUID = 1L;

			public boolean match(SaveGame sg) {
				return sg.getSaveGameName().equals(saveGameName);
			}
		});

		if (listSaveGame.size() > 0) {
			return listSaveGame.get(0);
		}
		return null;
	}

	@Override
	public List<String> getAllSaveGames() {
		Iterator<SaveGame> it = db.query(SaveGame.class).iterator();
		List<String> allSaveGames = new LinkedList<>();
		
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