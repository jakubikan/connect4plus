package connectfour.ui.gui.swing.events;

import connectfour.util.observer.IObserver;
import connectfour.util.observer.Observable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author: Stefano Di Martino
 * @created: Jun 26, 2012
 */
public abstract class EventAdapter extends Observable implements MouseListener {
    
    public EventAdapter(final IObserver observer) {
        this.addObserver(observer);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}
    
    @Override
    public void mousePressed(MouseEvent e) {}
    
    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void mouseEntered(MouseEvent e) {}
    
    @Override
    public void mouseExited(MouseEvent e) {}
}