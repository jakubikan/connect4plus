package connectfour.model;

public class Human extends PlayerAbstract {
    private int move = 0;

    @Override
    public int dropCoin(final int column) {
        return column;
    }
    
    @Override
    public int getMove() {
        return move;
    }
    
    @Override
    public void setMove(final int column) {
        move = column;
        
    }

    @Override
    public void update(Object arg) { }
}