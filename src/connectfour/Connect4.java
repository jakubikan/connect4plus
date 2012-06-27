package connectfour;

import connectfour.controller.GameController;
import connectfour.ui.gui.swing.SwingGUI;

/**
 * @author: Stefano Di Martino
 */

final class Connect4 {
    private Connect4() {}
    
    public static void main(String[] args) {
        //        GameController.getInstance().addObserver(new TUI());
        GameController.getInstance().newGame();
        GameController.getInstance().addObserver(new SwingGUI());
    }
}
