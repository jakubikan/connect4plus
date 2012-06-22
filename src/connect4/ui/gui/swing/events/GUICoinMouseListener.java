package connect4.ui.gui.swing.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import connect4.util.observer.IObserver;
import connect4.util.observer.Observable;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */
public class GUICoinMouseListener extends Observable implements MouseListener {
    private final int column;
    
    public GUICoinMouseListener(final IObserver observer, final int column) {
        this.column = column;
        this.addObserver(observer);
    }
    
    @Override
    public void mouseEntered(final MouseEvent e) {
        ArrowManager.getInstance().markColumnWhereMouseHasEntered(this.column);
    }
    
    @Override
    public void mouseExited(final MouseEvent e) {
        ArrowManager.getInstance().markColumnWhereMouseHasEntered(this.column);
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {}
    
    @Override
    public void mouseReleased(final MouseEvent e) {
        this.notifyObservers();
    }
    
    @Override
    public void mouseClicked(final MouseEvent e) {}
}