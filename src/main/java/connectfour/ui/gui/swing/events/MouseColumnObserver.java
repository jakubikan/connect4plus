package connectfour.ui.gui.swing.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import connectfour.util.observer.IObserverWithArguments;
import connectfour.util.observer.ObservableWithArguments;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */
public class MouseColumnObserver extends ObservableWithArguments implements MouseListener {
    private final int column;
    
    public MouseColumnObserver(final IObserverWithArguments observer, final int column) {
        this.column = column;
        this.addObserver(observer);
    }
    
    @Override
    public void mouseEntered(final MouseEvent e) {
        this.notifyObservers(this.column);
    }
    
    @Override
    public void mouseExited(final MouseEvent e) {
        this.notifyObservers(this.column);
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {}
    
    @Override
    public void mouseReleased(final MouseEvent e) {}
    
    @Override
    public void mouseClicked(final MouseEvent e) {}
}