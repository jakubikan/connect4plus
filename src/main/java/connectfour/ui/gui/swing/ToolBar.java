package connectfour.ui.gui.swing;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import connectfour.controller.IController;
import connectfour.ui.gui.swing.events.LoadSaveGameEvent;
import connectfour.ui.gui.swing.events.NewGameEvent;
import connectfour.ui.gui.swing.events.RedoEvent;
import connectfour.ui.gui.swing.events.SaveEvent;
import connectfour.ui.gui.swing.events.UndoEvent;
import connectfour.util.observer.IObserver;

/**
 * @author: Stefano Di Martino
 * @created: Jun 22, 2012
 */

@SuppressWarnings("serial")
final class ToolBar extends JPanel {
    private final String undo = "previous";
    private final String redo = "next";
    private final String save = "save";
    private final String load = "load";
    private final String newGame = "new game";
    
    private final JToolBar toolBar = new JToolBar("Toolbar");
    private final IObserver observer;
    private final IController controller;
	private final Frame frame;
    
    public ToolBar(final IController controller, final IObserver observer, final Frame frame) {
        this.observer = observer;
        this.setBackground(Color.WHITE);
        this.toolBar.setBackground(Color.WHITE);
        this.add(toolBar);
        this.controller = controller;
        this.frame = frame;
        addButtons();
    }
    
    private void addButtons() {
        JButton button = null;
        
        button = makeNavigationButton(newGame, "New Game", "New Game");
        button.addMouseListener(new NewGameEvent(controller, observer));
        toolBar.add(button);
        
        button = makeNavigationButton(undo, "Undo", "Undo");
        button.addMouseListener(new UndoEvent(controller, observer));
        toolBar.add(button);
        
        button = makeNavigationButton(redo, "Redo", "Redo");
        button.addMouseListener(new RedoEvent(controller, observer));
        toolBar.add(button);
        
        button = makeNavigationButton(save, "Save", "Save");
        button.addMouseListener(new SaveEvent(this.frame, this.controller));
        toolBar.add(button);
        
        button = makeNavigationButton(load, "Load", "Load");
        button.addMouseListener(new LoadSaveGameEvent(this.frame, this.controller, observer));
        toolBar.add(button);
    }
    
    private JButton makeNavigationButton(final String actionCommand, final String toolTipText,
                                            final String altText) {
        // Create and initialize the button.
        JButton button = new JButton();
        button.setBackground(Color.WHITE);
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.setText(altText);
        
        return button;
    }
}