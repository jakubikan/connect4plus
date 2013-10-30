/**
 * 
 */
package connect4.ui.tui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;

import connectfour.controller.IController;
import connectfour.model.Human;
import connectfour.model.Player;
import connectfour.ui.tui.TUI;

/**
 * @author Stefano Di Martino
 */
public class TUITest {

	Player player1;
	Player player2;
	
	@Inject
	IController controller;
	
	@Before
	public void setUp() throws Exception {
		// For this test player2 should begin.
		player1 = new Human();
		player2 = new Human();
		this.controller.newGame();
		this.controller.setPlayer((Human) player1);
		this.controller.setOpponend(player2);
	}

	@Test
	public void dropCointTest1() {
		boolean success = true;
		success &= this.controller.dropCoinWithSuccessFeedback(3);
		success &= this.controller.dropCoinWithSuccessFeedback(3);
		success &= this.controller.dropCoinWithSuccessFeedback(1);
		success &= this.controller.dropCoinWithSuccessFeedback(1);
		success &= this.controller.dropCoinWithSuccessFeedback(1);
		success &= this.controller.dropCoinWithSuccessFeedback(1);
		success &= this.controller.dropCoinWithSuccessFeedback(1);
		success &= this.controller.dropCoinWithSuccessFeedback(1);

		assertTrue(success);

		String newline = "\n";

		String strCoinTest1 = "|___|_O_|___|___|___|___|___|"
				+ newline + "|___|_X_|___|___|___|___|___|" + newline
				+ "|___|_O_|___|___|___|___|___|" + newline
				+ "|___|_X_|___|___|___|___|___|" + newline
				+ "|___|_O_|___|_O_|___|___|___|" + newline
				+ "|___|_X_|___|_X_|___|___|___|" + newline;

		assertEquals(strCoinTest1, new TUI(this.controller).renderGameField());
	}
}
