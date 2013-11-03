package connectfour.ui.gui.swing.events;

import java.awt.Frame;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import connectfour.controller.IController;
import connectfour.util.observer.IObserver;

public class LoadSaveGameEvent extends EventAdapter {
	private Frame frame;
	final IController controller;

	public LoadSaveGameEvent(final Frame container, final IController controller, final IObserver observer) {
		super(observer);
		this.frame = container;
		this.controller = controller;
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		Object[] allSaveGameNames = this.controller.getAllSaveGameNames().toArray();

		String selectedSaveGameName = (String) JOptionPane.showInputDialog(
				frame, "Pick a Name:", "ComboBox Dialog",
				JOptionPane.QUESTION_MESSAGE, null, allSaveGameNames,
				allSaveGameNames[0]);
		
		System.out.println(selectedSaveGameName);

		if (selectedSaveGameName != null) {
			this.controller.loadSaveGame(selectedSaveGameName);
			this.notifyObservers();
		}
	}
}