package connect4.controller;

import javax.swing.undo.UndoManager;

import connect4.model.Human;
import connect4.model.Player;
import connect4.model.gameField.GameField;
import connect4.model.gameField.GameFieldEdit;
import connect4.util.observer.Observable;

public final class GameController extends Observable {

	private GameField gameField;
	private static GameController instance;
	private boolean bGameHasStarted;

	private final UndoManager undoManager = new UndoManager();

	public static String newline = System
			.getProperty("line.separator");

	private GameController() {
		this.gameField = new GameField();
		this.bGameHasStarted = false;
	}

	public void newGame() {
		this.bGameHasStarted = true;
		gameField = new GameField();
		this.notifyObservers();
	}

	public String getWinner() {
		Player winner = gameField.getWinner();
		if (winner != null) {
			return winner.getName();
		} else {
			return "";
		}
	}

	public void saveGameState() {

	}

	public void loadGameState() {

	}

	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}

		return instance;
	}

	public boolean gameHasStarted() {
		return this.bGameHasStarted;
	}

	public boolean dropCoinWithSuccessFeedback(final int col) {

		boolean success = false;
		if (!userHasWon()) {
			GameField previousState = null;
			try {
				previousState = gameField.clone();
			} catch (CloneNotSupportedException e1) {
				e1.printStackTrace();
			}

			try {

				Player p = gameField.getPlayerOnTurn();
				int move = p.dropCoin(col);

				gameField.dropCoin(move);
				gameField.changePlayerTurn(); // Change only on success the
												// players turn
				success = true;

				GameField newState = null;
				try {
					newState = gameField.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}

				String undoInfo = String.format(
						"Undoing %s Player Move", getPlayerOnTurn()
								.getName());
				GameFieldEdit edit = new GameFieldEdit(previousState,
						newState, undoInfo);
				undoManager.addEdit(edit);
				System.out.println("Added Undo for "
						+ undoManager.getUndoPresentationName());

				this.notifyObservers();
			} catch (IllegalArgumentException e) {
				System.out.println("Ungueltige Eingabe!\n");
			}
		}
		return success;
	}

	public Player getPlayerOnTurn() {
		return gameField.getPlayerOnTurn();
	}

	public boolean userHasWon() {
		Player winner = gameField.getWinner();

		if (winner == null) {
			return false;
		}

		return true;
	}

	public String getPlayerNameOnTurn() {
		Player player = gameField.getPlayerOnTurn();
		return player.getName();

	}

	/**
	 * @param gameField
	 *            the gfinal ld to set
	 */
	public void setGameField(final GameField gameField) {
		this.gameField = gameField;
	}

	/**
	 * @return the gameField
	 */
	public GameField getGameField() {
		return this.gameField;
	}

	public void undoStep() {
		if (undoManager.canUndo()) {
			System.out.println("Do Undo "
					+ undoManager.getPresentationName());
			undoManager.undo();
		} else {
			System.out.println("Cant Undo");
			return;
		}
	}

	/**
	 * 
	 */
	public void redoStep() {
		if (undoManager.canRedo()) {
			System.out.println("Do Redo "
					+ undoManager.getPresentationName());
			undoManager.redo();
		} else {
			System.out.println("Cant Redo");
			return;
		}
		// TODO Auto-generated method stub

	}

	public void setPlayer(final Human p) {
		gameField.setPlayer(p);

	}

	public void setOpponend(final Player p) {
		gameField.setOpponend(p);
	}

	// Only for Support. This method sould not be used any more
	// DEPRECATED!!!!!
	// Since its bad to get an Array of Objects.
	public Player[] getPlayers() {
		return gameField.getPlayers();
	}

	public Player getPlayerAt(final int row, final int col) {
		return gameField.getPlayerAt(row, col);
	}

	/**
	 * @return
	 */
	public Player getOpponend() {
		return gameField.getOpponend();
	}

	/**
	 * @return
	 */
	public Human getPlayer() {
		return gameField.getPlayer();
	}

	/**
	 * @param state
	 */
	public void useState(final GameField state) {
		try {
			gameField = state.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}