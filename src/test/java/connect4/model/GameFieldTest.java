package connect4.model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import connectfour.GameControllerModule;
import connectfour.controller.GameController;
import connectfour.controller.GameField;
import connectfour.model.Human;
import connectfour.model.Player;
import connectfour.util.observer.IObserverWithArguments;

public class GameFieldTest {
	GameField gameField;
	Player player;
	Player opponend;

	private IObserverWithArguments obsersable;

	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new GameControllerModule());
		this.obsersable = injector.getInstance(GameController.class);

		player = new Human();
		player.setName("Hugo");
		opponend = new Human();
		gameField = new GameField(player, opponend);
	}

	@After
	public void tearDown() throws Exception {
		gameField = new GameField(player, opponend);
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
		row = gameField.dropCoin(1, opponend);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		assertEquals(0, row);
		row = gameField.dropCoin(1, opponend);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		assertEquals(1, row);
		row = gameField.dropCoin(1, opponend);
		assertEquals(2, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		row = gameField.dropCoin(1, opponend);
		assertEquals(3, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		row = gameField.dropCoin(1, opponend);
		assertEquals(4, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		row = gameField.dropCoin(1, opponend);
		assertEquals(5, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		assertEquals(gameField.getPlayerAt(1, 1), opponend);
	}

	@Test
	public void newGameFieldTest() {
		gameField.dropCoin(0, opponend);
		gameField.dropCoin(0, opponend);
		gameField.dropCoin(0, opponend);
		gameField.dropCoin(0, opponend);
		gameField = new GameField(player, opponend);
		assertEquals(gameField.getPlayerAt(0, 0), null);
	}

	@Test
	public void isWonTest() {
		System.out.println("isWonTest");
		gameField.dropCoin(0, opponend);
		gameField.dropCoin(0, opponend);
		gameField.dropCoin(0, opponend);
		gameField.dropCoin(0, opponend);

		assertEquals(gameField.getWinner(), opponend);

		gameField = new GameField(player, opponend);

		gameField.dropCoin(0, opponend);
		gameField.dropCoin(0, opponend);
		gameField.dropCoin(0, player);
		gameField.dropCoin(0, player);
		gameField.dropCoin(0, opponend);
		gameField.dropCoin(0, opponend);
		assertEquals(gameField.getWinner(), null);

		gameField = new GameField(player, opponend);

		/*
		 * o ox oxx oxxxoo
		 */
		gameField.dropCoin(0, opponend);
		gameField.dropCoin(1, player);
		gameField.dropCoin(1, opponend);
		gameField.dropCoin(2, player);
		gameField.dropCoin(3, opponend);
		gameField.dropCoin(2, player);
		gameField.dropCoin(2, opponend);
		gameField.dropCoin(3, player);
		gameField.dropCoin(4, opponend);
		gameField.dropCoin(3, player);
		gameField.dropCoin(3, opponend);
		assertEquals(gameField.getWinner(), opponend);

		gameField = new GameField(player, opponend);

		/*
		 * x ox xox xoxox
		 */
		gameField.dropCoin(6, player);
		gameField.dropCoin(5, opponend);
		gameField.dropCoin(4, player);
		gameField.dropCoin(3, opponend);
		gameField.dropCoin(5, player);
		gameField.dropCoin(4, opponend);
		gameField.dropCoin(3, player);
		gameField.dropCoin(3, opponend);
		gameField.dropCoin(4, player);
		gameField.dropCoin(2, opponend);
		gameField.dropCoin(3, player);
		assertEquals(gameField.getWinner(), player);

	}
}