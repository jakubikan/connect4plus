package connect4.ui;

import connect4.controller.GameController;
import connect4.model.Player;

public abstract class UI {
	protected GameController controller;
	protected Player players[];

	public UI() {
		this.controller = GameController.getInstance();
		this.players = controller.getPlayers();
	}

	public abstract void dropCoin(int x, int y);

	public abstract void clearAll();

	public abstract void drawGameField();
}