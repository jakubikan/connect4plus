package connect4.ui.gui.swing;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import connect4.ui.gui.swing.events.NewGameEvent;
import connect4.ui.gui.swing.events.RedoEvent;
import connect4.ui.gui.swing.events.UndoEvent;
import connect4.util.observer.IObserver;

/**
 * @author: Stefano Di Martino
 * @created: Jun 22, 2012
 */

@SuppressWarnings("serial")
final class ToolBar extends JPanel {
    private final String UNDO = "previous";
    private final String REDO = "next";
    private final String NEW_GAME = "new game";
    
    private final JToolBar toolBar = new JToolBar("Toolbar");
    private final IObserver observer;
    
    public ToolBar(final IObserver observer) {
        this.observer = observer;
        addButtons();
        this.add(toolBar);
    }
    
    private void addButtons() {
        JButton button = null;
        
        button = makeNavigationButton(NEW_GAME, "New Game", "New Game");
        button.addMouseListener(new NewGameEvent(observer));
        toolBar.add(button);
        
        button = makeNavigationButton(UNDO, "Undo", "Undo");
        button.addMouseListener(new UndoEvent(observer));
        toolBar.add(button);
        
        button = makeNavigationButton(REDO, "Redo", "Redo");
        button.addMouseListener(new RedoEvent(observer));
        toolBar.add(button);
    }
    
    private JButton makeNavigationButton(final String actionCommand, final String toolTipText,
                                            final String altText) {
        // Create and initialize the button.
        JButton button = new JButton();
        
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.setText(altText);
        
        return button;
    }
}
