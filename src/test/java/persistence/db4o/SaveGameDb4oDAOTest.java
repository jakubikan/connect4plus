package persistence.db4o;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import connectfour.GameControllerModule;
import connectfour.controller.GameController;
import connectfour.controller.IController;
import connectfour.model.Human;
import connectfour.model.Player;

public class SaveGameDb4oDAOTest {
	private Player player1;
	private Player player2;

	private IController controller;

	@Before
	public void setUp() throws Exception {
		// For this test player2 should begin.
		Injector injector = Guice.createInjector(new GameControllerModule());
		this.controller = injector.getInstance(GameController.class);

		player1 = new Human();
		player2 = new Human();

		this.controller.newGame();
		this.controller.setPlayer(player1);
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
	}
}
