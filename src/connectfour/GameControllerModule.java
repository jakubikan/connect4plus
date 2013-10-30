package connectfour;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import connectfour.controller.GameController;
import connectfour.controller.IController;
import connectfour.util.observer.IObserverWithArguments;

public class GameControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IController.class).to(GameController.class);
		bind(IObserverWithArguments.class).to(GameController.class).in(Singleton.class);
	}
}
