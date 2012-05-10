package connect4.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import connect4.controller.GameController;
import connect4.model.Player;

public class GameControllerTest {
	private Player player1;
	private Player player2;
	private int row;

	@Before
	public void setUp() throws Exception {
		GameController.getInstance().newGame();
		player1 = GameController.getInstance().getPlayers()[0];
		player2 = GameController.getInstance().getPlayers()[1];
		row = GameController.getInstance().dropCoin(3, player1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(player1 != null);
		assertTrue(player2 != null);
		assertTrue(GameController.getInstance().getUI() != null);
		assertEquals(0, row);
		assertTrue(GameController.getInstance().getPlayerAt(0, 3) == player1);
	}

}
