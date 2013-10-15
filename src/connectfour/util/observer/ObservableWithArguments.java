package connectfour.util.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObservableWithArguments extends Observable {
    
    private List<IObserverWithArguments> subscribersWithArguments = new ArrayList<IObserverWithArguments>(
                                            2);
    
    public void addObserver(IObserverWithArguments s) {
        subscribersWithArguments.add(s);
    }
    
    public void removeObserver(IObserverWithArguments s) {
        subscribersWithArguments.remove(s);
    }
    
    public void notifyObservers(Object arg) {
        for (Iterator<IObserverWithArguments> iter = subscribersWithArguments.iterator(); iter
                                                .hasNext();) {
            IObserverWithArguments observer = iter.next();
            observer.update(arg);
        }
    }
}
