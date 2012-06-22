package connect4.ui.gui.swing.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import connect4.controller.GameController;
import connect4.util.observer.IObserver;
import connect4.util.observer.Observable;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */
public class ArrowMouseListener extends Observable implements MouseListener {
    private final int column;
    
    public ArrowMouseListener(final IObserver observer, final int column) {
        this.column = column;
        this.addObserver(observer);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        GameController.getInstance().dropCoinWithSuccessFeedback(this.column);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.notifyObservers();
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        this.notifyObservers();
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        this.notifyObservers();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        this.notifyObservers();
    }
    
}
