package connectfour;

import connectfour.controller.GameController;
import connectfour.ui.tui.TUI;
import connectfour.ui.gui.swing.*;

/**
 * @author: Stefano Di Martino
 */

final class Connect4 {
    private Connect4() {}

    
    public static void main(String[] args) {	
    	GameController.getInstance().addObserver(new TUI());
    	
        
        GameController.getInstance().newGame();
        
        GameController.getInstance().addObserver(new SwingGUI());
        /*
        Thread guiThread = new Thread(new Runnable() {
            @Override
            public void run() {
            	GameController.getInstance().addObserver(new SwingGUI());            	
            }
        });
        guiThread.start();*/
    }
}