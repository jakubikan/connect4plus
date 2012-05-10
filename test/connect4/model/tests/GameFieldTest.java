package connect4.model.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import connect4.model.Coin;
import connect4.model.GameField;
import connect4.model.Human;
import connect4.model.Player;

public class GameFieldTest {
	
	GameField gameField;
	Player player;
	Player opponend;
	Coin playerCoin;
	Coin opponendCoin;
	
	

	@Before
	public void setUp() throws Exception {
		playerCoin = new Coin(Color.YELLOW);
		opponendCoin = new Coin(Color.YELLOW);
		player = new Human(playerCoin);
		player.setName("Hugo");
		opponend = new Human(opponendCoin);
		gameField = player.getGameField();
	}

	@After
	public void tearDown() throws Exception {
	}

	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void dropCointTest() {
		int row;
		
		row = player.dropCoin(3);
		assertEquals(0, row);
		assertEquals(gameField.getPlayerAt(row, 3), player);
		row = player.dropCoin(3);
		assertEquals(gameField.getPlayerAt(row, 3), player);
		assertEquals(1, row);
		row = opponend.dropCoin(1);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		assertEquals(0, row);
		row = opponend.dropCoin(1);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		assertEquals(1, row);
		row = opponend.dropCoin(1);
		assertEquals(2, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		row = opponend.dropCoin(1);
		assertEquals(3, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		row = opponend.dropCoin(1);
		assertEquals(4, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		row = opponend.dropCoin(1);
		assertEquals(5, row);
		assertEquals(gameField.getPlayerAt(row, 1), opponend);
		assertEquals(gameField.getPlayerAt(1, 1), opponend);
	}
	
	public void newGameTest() {
		
		int row;
		row = opponend.dropCoin(0);
		row = opponend.dropCoin(0);
		row = opponend.dropCoin(0);
		row = opponend.dropCoin(0);
		gameField.newGame();
		assertEquals(gameField.getPlayerAt(0, 0), null);
	}

}
