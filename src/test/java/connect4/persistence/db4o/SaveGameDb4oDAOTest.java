package connect4.persistence.db4o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.ISaveGameDAO;
import persistence.db4o.SaveGameDb4oDAO;

import com.google.inject.Guice;
import com.google.inject.Injector;

import connectfour.GameControllerModule;
import connectfour.controller.GameController;
import connectfour.model.GameField;
import connectfour.model.Human;
import connectfour.model.Player;
import connectfour.model.SaveGame;
import connectfour.util.observer.IObserverWithArguments;

public class SaveGameDb4oDAOTest {
	private GameField gameField;
	private Player player;
	private Player opponend;
	private IObserverWithArguments obsersable;
	private ISaveGameDAO sgdb4o;
	private List<Integer> rows;

	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new GameControllerModule());
		this.obsersable = injector.getInstance(GameController.class);

		player = new Human();
		player.setName("Hugo");
		opponend = new Human();
		gameField = new GameField(obsersable);
		rows = new LinkedList<Integer>();
	}

	@Test
	public void allTests() {
		initGameField();
		saveGameAlreadyExistsTest();
		loadTest();
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

		row = gameField.dropCoin(1, opponend);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		assertEquals(0, row);
		rows.add(row);

		row = gameField.dropCoin(1, opponend);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		assertEquals(1, row);
		rows.add(row);

		row = gameField.dropCoin(1, opponend);
		assertEquals(2, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		rows.add(row);

		row = gameField.dropCoin(1, opponend);
		assertEquals(3, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		rows.add(row);

		row = gameField.dropCoin(1, opponend);
		assertEquals(4, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		rows.add(row);

		row = gameField.dropCoin(1, opponend);
		assertEquals(5, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		assertEquals(gameField.getPlayerAt(1, 1), opponend);
		rows.add(row);

	}

	private void saveGameAlreadyExistsTest() {
		// Save gameField and players
		String sgName = "Unit_Test";
		SaveGame sg = new SaveGame(sgName, gameField, player, opponend);

		sgdb4o = new SaveGameDb4oDAO();
		sgdb4o.saveGame(sg);

		// Test if the saveGame exists
		assertTrue(sgdb4o.saveGameExists(sgName));
		
		assertFalse(sgdb4o.saveGameExists("DummyName"));
	}

	private void loadTest() {
		// Save gameField and players
				String sgName = new Date().toString(); // Every run should have different name
				SaveGame sg = new SaveGame(sgName, gameField, player, opponend);

				// Load gameField and players
				sg = sgdb4o.loadSaveGame();

				gameField = sg.getGameField();
				player = sg.getPlayer1();
				opponend = sg.getPlayer2();

				// Check, if the state has been loaded correctly!
				Iterator<Integer> it = rows.iterator();

				int row = it.next();
				assertEquals(0, row);
				assertEquals(gameField.getPlayerAt(row, 3), player);

				row = it.next();
				assertEquals(gameField.getPlayerAt(row, 3), player);
				assertEquals(1, row);

				row = it.next();
				assertEquals(gameField.getPlayerAt(row, 1), opponend);
				assertEquals(0, row);

				row = it.next();
				assertEquals(gameField.getPlayerAt(row, 1), opponend);
				assertEquals(1, row);

				row = it.next();
				assertEquals(2, row);
				assertEquals(gameField.getPlayerAt(row, 1), opponend);

				row = it.next();
				assertEquals(3, row);
				assertEquals(gameField.getPlayerAt(row, 1), opponend);

				row = it.next();
				assertEquals(4, row);
				assertEquals(gameField.getPlayerAt(row, 1), opponend);

				row = it.next();
				assertEquals(5, row);
				assertEquals(gameField.getPlayerAt(row, 1), opponend);
				assertEquals(gameField.getPlayerAt(1, 1), opponend);
	}

	@After
	public void tearDown() throws Exception {
		sgdb4o.closeDB();
	}
}