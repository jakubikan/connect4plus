package connectfour.ui.gui.swing.controller;

import connectfour.ui.gui.swing.widgets.ArrowCell;
import connectfour.util.observer.IObserverWithArguments;

import java.util.List;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */

public final class ArrowManager implements IObserverWithArguments {
    private int currentColumn;
    private List<ArrowCell> arrowCells;
    private static ArrowManager instance;
    
    private ArrowManager() {}
    
    public static ArrowManager getInstance() {
        if (instance == null) {
            instance = new ArrowManager();
        }
        return instance;
    }
    
    public void setArrowCells(List<ArrowCell> arrowCells) {
        this.arrowCells = arrowCells;
    }
    
    public void markColumnWhereMouseHasEntered(int col) {
        if (col != this.currentColumn) {
            // Hide arrow in old column
            this.arrowCells.get(this.currentColumn).showArrow(false);
            this.arrowCells.get(this.currentColumn).repaint();
            
            // Show arrow in new column
            this.arrowCells.get(col).showArrow(true);
            this.arrowCells.get(col).repaint();
            
            this.currentColumn = col;
        }
    }
    
    @Override
    public void update(Object arg) {
        int column = (Integer) arg;
        this.markColumnWhereMouseHasEntered(column);
    }
}