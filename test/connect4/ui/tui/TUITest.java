/**
 * 
 */
package connect4.ui.tui;

import connectfour.controller.GameController;
import connectfour.model.Human;
import connectfour.model.Player;
import connectfour.ui.tui.TUI;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Stefano Di Martino
 */
public class TUITest {

	Player player1;
	Player player2;

	@Before
	public void setUp() throws Exception {
		// For this test player2 should begin.
		player1 = new Human();
		player2 = new Human();
		GameController.getInstance().newGame();
		GameController.getInstance().setPlayer((Human) player1);
		GameController.getInstance().setOpponend(player2);
	}

	@Test
	public void dropCointTest1() {
		boolean success = true;
		success &= GameController.getInstance()
				.dropCoinWithSuccessFeedback(3);
		success &= GameController.getInstance()
				.dropCoinWithSuccessFeedback(3);
		success &= GameController.getInstance()
				.dropCoinWithSuccessFeedback(1);
		success &= GameController.getInstance()
				.dropCoinWithSuccessFeedback(1);
		success &= GameController.getInstance()
				.dropCoinWithSuccessFeedback(1);
		success &= GameController.getInstance()
				.dropCoinWithSuccessFeedback(1);
		success &= GameController.getInstance()
				.dropCoinWithSuccessFeedback(1);
		success &= GameController.getInstance()
				.dropCoinWithSuccessFeedback(1);

		assertTrue(success);

		String newline = "\n";

		String strCoinTest1 = "|___|_O_|___|___|___|___|___|"
				+ newline + "|___|_X_|___|___|___|___|___|" + newline
				+ "|___|_O_|___|___|___|___|___|" + newline
				+ "|___|_X_|___|___|___|___|___|" + newline
				+ "|___|_O_|___|_O_|___|___|___|" + newline
				+ "|___|_X_|___|_X_|___|___|___|" + newline;

		assertEquals(strCoinTest1, new TUI().renderGameField());
	}
}
