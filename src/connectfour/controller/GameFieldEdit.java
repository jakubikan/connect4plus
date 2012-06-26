/**
 * 
 */
package connectfour.controller;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import connectfour.model.GameField;

/**
 * @author jakub
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
    
    public GameFieldEdit(final GameField previousState, final GameField newState, final String name) {
        this.previousState = previousState;
        this.newState = newState;
        this.name = name;
    }
    
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        GameController.getInstance().useState(newState);
    }
    
    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        GameController.getInstance().useState(previousState);
    }
    
    @Override
    public String getPresentationName() {
        return name;
    }
}