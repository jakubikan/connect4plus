/**
 * 
 */
package connect4.ui.tui;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import connect4.controller.GameController;
import connect4.model.Coin;
import connect4.model.GameField;
import connect4.model.Human;
import connect4.model.Player;

/**
 * @author Stefano Di Martino
 */
public class TUITest {
    
    GameField gameField;
    Player player1;
    Player player2;
    TUI tui;
    
    @Before
    public void setUp() throws Exception {
    	GameController.getInstance().newGame();

        player1 = GameController.getInstance().getPlayers()[0];
        player2 = GameController.getInstance().getPlayers()[1];
        gameField = player1.getGameField();
        tui = (TUI) GameController.getInstance().getUI();
    }
    
    @Test
    public void test() {
        dropCointTest1();

        String strCoinTest1 = "|___|_X_|___|___|___|___|___|" + GameController.newline + 
                              "|___|_X_|___|___|___|___|___|" + GameController.newline +
                              "|___|_X_|___|___|___|___|___|" + GameController.newline +
                              "|___|_X_|___|___|___|___|___|" + GameController.newline +
                              "|___|_X_|___|_O_|___|___|___|" + GameController.newline +
                              "|___|_X_|___|_O_|___|___|___|" + GameController.newline;
        
        assertEquals(strCoinTest1, tui.renderGameField());
    }
    
    public void dropCointTest1() {
        int row;
        
        row = player1.dropCoin(3);
        assertEquals(0, row);
        assertEquals(gameField.getPlayerAt(row, 3), player1);
        row = player1.dropCoin(3);
        assertEquals(gameField.getPlayerAt(row, 3), player1);
        assertEquals(1, row);
        row = player2.dropCoin(1);
        assertEquals(gameField.getPlayerAt(row, 1), player2);
        assertEquals(0, row);
        row = player2.dropCoin(1);
        assertEquals(gameField.getPlayerAt(row, 1), player2);
        assertEquals(1, row);
        row = player2.dropCoin(1);
        assertEquals(2, row);
        assertEquals(gameField.getPlayerAt(row, 1), player2);
        row = player2.dropCoin(1);
        assertEquals(3, row);
        assertEquals(gameField.getPlayerAt(row, 1), player2);
        row = player2.dropCoin(1);
        assertEquals(4, row);
        assertEquals(gameField.getPlayerAt(row, 1), player2);
        row = player2.dropCoin(1);
        assertEquals(5, row);
        assertEquals(gameField.getPlayerAt(row, 1), player2);
        assertEquals(gameField.getPlayerAt(1, 1), player2);
    }
}
