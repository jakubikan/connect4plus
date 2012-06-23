package connectfour.model;

import connectfour.controller.GameController;
import connectfour.model.gameField.GameField;

public class Computer extends PlayerAbstract {
    
    private GameField gameField;
    private int doNextColumn = 0;
    
    @Override
    public void surrender() {}
    
    @Override
    public int dropCoin(final int column) {
        setMove(column);
        return getMove();
    }
    
    public Computer() {
        super();
    }
    
    @Override
    public int getMove() {
        /*
         * Random r = new Random(); int random = r.nextInt() % 7; return random < 0 ? -random :
         * random;
         */
        try {
            gameField = GameController.getInstance().getGameField().clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        maxWert(5);
        return doNextColumn;
        
    }
    
    @Override
    public void setMove(final int column) {
        
    }
    
    private int maxWert(final int restTiefe) {
        int ermittelt = -Integer.MAX_VALUE;
        int zugWert;
        for (int i = 0; i < GameField.DEFAULT_COLUMNS; i++) {
            GameField previousState = saveState();
            gameField.dropCoin(i);
            GameField newState = saveState();
            
            if (restTiefe <= 1 || gameField.getWinner() != null) {
                zugWert = gameField.evaluatePlayerScore();
            } else {
                zugWert = minWert(restTiefe - 1);
            }
            gameField = previousState;
            if (zugWert > ermittelt) {
                ermittelt = zugWert;
                doNextColumn = i;
            }
            
        }
        return ermittelt;
    }
    
    private int minWert(final int restTiefe) {
        int ermittelt = Integer.MAX_VALUE;
        int zugWert;
        for (int i = 0; i < GameField.DEFAULT_COLUMNS; i++) {
            GameField previousState = saveState();
            gameField.dropCoin(i);
            GameField newState = saveState();
            
            if (restTiefe <= 1 || gameField.getWinner() != null) {
                zugWert = gameField.evaluatePlayerScore();
            } else {
                zugWert = maxWert(restTiefe - 1);
            }
            gameField = previousState;
            if (zugWert < ermittelt) {
                ermittelt = zugWert;
            }
        }
        return ermittelt;
    }
    
    private GameField saveState() {
        GameField state = null;
        try {
            state = gameField.clone();
        } catch (CloneNotSupportedException e1) {
            e1.printStackTrace();
        }
        return state;
        
    }
}