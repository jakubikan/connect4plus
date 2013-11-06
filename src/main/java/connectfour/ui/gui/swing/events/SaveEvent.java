package connectfour.ui.gui.swing.events;

import connectfour.controller.IController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
