/**
 * 
 */
package connectfour.model;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import connectfour.controller.IController;

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
    private final IController controller;

    public GameFieldEdit(final IController controller, final GameField previousState, final GameField newState, final String name) {
        this.previousState = previousState;
        this.newState = newState;
        this.name = name;
        this.controller = controller;
    }
    
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        this.controller.useState(newState);
    }
    
    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        this.controller.useState(previousState);
    }
    
    @Override
    public String getPresentationName() {
        return name;
    }
}