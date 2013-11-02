package persistence;

import connectfour.model.SaveGame;

public interface ISaveGameDAO {
	void saveGame(SaveGame saveGame);
	SaveGame loadSaveGame(String saveGameName);
	void closeDB();
	boolean saveGameExists(String saveGameName);
}
