package connectfour.util.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Observable {
    
    private List<IObserver> subscribers = new ArrayList<IObserver>(2);
    
    public void addObserver(IObserver s) {
        subscribers.add(s);
    }
    
    public void removeObserver(IObserver s) {
        subscribers.remove(s);
    }
    
    public void removeAllObservers() {
        subscribers = new ArrayList<IObserver>();
    }
    
    public void notifyObservers() {
        for (IObserver observer : subscribers) {
            observer.update();
        }
    }
}
