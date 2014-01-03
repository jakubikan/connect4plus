package connectfour.persistence.hibernate;

import connectfour.controller.GameController;
import connectfour.model.GameField;
import connectfour.model.Human;
import connectfour.model.Player;
import connectfour.model.SaveGame;
import connectfour.persistence.ISaveGameDAO;
import connectfour.util.observer.IObserverWithArguments;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * User: Stefano Di Martino
 * Date: 12.11.13
 * Time: 16:07
 */
@Ignore
public class HibernateUtilTest {
    private GameField gameField;
    private Player player;
    private Player opponent;
    private IObserverWithArguments observable;
    private ISaveGameDAO db;
    private java.util.List<Integer> rows;

    @Before
    public void setUp() throws Exception {
        this.observable = new GameController();

        player = new Human("Hugo");
        opponent = new Human("Boss");
        gameField = new GameField(player, opponent);
        rows = new LinkedList<Integer>();
        db = new SaveGameDbHibernate();
    }

    @Test
    public void allTests() {
        initGameField();
        saveGameAlreadyExistsTest();
        loadTest();
        saveGameWillBeOverwrittenTest();
    }

    private void initGameField() {
        int row;

        // Evaluate for correctness and later save and load the game again and
        // check again for correctness
        row = gameField.dropCoin(3, player);
        assertEquals(0, row);
        assertEquals(gameField.getPlayerAt(row, 3), player);
        rows.add(row);

        row = gameField.dropCoin(3, player);
        assertEquals(gameField.getPlayerAt(row, 3), player);
        assertEquals(1, row);
        rows.add(row);

        row = gameField.dropCoin(1, opponent);
        assertEquals(gameField.getPlayerAt(row, 1), opponent);
        assertEquals(0, row);
        rows.add(row);

        row = gameField.dropCoin(1, opponent);
        assertEquals(gameField.getPlayerAt(row, 1), opponent);
        assertEquals(1, row);
        rows.add(row);

        row = gameField.dropCoin(1, opponent);
        assertEquals(2, row);
        assertEquals(gameField.getPlayerAt(row, 1), opponent);
        rows.add(row);

        row = gameField.dropCoin(1, opponent);
        assertEquals(3, row);
        assertEquals(gameField.getPlayerAt(row, 1), opponent);
        rows.add(row);

        row = gameField.dropCoin(1, opponent);
        assertEquals(4, row);
        assertEquals(gameField.getPlayerAt(row, 1), opponent);
        rows.add(row);

        row = gameField.dropCoin(1, opponent);
        assertEquals(5, row);
        assertEquals(gameField.getPlayerAt(row, 1), opponent);
        assertEquals(gameField.getPlayerAt(1, 1), opponent);
        rows.add(row);
    }

    private void saveGameAlreadyExistsTest() {
        // Save gameField and players
        String sgName = "Unit_Test";
        SaveGame sg = new SaveGame(sgName, gameField, player, opponent);

        db.saveGame(sg);

        // Test if the saveGame exists
        assertTrue(db.saveGameExists(sgName));

        assertFalse(db.saveGameExists("DummyName"));
    }

    private int countSaveGameName(String sgName) {
        Iterator<String> it = db.getAllSaveGames().iterator();
        int i = 0;

        while(it.hasNext()) {
            String name = it.next();
            if (name.equals(sgName)) {
                i++;
            }
        }

        return i;
    }

    private String getUniqueName() {
        return Long.toString(System.nanoTime());
    }

    private void saveGameWillBeOverwrittenTest() {
        // Save gameField and players
        String sgName = getUniqueName();
        SaveGame sg = new SaveGame(sgName, gameField, player, opponent);

        // Before save
        assertEquals(countSaveGameName(sgName), 0);

        db.saveGame(sg);

        // After save
        assertEquals(countSaveGameName(sgName), 1);

        // After second save, the save game with the same name
        // should be overwritten. So countSaveGame() should still
        // return 1!

        db.saveGame(sg);
        assertEquals(countSaveGameName(sgName), 1);
    }

    private void loadTest() {
        // Save gameField and players
        String sgName = getUniqueName(); // Every run should have different name
        SaveGame sg = new SaveGame(sgName, gameField, player, opponent);

        db.saveGame(sg);

        // Load gameField and players
        sg = db.loadSaveGame(sgName);

        gameField = sg.getGameField();
        player = sg.getPlayer1();
        opponent = sg.getPlayer2();

        // Check, if the state has been loaded correctly!
        Iterator<Integer> it = rows.iterator();

        int row = it.next();
        assertEquals(0, row);
        assertEquals(gameField.getPlayerAt(row, 3), player);

        row = it.next();
        assertEquals(gameField.getPlayerAt(row, 3), player);
        assertEquals(1, row);

        row = it.next();
        assertEquals(gameField.getPlayerAt(row, 1), opponent);
        assertEquals(0, row);

        row = it.next();
        assertEquals(gameField.getPlayerAt(row, 1), opponent);
        assertEquals(1, row);

        row = it.next();
        assertEquals(2, row);
        assertEquals(gameField.getPlayerAt(row, 1), opponent);

        row = it.next();
        assertEquals(3, row);
        assertEquals(gameField.getPlayerAt(row, 1), opponent);

        row = it.next();
        assertEquals(4, row);
        assertEquals(gameField.getPlayerAt(row, 1), opponent);

        row = it.next();
        assertEquals(5, row);
        assertEquals(gameField.getPlayerAt(row, 1), opponent);
        assertEquals(gameField.getPlayerAt(1, 1), opponent);
    }

    @After
    public void tearDown() throws Exception {
        db.closeDB();
    }
}
