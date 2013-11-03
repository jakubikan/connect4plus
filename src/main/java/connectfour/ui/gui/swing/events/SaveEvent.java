package connectfour.ui.gui.swing.events;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import connectfour.controller.IController;

public class SaveEvent extends MouseAdapter {
	private Frame frame;
	private IController controller;

	public SaveEvent(final Frame container, final IController controller) {
		this.frame = container;
		this.controller = controller;
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		String[] message = { "Savegame Name" };
		String saveGameName = JOptionPane.showInputDialog(frame, message,
				"Enter Savegame Name", JOptionPane.OK_CANCEL_OPTION);
		
		if (saveGameName != null)
			this.controller.saveGame(saveGameName);
	}
}
