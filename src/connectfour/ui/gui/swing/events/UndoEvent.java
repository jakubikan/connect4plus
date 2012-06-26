/**
 * 
 */
package connectfour.ui.gui.swing.events;

import java.awt.event.MouseEvent;

import connectfour.controller.GameController;
import connectfour.util.observer.IObserver;

/**
 * @author: Stefano Di Martino
 * @created: Jun 22, 2012
 */
public class UndoEvent extends EventAdapter {
    
    public UndoEvent(final IObserver oberserver) {
        super(oberserver);
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {
        GameController.getInstance().undoStep();
        this.notifyObservers();
    }
}
