package connectfour.util.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObservableWithArguments extends Observable {
    
    private List<IObserverWithArguments> subscribers = new ArrayList<IObserverWithArguments>(2);
    
    public void addObserver(IObserverWithArguments s) {
        subscribers.add(s);
    }
    
    public void removeObserver(IObserverWithArguments s) {
        subscribers.remove(s);
    }
    
    public void notifyObservers(Object arg) {
        for (Iterator<IObserverWithArguments> iter = subscribers.iterator(); iter.hasNext();) {
            IObserverWithArguments observer = iter.next();
            observer.update(arg);
        }
    }
}
