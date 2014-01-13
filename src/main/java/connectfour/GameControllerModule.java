package connectfour;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import connectfour.controller.GameController;
import connectfour.controller.IController;
import connectfour.persistence.ISaveGameDAO;
import connectfour.persistence.couchdb.SaveGameCouchDbDAO;
import connectfour.persistence.db4o.SaveGameDb4oDAO;
import connectfour.persistence.hibernate.SaveGameDbHibernate;
import connectfour.solver.MiddleSolver;
import connectfour.solver.SolverPlugin;
import connectfour.ui.gui.swing.SwingGUI;
import connectfour.ui.tui.TUI;
import connectfour.util.observer.IObserver;
import connectfour.util.observer.IObserverWithArguments;

public class GameControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IController.class).to(GameController.class);
        bind(IObserverWithArguments.class).to(GameController.class);
        bind(ISaveGameDAO.class).annotatedWith(Names.named("hibernate")).to(SaveGameDbHibernate.class);
        bind(ISaveGameDAO.class).annotatedWith(Names.named("db4o")).to(SaveGameDb4oDAO.class);
        bind(ISaveGameDAO.class).annotatedWith(Names.named("couchdb")).to(SaveGameCouchDbDAO.class);
        //bind(ISaveGameDAO.class).to(SaveGameDbHibernate.class);
        //bind(ISaveGameDAO.class).to(SaveGameCouchDbDAO.class);
        bind(ISaveGameDAO.class).to(SaveGameDb4oDAO.class);
        bind(SolverPlugin.class).to(MiddleSolver.class);
    }
}
