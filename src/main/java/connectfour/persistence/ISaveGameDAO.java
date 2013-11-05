package connectfour.persistence;

import java.util.List;

import connectfour.model.SaveGame;

public interface ISaveGameDAO {
	
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
	
	void openDB();
	void closeDB();
	boolean saveGameExists(String saveGameName);
	List<String> getAllSaveGames();
}
