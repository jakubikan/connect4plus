/**
 * 
 */
package connect4.ui.gui.swing.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import connect4.controller.GameController;
import connect4.util.observer.IObserver;
import connect4.util.observer.Observable;

/**
 * @author: Stefano Di Martino
 * @created: Jun 22, 2012
 */
public class UndoEvent extends Observable implements MouseListener {
    
    public UndoEvent(final IObserver oberserver) {
        this.addObserver(oberserver);
    }
    
    @Override
    public void mouseClicked(final MouseEvent e) {}
    
    @Override
    public void mousePressed(final MouseEvent e) {
        GameController.getInstance().undoStep();
        this.notifyObservers();
    }
    
    @Override
    public void mouseReleased(final MouseEvent e) {}
    
    @Override
    public void mouseEntered(final MouseEvent e) {}
    
    @Override
    public void mouseExited(final MouseEvent e) {}
    
}
