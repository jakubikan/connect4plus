package connectfour.persistence.db4o;

import com.google.inject.Guice;
import com.google.inject.Injector;
import connectfour.GameControllerModule;
import connectfour.controller.GameController;
import connectfour.model.GameField;
import connectfour.model.Human;
import connectfour.model.Player;
import connectfour.model.SaveGame;
import connectfour.persistence.ISaveGameDAO;
import connectfour.util.observer.IObserverWithArguments;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class SaveGameDb4oDAOTest {
    private GameField gameField;
    private Player player;
    private Player opponent;
    private IObserverWithArguments observable;
    private ISaveGameDAO sgdb4o;
    private List<Integer> rows;

    @Before
    public void setUp() throws Exception {
        //Injector injector = Guice.createInjector(new GameControllerModule());
        //this.observable = injector.getInstance(GameController.class);
        this.observable = new GameController();

        player = new Human();
        player.setName("Hugo");
        opponent = new Human();
        gameField = new GameField(player, opponent);
        rows = new LinkedList<>();
        sgdb4o = new SaveGameDb4oDAO();
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

        sgdb4o.saveGame(sg);

        // Test if the saveGame exists
        assertTrue(sgdb4o.saveGameExists(sgName));

        assertFalse(sgdb4o.saveGameExists("DummyName"));
    }

    private int countSaveGameName(String sgName) {
        Iterator<String> it = sgdb4o.getAllSaveGames().iterator();
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
        assertTrue(countSaveGameName(sgName) == 0);

        sgdb4o.saveGame(sg);

        // After save
        assertTrue(countSaveGameName(sgName) == 1);

        // After second save, the save game with the same name
        // should be overwritten. So countSaveGame() should still
        // return 1!

        sgdb4o.saveGame(sg);
        assertTrue(countSaveGameName(sgName) == 1);
    }

    private void loadTest() {
        // Save gameField and players
        String sgName = getUniqueName(); // Every run should have different name
        SaveGame sg = new SaveGame(sgName, gameField, player, opponent);

        sgdb4o.saveGame(sg);

        // Load gameField and players
        sg = sgdb4o.loadSaveGame(sgName);

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
        sgdb4o.closeDB();
    }
}