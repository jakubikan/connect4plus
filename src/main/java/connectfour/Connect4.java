package connectfour;

import com.google.inject.Guice;
import com.google.inject.Injector;

import connectfour.controller.GameController;
import connectfour.ui.gui.swing.SwingGUI;
import connectfour.ui.tui.TUI;

/**
 * @author: Stefano Di Martino
 */

final class Connect4 {
    private Connect4() {}
    
    public static void main(String[] args) {
    	Injector injector = Guice.createInjector(new GameControllerModule());
    	
    	GameController controller = injector.getInstance(GameController.class);
    	controller.newGame();
        
    	controller.addObserver(injector.getInstance(SwingGUI.class));
    	controller.addObserver(injector.getInstance(TUI.class));
    }
}
