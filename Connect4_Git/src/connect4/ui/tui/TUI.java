package connect4.ui.tui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import connect4.controller.GameController;
import connect4.model.Player;
import connect4.model.gameField.GameField;
import connect4.ui.UI;
import connect4.util.observer.IObserver;

/**
* @author:  Stefano Di Martino
*/

public class TUI implements UI, IObserver {
	private final String EXIT = "quit";
	private GameController controller;
	private Player players[];

	public TUI() {
		this.controller = GameController.getInstance();
		this.players = controller.getPlayers();
	}

	@Override
	public void drawGameField() {
		if (controller.userHasWon()) {
			return;
		}

		String userInput = "";
		BufferedReader ir = new BufferedReader(new InputStreamReader(System.in));

		System.out.printf("%s ist dran\n\n", controller.getPlayerNameOnTurn());
		System.out.println(this.renderGameField());
		System.out.print("Eingabe: ");

		try {
			userInput = ir.readLine();
		} catch (IOException e) {
			System.out.println("Eingabefehler!\n");
			e.printStackTrace();
		}

		System.out.println("\n\n");

		if (userInput.equals(this.EXIT)) {
			System.exit(0);
		}

		this.parseUserInput(userInput);
	}

	private void parseUserInput(String userInput) {
		if (isInteger(userInput)) {
			int column = Integer.parseInt(userInput) - 1;
			controller.dropCoinWithSuccessFeedback(column);
		} else {
			System.out.println("Ungültige Eingabe!\n");
			this.drawGameField();
		}
	}

	private boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String renderGameField() {
		int row = GameField.DEFAULT_ROWS - 1;
		int col = GameField.DEFAULT_COLUMNS;

		StringBuilder playingField = new StringBuilder();
		String begin = "|";
		String empty = "_";
		String end = "_|";
		String coin1 = "O";
		String coin2 = "X";

		for (int currentRow = row; currentRow >= 0; currentRow--) {
			playingField.append(begin);

			for (int currentColumn = 0; currentColumn < col; currentColumn++) {
				playingField.append(empty);

				Player player = this.controller.getPlayerAt(currentRow,
						currentColumn);

				if (player == null) {
					playingField.append(empty);
				} else if (player == players[0]) {
					playingField.append(coin1);
				} else if (player == players[1]) {
					playingField.append(coin2);
				} else {
					playingField.append("FEHLER!");
				}

				playingField.append(end);
			}
			playingField.append(GameController.newline);
		}
		return playingField.toString();
	}

	@Override
	public void update() {
		this.drawGameField();
	}
}