/**
 * 
 */
package connectfour.ui.gui.swing.events;

import connectfour.controller.IController;
import connectfour.util.observer.IObserver;

import java.awt.event.MouseEvent;

/**
 * @author: Stefano Di Martino
 * @created: Jun 22, 2012
 */
public class RedoEvent extends EventAdapter {
	private final IController controller;
	
    public RedoEvent(final IController controller, final IObserver observer) {
        super(observer);
        this.controller = controller;
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {
        this.controller.redoStep();
        this.notifyObservers();
    }
}
