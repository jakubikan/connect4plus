package connect4.util.observer;

import java.util.ArrayList;
import java.util.Iterator;

public class Observable {
    
    private ArrayList<IObserver> subscribers = new ArrayList<IObserver>(2);
    
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
        for (Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
            IObserver observer = iter.next();
            observer.update();
        }
    }
}
