package connect4.ui.tui;

import connect4.controller.GameController;
import connect4.model.GameField;
import connect4.model.Player;
import connect4.ui.UI;

public class TUI extends UI {

	/*
	 * (non-Javadoc)
	 * @see gui.Connect4GUI#clearAll()
	 */
	@Override
	public void clearAll() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see gui.Connect4GUI#dropCoin(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void dropCoin(int x, int y) {
		renderGameField();
	}

	/*
	 * (non-Javadoc)
	 * @see connect4.ui.UI#drawGameField()
	 */
	@Override
	public void drawGameField() {

	}

	public String renderGameField() {
		int row = GameField.DEFAULT_GAME_HEIGHT - 1;
		int col = GameField.DEFAULT_GAME_WIDTH;

		StringBuilder playingField = new StringBuilder();
		String begin = "|";
		String empty = "_";
		String end = "_|";
		String player1 = "O";
		String player2 = "X";

		for (int currentRow = row; currentRow >= 0; currentRow--) {
			playingField.append(begin);

			for (int currentColumn = 0; currentColumn < col; currentColumn++) {
				playingField.append(empty);

				Player player = this.controller.getPlayerAt(currentRow,
						currentColumn);

				if (player == null) {
					playingField.append(empty);
				} else if (player == players[0]) {
					playingField.append(player1);
				} else if (player == players[1]) {
					playingField.append(player2);
				} else {
					playingField.append("FEHLER!");
				}

				playingField.append(end);
			}
			playingField.append(GameController.newline);
		}

		return playingField.toString();
	}
}