package connect4.ui.gui.swing.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import connect4.ui.gui.swing.widgets.GUICoin;
import connect4.util.observer.Observable;

/**
 * @author: Stefano Di Martino
 * @created: May 27, 2012
 */
public class GUICoinMouseListener extends Observable implements MouseListener {
    private final GUICoin guiCoin;
    
    public GUICoinMouseListener(final GUICoin guiCoin) {
        this.guiCoin = guiCoin;
        this.addObserver(guiCoin);
    }
    
    @Override
    public void mouseEntered(final MouseEvent e) {
        ArrowManager.getInstance().markColumnWhereMouseHasEntered(guiCoin.getColumn());
    }
    
    @Override
    public void mouseExited(final MouseEvent e) {
        ArrowManager.getInstance().markColumnWhereMouseHasEntered(guiCoin.getColumn());
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