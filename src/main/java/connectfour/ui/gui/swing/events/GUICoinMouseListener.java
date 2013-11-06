package connectfour.ui.gui.swing.events;

import connectfour.util.observer.IObserver;
import connectfour.util.observer.Observable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */
public class GUICoinMouseListener extends Observable implements MouseListener {
    
    public GUICoinMouseListener(final IObserver observer) {
        this.addObserver(observer);
    }
    
    @Override
    public void mouseEntered(final MouseEvent e) {}
    
    @Override
    public void mouseExited(final MouseEvent e) {}
    
    @Override
    public void mousePressed(final MouseEvent e) {
        this.notifyObservers();
    }
    
    @Override
    public void mouseReleased(final MouseEvent e) {}
    
    @Override
    public void mouseClicked(final MouseEvent e) {}
}