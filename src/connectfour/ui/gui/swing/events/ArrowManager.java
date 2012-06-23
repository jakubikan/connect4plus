package connectfour.ui.gui.swing.events;

import connectfour.ui.gui.swing.widgets.ArrowCell;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */

public final class ArrowManager {
    private int currentColumn;
    private ArrowCell[] arrowCells;
    private static ArrowManager instance;
    
    private ArrowManager() {}
    
    public static ArrowManager getInstance() {
        if (instance == null) {
            instance = new ArrowManager();
        }
        return instance;
    }
    
    public void setArrowCells(ArrowCell[] arrowCells) {
        this.arrowCells = arrowCells;
    }
    
    public void markColumnWhereMouseHasEntered(int col) {
        if (col != this.currentColumn) {
            // Hide arrow in old column
            this.arrowCells[this.currentColumn].showArrow(false);
            this.arrowCells[this.currentColumn].repaint();
            
            // Show arrow in new column
            this.arrowCells[col].showArrow(true);
            this.arrowCells[col].repaint();
            
            this.currentColumn = col;
        }
    }
}