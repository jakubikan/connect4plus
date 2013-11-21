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
        	if (gameField != null) {
        		this.gameField = gameField.clone();
        	} else { // db4o needs to set the players gameField to null, in order not to save it. 
        		this.gameField = null;
        	}
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (super.equals(obj)) {
            return true;
        }

        return obj instanceof Player && getName().equals(((Player) obj).getName());
    }
}