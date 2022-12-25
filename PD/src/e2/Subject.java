package e2;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private final List<Observer> observers = new ArrayList<>(); //Lista

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers(double Value, int typeAlert, double newValue, Sensor sensor, Alert alerta) {
        for (Observer o : observers)
            o.update(Value, typeAlert, newValue, sensor, alerta);
    }
}