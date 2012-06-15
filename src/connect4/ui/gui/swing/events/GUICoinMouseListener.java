package connect4.ui.gui.swing.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import connect4.ui.gui.swing.widgets.GUICoin;
import connect4.util.observer.Observable;

/**
* @author:  Stefano Di Martino
* @created: May 27, 2012
*/
public class GUICoinMouseListener extends Observable implements MouseListener {
	private GUICoin guiCoin;

	public GUICoinMouseListener(GUICoin guiCoin) {
		this.guiCoin = guiCoin;
		this.addObserver(guiCoin);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		ArrowManager.getInstance().markColumnWhereMouseHasEntered(
				guiCoin.getColumn());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		ArrowManager.getInstance().markColumnWhereMouseHasEntered(
				guiCoin.getColumn());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.notifyObservers();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.notifyObservers();
	}
}