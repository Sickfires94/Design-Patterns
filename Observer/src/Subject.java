import java.util.ArrayList;
import java.util.List;

abstract class Subject{
    List<Observer> observers = new ArrayList<Observer>();

    void attach(Observer observer){
        observers.add(observer);
    }

    void detach(Observer observer){
        observers.remove(observer);
    }

    void notifyObservers(){
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}