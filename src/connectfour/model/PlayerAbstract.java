package connectfour.model;

import connectfour.model.gameField.GameField;

public abstract class PlayerAbstract implements Player {
    private String name;
    private GameField gameField;
    
    public GameField getGameField() {
        return this.gameField;
    }
    
    @Override
    public String getName() {
        return name;
        
    }
    
    @Override
    public void setName(String string) {
        name = string;
    }
}
