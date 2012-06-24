package connectfour.controller;

import javax.swing.undo.UndoManager;

import connectfour.model.Human;
import connectfour.model.Player;
import connectfour.model.gameField.GameField;
import connectfour.util.observer.Observable;

public final class GameController extends Observable {

	private GameField gameField;
	private static GameController instance;
	private boolean bGameHasStarted;

	private final UndoManager undoManager = new UndoManager();

	private GameController() {
		this.undoManager.discardAllEdits();
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
			try {

				System.out.println("Copying State");
				GameField previousState = null;
				try {
					previousState = gameField.clone();
				} catch (CloneNotSupportedException e1) {
					System.err.println(e1.toString());
				}

				System.out.println("Getting Player");
				Player p = gameField.getPlayerOnTurn();
				p.setGameField(gameField);

				System.out.println("Trying to Calculate Move");

				int move = p.dropCoin(col);
				System.out.println(p.getName() + " making move: "
						+ move);

				System.out.println("Trying to make Move");
				gameField.dropCoin(move);
				gameField.changePlayerTurn(); // Change only on success the
												// players turnk
				success = true;

				GameField newState = null;
				try {
					newState = gameField.clone();
				} catch (CloneNotSupportedException e) {
					System.err.println(e.toString());
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
	 *            the gameField to set
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
		gameField = state;
	}

}
