package connect4.model;

import connect4.model.gameField.GameField;

public abstract class PlayerAbstract implements Player {
    
    protected String name;
    protected GameField gameField;
    
    @Override
    public String getName() {
        return name;
        
    }
    
    @Override
    public void setName(String string) {
        // TODO Auto-generated method stub
        name = string;
        
    }
    
}
