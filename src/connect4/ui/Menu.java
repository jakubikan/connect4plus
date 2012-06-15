package connect4.ui;

import connect4.controller.GameController;

public abstract class Menu {

	public abstract void saveGame();

	public abstract void loadSaveGame();

	public void newGame() {
		GameController.getInstance().newGame();
	}

	public abstract void quitGame();

	public abstract void show();

	public void exitGame() {
		System.exit(0);
	}

}