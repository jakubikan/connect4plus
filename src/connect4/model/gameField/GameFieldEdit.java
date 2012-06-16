/**
 * 
 */
package connect4.model.gameField;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;

import connect4.controller.GameController;

/**
 * @author jakub
 *
 */
@SuppressWarnings("serial")
public class GameFieldEdit extends AbstractUndoableEdit {
	/**
	* @author:  jakub
	* @created: May 30, 2012
	*/
	private GameField previousState;
	private GameField newState;
	private String name;

	/**
	 * @param name 
	 * 
	 */
	public GameFieldEdit(GameField previousState, GameField newState,
			String name) {
		this.previousState = previousState;
		this.newState = newState;
		this.name = name;
		System.out.println(previousState);
		System.out.println(newState);
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
	 * @see javax.swing.undo.AbstractUndoableEdit#getPresentationName()
	 */
	@Override
	public String getPresentationName() {
		return name;
	}

}
