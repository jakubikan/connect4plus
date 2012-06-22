package connect4.model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import connect4.model.gameField.GameField;

public class GameFieldTest {
    
    GameField gameField;
    Player player;
    Player opponend;
    
    @Before
    public void setUp() throws Exception {
        player = new Human();
        player.setName("Hugo");
        opponend = new Human();
        gameField = new GameField();
    }
    
    @After
    public void tearDown() throws Exception {
        gameField = new GameField();
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
        
        int row;
        row = gameField.dropCoin(0, opponend);
        row = gameField.dropCoin(0, opponend);
        row = gameField.dropCoin(0, opponend);
        row = gameField.dropCoin(0, opponend);
        gameField = new GameField();
        assertEquals(gameField.getPlayerAt(0, 0), null);
    }
    
    @Test
    public void isWonTest() {
        int row;
        System.out.println("isWonTest");
        row = gameField.dropCoin(0, opponend);
        row = gameField.dropCoin(0, opponend);
        row = gameField.dropCoin(0, opponend);
        row = gameField.dropCoin(0, opponend);
        
        assertEquals(gameField.getWinner(), opponend);
        
        gameField = new GameField();
        
        row = gameField.dropCoin(0, opponend);
        row = gameField.dropCoin(0, opponend);
        row = gameField.dropCoin(0, player);
        row = gameField.dropCoin(0, player);
        row = gameField.dropCoin(0, opponend);
        row = gameField.dropCoin(0, opponend);
        assertEquals(gameField.getWinner(), null);
        
        gameField = new GameField();
        
        /*
         * o ox oxx oxxxoo
         */
        row = gameField.dropCoin(0, opponend);
        row = gameField.dropCoin(1, player);
        row = gameField.dropCoin(1, opponend);
        row = gameField.dropCoin(2, player);
        row = gameField.dropCoin(3, opponend);
        row = gameField.dropCoin(2, player);
        row = gameField.dropCoin(2, opponend);
        row = gameField.dropCoin(3, player);
        row = gameField.dropCoin(4, opponend);
        row = gameField.dropCoin(3, player);
        row = gameField.dropCoin(3, opponend);
        assertEquals(gameField.getWinner(), opponend);
        
        gameField = new GameField();
        
        /*
         * x ox xox xoxox
         */
        row = gameField.dropCoin(6, player);
        row = gameField.dropCoin(5, opponend);
        row = gameField.dropCoin(4, player);
        row = gameField.dropCoin(3, opponend);
        row = gameField.dropCoin(5, player);
        row = gameField.dropCoin(4, opponend);
        row = gameField.dropCoin(3, player);
        row = gameField.dropCoin(3, opponend);
        row = gameField.dropCoin(4, player);
        row = gameField.dropCoin(2, opponend);
        row = gameField.dropCoin(3, player);
        assertEquals(gameField.getWinner(), player);
        
    }
    
}
