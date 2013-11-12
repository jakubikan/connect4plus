package connectfour.persistence;

import connectfour.model.SaveGame;

import java.util.List;

public interface ISaveGameDAO {
    void openDB();

	/**
	 * @param saveGame If save game name already exists, it will be overwritten.
	 */
	void saveGame(SaveGame saveGame);
	
	SaveGame loadSaveGame(String saveGameName);
	
	/**
	 * @param saveGameName save game name to be deleted
	 * @return true, if deletion was successful, false otherwise
	 */
	boolean deleteSaveGameIfExists(String saveGameName);

	void closeDB();
	boolean saveGameExists(String saveGameName);
	List<String> getAllSaveGames();
}
