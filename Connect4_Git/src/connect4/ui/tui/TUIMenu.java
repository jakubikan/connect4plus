package connect4.ui.tui;

import connect4.controller.GameController;
import connect4.ui.Menu;

public class TUIMenu extends Menu {

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.Menu#quitGame()
	 */
	@Override
	public void quitGame() {
		System.exit(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.Menu#saveGame()
	 */
	@Override
	public void saveGame() {
		GameController.getInstance().saveGameState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.Menu#show()
	 */
	@Override
	public void show() {
		System.out.println(this.renderMenu());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gui.Menu#loadGame()
	 */
	@Override
	public void loadSaveGame() {
		GameController.getInstance().loadGameState();
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