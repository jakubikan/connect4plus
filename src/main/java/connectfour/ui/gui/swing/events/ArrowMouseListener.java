package connectfour.ui.gui.swing.events;

import connectfour.controller.IController;
import connectfour.util.observer.IObserver;
import connectfour.util.observer.Observable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */
public class ArrowMouseListener extends Observable implements MouseListener {
    private final int column;
    private final IController controller;
    
    public ArrowMouseListener(IController controller, final IObserver observer, final int column) {
        this.column = column;
        this.addObserver(observer);
        this.controller = controller;
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        controller.dropCoinWithSuccessFeedback(this.column);
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
