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
public class NewGameEvent extends EventAdapter {
    
    public NewGameEvent(final IObserver oberserver) {
        super(oberserver);
    }
    
    @Override
    public void mouseClicked(final MouseEvent e) {}
    
    @Override
    public void mousePressed(final MouseEvent e) {
        GameController.getInstance().newGame();
        this.notifyObservers();
    }
}
