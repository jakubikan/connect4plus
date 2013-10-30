package connectfour.ui;

import connectfour.controller.IController;

public abstract class Menu {

	private IController controller;

	public Menu(IController controller) {
		this.controller = controller;
	}
	
	public abstract void saveGame();

	public abstract void loadSaveGame();

	public void newGame() {
		this.controller.newGame();
	}

	public abstract void quitGame();

	public abstract void show();

	public void exitGame() {
		System.exit(0);
	}

}