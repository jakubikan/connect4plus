package connectfour.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import connectfour.GameControllerModule;
import connectfour.model.Human;
import connectfour.model.Player;

public class GameControllerTest {
	private final Human player1 = new Human();
	private final Player player2 = new Human();
	
	private IController gc;
	
	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new GameControllerModule());
    	gc = injector.getInstance(GameController.class);
    	
		gc.newGame();
		gc.setOpponend(player1);
		gc.setOpponend(player2);
	}

	@After
	public void tearDown() throws Exception {
		gc.newGame();
		gc.setOpponend(player1);
		gc.setOpponend(player2);
	}

	@Test
	public void getPlayerAtTest() {
		Player p = gc.getPlayerOnTurn();
		gc.dropCoinWithSuccessFeedback(0);
		Player test = gc.getPlayerAt(0, 0);
		assertSame(p, test);

		p = null;
		test = null;
		p = gc.getPlayerOnTurn();
		gc.dropCoinWithSuccessFeedback(0);
		test = gc.getPlayerAt(1, 0);
		assertSame(p, test);

	}

	@Test
	public void dropCoinWithSuccessFeedbackTest() {
		gc.dropCoinWithSuccessFeedback(0);
		gc.dropCoinWithSuccessFeedback(0);
		gc.dropCoinWithSuccessFeedback(0);
		gc.dropCoinWithSuccessFeedback(0);
		Player p = gc.getPlayerAt(0, 0);
		assertNotNull(p);
		p = null;
		p = gc.getPlayerAt(1, 0);
		assertNotNull(p);
		p = null;
		p = gc.getPlayerAt(2, 0);
		assertNotNull(p);
		p = null;
		p = gc.getPlayerAt(3, 0);
		assertNotNull(p);
		p = null;
		p = gc.getPlayerAt(4, 0);
		assertNull(p);

	}

	@Test
	public void newGameTest() throws Exception {
		gc.dropCoinWithSuccessFeedback(0);
		gc.dropCoinWithSuccessFeedback(0);
		gc.dropCoinWithSuccessFeedback(0);
		gc.dropCoinWithSuccessFeedback(0);
		gc.newGame();
		Player p = gc.getPlayerAt(0, 0);
		assertNull(p);
	}

	@Test
	public void undoStepTest() {
		boolean success = true;
		success &= gc.dropCoinWithSuccessFeedback(0);
		success &= gc.dropCoinWithSuccessFeedback(0);
		success &= gc.dropCoinWithSuccessFeedback(0);
		success &= gc.dropCoinWithSuccessFeedback(0);
		success &= gc.dropCoinWithSuccessFeedback(0);
		assertTrue(success);
		Player p = gc.getGameField().getPlayerAt(4, 0);
		assertNotNull(p);
		gc.undoStep();
		p = null;
		p = gc.getGameField().getPlayerAt(4, 0);
		assertNull(p);
	}

	@Test
	public void redoStepTest() {
		boolean success = true;
		success &= gc.dropCoinWithSuccessFeedback(0);
		success &= gc.dropCoinWithSuccessFeedback(0);
		success &= gc.dropCoinWithSuccessFeedback(0);
		success &= gc.dropCoinWithSuccessFeedback(0);
		success &= gc.dropCoinWithSuccessFeedback(0);
		assertTrue(success);
		Player p = gc.getGameField().getPlayerAt(4, 0);
		assertNotNull(p);
		gc.undoStep();
		p = null;
		p = gc.getGameField().getPlayerAt(4, 0);
		assertNull(p);
		gc.redoStep();
		p = null;
		p = gc.getGameField().getPlayerAt(4, 0);
		assertNotNull(p);
	}

}
