package connect4;

import connect4.controller.GameController;
import connect4.ui.gui.swing.SwingGUI;
import connect4.ui.tui.TUI;

/**
* @author:  Stefano Di Martino
*/

public class Connect4 {

	public static void main(String[] args) {
		//GameController.getInstance().addObserver(new TUI());
		GameController.getInstance().newGame();
		GameController.getInstance().addObserver(new SwingGUI());
	}
}
