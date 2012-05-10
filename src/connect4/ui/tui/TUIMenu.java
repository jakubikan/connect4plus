package connect4.ui.tui;

import connect4.controller.GameController;
import connect4.ui.Menu;

public class TUIMenu extends Menu {

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.Menu#newGame()
	 */
	@Override
	public void newGame() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.Menu#quitGame()
	 */
	@Override
	public void quitGame() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.Menu#saveGame()
	 */
	@Override
	public void saveGame() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.Menu#show()
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.Menu#loadGame()
	 */
	@Override
	public void loadSaveGame() {
		// TODO Auto-generated method stub

	}

	public String renderMenu() {
		String menu = "Bitte wählen Sie folgende Optionen: "
				+ GameController.newline + "1. Neues Spiel"
				+ GameController.newline + "2. Spielstand laden"
				+ GameController.newline + "3. Spiel beenden"
				+ GameController.newline;

		if (GameController.getInstance().gameHasStarted()) {
			menu += "4. Spiel fortsetzen";
		}

		return menu;
	}
}