package connectfour.model;

import connectfour.util.observer.ObservableWithArguments;

public abstract class PlayerAbstract extends ObservableWithArguments implements Player {
    private String name;
    private GameField gameField;
    
    @Override
    public GameField getGameField() {
        return this.gameField;
    }
    
    @Override
    public final void setGameField(final GameField gameField) {
        try {
            this.gameField = gameField.clone();
        } catch (CloneNotSupportedException e) {}
    }
    
    @Override
    public String getName() {
        return name;
        
    }
    
    @Override
    public void setName(final String string) {
        name = string;
    }
}
