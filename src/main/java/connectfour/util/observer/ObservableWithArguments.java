package connectfour.util.observer;

import java.util.ArrayList;
import java.util.List;

public class ObservableWithArguments extends Observable {
    
    private List<IObserverWithArguments> subscribersWithArguments = new ArrayList<>(
                                            2);
    
    public void addObserver(IObserverWithArguments s) {
        subscribersWithArguments.add(s);
    }
    
    public void removeObserver(IObserverWithArguments s) {
        subscribersWithArguments.remove(s);
    }
    
    public void notifyObservers(Object arg) {
        for (IObserverWithArguments observer : subscribersWithArguments) {
            observer.update(arg);
        }
    }
}
