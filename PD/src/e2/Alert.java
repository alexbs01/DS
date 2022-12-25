package e2;

public class Alert extends Subject {
    private final int OFF = -1, ORANGE = 0, RED = 1;
    private final double minOrange, maxOrange, minRed, maxRed;
    private int state;
    private final String nameAlert;
    private final Sensor sensor;

    public Alert(String nameAlert, double minOrange, double maxOrange, double minRed, double maxRed, Sensor sensor) {
        this.nameAlert = nameAlert;
        this.minOrange = minOrange;
        this.maxOrange = maxOrange;
        this.minRed = minRed;
        this.maxRed = maxRed;

        this.state = OFF;
        this.sensor = sensor;
        sensor.addAlert(this);
    }

    public String getNameAlert() {
        return nameAlert;
    }

    public void update(double Value) {
        if (Value < maxOrange && Value > minOrange) {
            state = OFF;
            notifyObservers(Value, state, (maxOrange + minOrange)/2, sensor,this);
        } else if(Value < maxRed && Value > minRed) {
            state = ORANGE;
            notifyObservers(Value, state, (maxOrange + minOrange)/2, sensor,this);
        } else {
            state = RED;
            notifyObservers(Value, state, (maxOrange + minOrange)/2, sensor, this);
        }
    }
}