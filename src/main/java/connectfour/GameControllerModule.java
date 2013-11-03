package connectfour;

import com.google.inject.AbstractModule;

import connectfour.controller.GameController;
import connectfour.controller.IController;
import connectfour.persistence.ISaveGameDAO;
import connectfour.persistence.db4o.SaveGameDb4oDAO;
import connectfour.util.observer.IObserverWithArguments;

public class GameControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IController.class).to(GameController.class);
		bind(IObserverWithArguments.class).to(GameController.class);
		bind(ISaveGameDAO.class).to(SaveGameDb4oDAO.class);
	}
}
