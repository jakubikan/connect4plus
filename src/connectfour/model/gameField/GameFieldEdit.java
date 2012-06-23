/**
 * 
 */
package connectfour.model.gameField;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;

import connectfour.controller.GameController;

/**
 * @author jakub
 * 
 */
@SuppressWarnings("serial")
public class GameFieldEdit extends AbstractUndoableEdit {
	/**
	 * @author: jakub
	 * @created: May 30, 2012
	 */
	private final GameField previousState;
	private final GameField newState;
	private final String name;

	/**
	 * @param name
	 * 
	 */
	public GameFieldEdit(final GameField previousState,
			final GameField newState, final String name) {
		this.previousState = previousState;
		this.newState = newState;
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.undo.AbstractUndoableEdit#redo()
	 */
	@Override
	public void redo() throws CannotRedoException {
		super.redo();
		GameController.getInstance().useState(newState);
	}

	@Override
	public void undo() throws CannotRedoException {
		super.undo();
		GameController.getInstance().useState(previousState);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.undo.AbstractUndoableEdit#getPresentationName()
	 */
	@Override
	public String getPresentationName() {
		return name;
	}

}
