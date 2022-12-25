package e2;

import java.util.List;

public class Tank {
    private final String tankName;  //Nombre del tanque.
    private final String tankLocation;  //Localización del tanque.
    private final List<Sensor> sensors; //Lista de sensores.

    public Tank(String tankName, String tankLocation, List<Sensor> sensors) {   //Constructor de tanque.
        this.tankName = tankName;
        this.tankLocation = tankLocation;
        this.sensors = sensors;
    }

    public String getTankName() {   //Getter del nombre del tanque.
        return tankName;
    }

    public String getTankLocation() {   //Getter de la localización del tanque.
        return tankLocation;
    }

    public void addSensor(Sensor newSensor) {   //Método que añade un sensor a la lista de sensores
        sensors.add(newSensor);
    }
}