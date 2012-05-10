package connect4.ui;

import connect4.controller.GameController;

public abstract class Menu {
	protected GameController controller;
	protected UI ui;

	public Menu() {
		controller = GameController.getInstance();
		ui = this.controller.getUI();
	}

	public abstract void saveGame();

	public abstract void loadSaveGame();

	public void newGame() {
		this.ui.drawGameField();
	}

	public abstract void quitGame();

	public abstract void show();

}