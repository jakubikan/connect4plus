package connectfour.persistence.hibernate;

import connectfour.model.SaveGame;
import connectfour.persistence.ISaveGameDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Stefano Di Martino
 * Date: 12.11.13
 * Time: 17:13
 */
public class SaveGameDbHibernate implements ISaveGameDAO {
    private SessionFactory sessionFactory;
    private Session session;

    /**
     * Opens the database by default.
     */
    public void SaveGameDbHibernate() {
        this.openDB();
    }

    @Override
    public void openDB() {
        this.sessionFactory = HibernateUtil.getInstance();
        this.session = this.sessionFactory.openSession();
    }

    private SaveGameHibernate convertToHibernateSaveGame(SaveGame saveGame) {
        SaveGameHibernate sgh = new SaveGameHibernate(
                        saveGame.getSaveGameName(),
                        saveGame.getGameField(),
                        saveGame.getPlayer1(),
                        saveGame.getPlayer2());

        return sgh;
    }

    private SaveGame convertToStandardSaveGame(SaveGameHibernate saveGame) {
        SaveGame sg = new SaveGame(
                saveGame.getSaveGameName(),
                saveGame.getGameField(),
                saveGame.getPlayer1(),
                saveGame.getPlayer2());

        return sg;
    }

    @Override
    public void saveGame(SaveGame saveGame) {
        SaveGameHibernate sgh = convertToHibernateSaveGame(saveGame);

        session.beginTransaction();
        session.save(sgh);
        session.getTransaction().commit();
    }

    @Override
    public SaveGame loadSaveGame(String saveGameName) {
        List<SaveGameHibernate> saveGameNames = session.createCriteria(SaveGameHibernate.class)
                .add(Restrictions.like("saveGameName", saveGameName))
                .list();

        if (saveGameNames.size() > 0) {
            return convertToStandardSaveGame(saveGameNames.get(0));
        }

        return null;
    }

    @Override
    public boolean deleteSaveGameIfExists(String saveGameName) {
        if (saveGameExists(saveGameName)) {
            SaveGame sg = loadSaveGame(saveGameName);
            this.session.delete(convertToHibernateSaveGame(sg));

            return true;
        }
        return false;
    }

    @Override
    public void closeDB() {
        this.session.close();
    }

    @Override
    public boolean saveGameExists(String saveGameName) {
        return loadSaveGame(saveGameName) != null;
    }

    @Override
    public List<String> getAllSaveGames() {
        Iterator<SaveGameHibernate> it = session.createCriteria(SaveGameHibernate.class).list().iterator();
        List<String> allSaveGames = new LinkedList<>();

        while(it.hasNext()) {
            SaveGame sg = it.next();
            allSaveGames.add(sg.getSaveGameName());
        }

        return allSaveGames;
    }
}