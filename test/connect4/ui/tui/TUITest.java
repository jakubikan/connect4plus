/**
 * 
 */
package connect4.ui.tui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import connectfour.controller.GameController;
import connectfour.model.Player;
import connectfour.ui.tui.TUI;

/**
 * @author Stefano Di Martino
 */
public class TUITest {

	Player player1;
	Player player2;

	@Before
	public void setUp() throws Exception {
		// For this test player2 should begin.
		do {
			GameController.getInstance().newGame();

			player1 = GameController.getInstance().getPlayers()[0];
			player2 = GameController.getInstance().getPlayers()[1];
		} while (GameController.getInstance().getPlayerOnTurn() != player2);
	}

	@Test
	public void test() {
		dropCointTest1();
		String newline = "\n";

		String strCoinTest1 = "|___|_O_|___|___|___|___|___|"
				+ newline + "|___|_X_|___|___|___|___|___|" + newline
				+ "|___|_O_|___|___|___|___|___|" + newline
				+ "|___|_X_|___|___|___|___|___|" + newline
				+ "|___|_O_|___|_O_|___|___|___|" + newline
				+ "|___|_X_|___|_X_|___|___|___|" + newline;

		assertEquals(strCoinTest1, new TUI().renderGameField());
	}

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
	}
}
