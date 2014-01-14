package connectfour.model;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import connectfour.controller.GameController;
import connectfour.controller.IController;
import connectfour.persistence.ISaveGameDAO;
import connectfour.persistence.couchdb.SaveGameCouchDbDAO;
import connectfour.persistence.db4o.SaveGameDb4oDAO;
import connectfour.persistence.hibernate.SaveGameDbHibernate;
import connectfour.util.observer.IObserverWithArguments;

public class SolverModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SolverPlugin.class).to(MiddleSolver.class);
    }
}
