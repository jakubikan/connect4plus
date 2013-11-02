package persistence.db4o;

import persistence.ISaveGameDAO;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import connectfour.model.SaveGame;

public class SaveGameDb4oDAO implements ISaveGameDAO {
	private ObjectContainer db;

	public SaveGameDb4oDAO() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "savegame.data");
	}

	@Override
	public void saveGame(SaveGame saveGame) {
		// Local gamefield for evaluating gamestrategy shouldn't be saved!
		saveGame.getPlayer1().setGameField(null);
		saveGame.getPlayer2().setGameField(null);
		db.store(saveGame);
	}

	@Override
	public SaveGame loadSaveGame() {
		return db.query(SaveGame.class).next();
	}
	
	@Override
	public void closeDB() {
		db.close();
	}
}