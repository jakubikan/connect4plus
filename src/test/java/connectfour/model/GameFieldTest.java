package connectfour.model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import connectfour.GameControllerModule;
import connectfour.controller.GameController;
import connectfour.util.observer.IObserverWithArguments;

public class GameFieldTest {
	GameField gameField;
	Player player;
	Player opponent;

	private IObserverWithArguments obsersable;

	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new GameControllerModule());
		this.obsersable = injector.getInstance(GameController.class);

		player = new Human("Hugo");
		opponent = new Human("Boss");
		gameField = new GameField(player, opponent);
	}

	@After
	public void tearDown() throws Exception {
		gameField = new GameField(player, opponent);
	}

	@Test
	public void dropCointTest() {
		int row;

		row = gameField.dropCoin(3, player);
		assertEquals(0, row);
		assertEquals(gameField.getPlayerAt(row, 3), player);
		row = gameField.dropCoin(3, player);
		assertEquals(gameField.getPlayerAt(row, 3), player);
		assertEquals(1, row);
		row = gameField.dropCoin(1, opponent);
		assertEquals(gameField.getPlayerAt(row, 1), opponent);
		assertEquals(0, row);
		row = gameField.dropCoin(1, opponent);
		assertEquals(gameField.getPlayerAt(row, 1), opponent);
		assertEquals(1, row);
		row = gameField.dropCoin(1, opponent);
		assertEquals(2, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponent);
		row = gameField.dropCoin(1, opponent);
		assertEquals(3, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponent);
		row = gameField.dropCoin(1, opponent);
		assertEquals(4, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponent);
		row = gameField.dropCoin(1, opponent);
		assertEquals(5, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponent);
		assertEquals(gameField.getPlayerAt(1, 1), opponent);
	}

	@Test
	public void newGameFieldTest() {
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);
		gameField = new GameField(player, opponent);
		assertEquals(gameField.getPlayerAt(0, 0), null);
	}

	@Test
	public void isWonTest() {
		System.out.println("isWonTest");
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);

		assertEquals(gameField.getWinner(), opponent);

		gameField = new GameField(player, opponent);

		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, player);
		gameField.dropCoin(0, player);
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(0, opponent);
		assertEquals(gameField.getWinner(), null);

		gameField = new GameField(player, opponent);

		/*
		 * o ox oxx oxxxoo
		 */
		gameField.dropCoin(0, opponent);
		gameField.dropCoin(1, player);
		gameField.dropCoin(1, opponent);
		gameField.dropCoin(2, player);
		gameField.dropCoin(3, opponent);
		gameField.dropCoin(2, player);
		gameField.dropCoin(2, opponent);
		gameField.dropCoin(3, player);
		gameField.dropCoin(4, opponent);
		gameField.dropCoin(3, player);
		gameField.dropCoin(3, opponent);
		assertEquals(gameField.getWinner(), opponent);

		gameField = new GameField(player, opponent);

		/*
		 * x ox xox xoxox
		 */
		gameField.dropCoin(6, player);
		gameField.dropCoin(5, opponent);
		gameField.dropCoin(4, player);
		gameField.dropCoin(3, opponent);
		gameField.dropCoin(5, player);
		gameField.dropCoin(4, opponent);
		gameField.dropCoin(3, player);
		gameField.dropCoin(3, opponent);
		gameField.dropCoin(4, player);
		gameField.dropCoin(2, opponent);
		gameField.dropCoin(3, player);
		assertEquals(gameField.getWinner(), player);

	}
}