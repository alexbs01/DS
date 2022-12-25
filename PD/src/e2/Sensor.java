package e2;

import java.util.List;

public class Sensor {
    private double levelValue;  //Valor del parámetro.
    private final String levelName; //Nombre del parámetro.
    private final List<Alert> alerts;   //Lista de alertas.
    private final Tank tanque;  //Tanque.

    public Sensor(double levelValue, String levelName, List<Alert> alerts, Tank tanque) {   //Contructor de sensor.
        this.levelValue = levelValue;
        this.levelName = levelName;
        this.alerts = alerts;
        this.tanque = tanque;
        tanque.addSensor(this);
    }

    public String getLevelName() {  //Getter de levelName.
        return levelName;
    }

    public void addAlert(Alert newAlert) {  //Método que añade una alerta a la lista de alertas.
        alerts.add(newAlert);
    }

    public Tank getTanqueSensor() { //Getter del tanque.
        return tanque;
    }

    public void updateValue(double newValue) {  //Update del valor del parámetro.
        this.levelValue = newValue;

        for (Alert alert : alerts) {
            alert.update(levelValue);
        }
    }
}