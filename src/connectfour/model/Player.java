package connectfour.model;

public interface Player {
    
    void surrender();
    
    void setName(String string);
    
    String getName();
    
    /**
     * This function should delegate to the method GameField.dropCoin(column, player).
     * 
     * @param column
     *            the Column where to drop the Coin
     * @param g
     *            is the Instance of the Gamefield
     * @return
     */
    int dropCoin(int column);
    
    int getMove();
    
    void setMove(int column);
}
