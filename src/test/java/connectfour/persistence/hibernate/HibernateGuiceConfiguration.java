package connectfour.persistence.hibernate;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import connectfour.controller.GameController;
import connectfour.controller.HighScoreController;
import connectfour.controller.IController;
import connectfour.controller.IHighScoreController;
import connectfour.persistence.ISaveGameDAO;
import connectfour.ui.gui.swing.SwingGUI;
import connectfour.ui.tui.TUI;
import connectfour.util.observer.IObserver;
import connectfour.util.observer.IObserverWithArguments;

/**
 * Created by jakub on 1/3/14.
 */
public class HibernateGuiceConfiguration extends AbstractModule {
    @Override
    protected void configure() {
        bind(IController.class).to(GameController.class);
        bind(IObserverWithArguments.class).to(GameController.class);
        bind(ISaveGameDAO.class).to(SaveGameDbHibernate.class);
        bind(IHighScoreController.class).to(HighScoreController.class);
    }
}
