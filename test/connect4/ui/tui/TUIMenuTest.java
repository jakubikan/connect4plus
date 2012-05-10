package connect4.ui.tui;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import connect4.controller.GameController;

/**
 * @author: Stefano Di Martino
 * @created: May 9, 2012
 */

public class TUIMenuTest {
    
	TUIMenu menu;
    
    @Before
    public void setUp() throws Exception {
    	GameController.getInstance();
        menu = (TUIMenu) GameController.getInstance().getMenu();
    }
    
	@Test
    public void test() {
		String menu1 = "Bitte wählen Sie folgende Optionen: " + GameController.newline +
					   "1. Neues Spiel" + GameController.newline +
					   "2. Spielstand laden" + GameController.newline +
					   "3. Spiel beenden" + GameController.newline;
		
		assertEquals(menu1, menu.renderMenu());
		
		GameController.getInstance().newGame();
		
		String menu2 = "Bitte wählen Sie folgende Optionen: " + GameController.newline +
		   "1. Neues Spiel" + GameController.newline +
		   "2. Spielstand laden" + GameController.newline +
		   "3. Spiel beenden" + GameController.newline +
		   "4. Spiel fortsetzen";
		
		assertEquals(menu2, menu.renderMenu());
	}
}
