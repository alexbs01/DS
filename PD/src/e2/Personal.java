package e2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Personal implements Observer {
    private final String personalName;  //Nombre del personal.
    private final List<String> orangeAlerts;    //Lista para alertas naranjas.
    private final List<String> redAlerts;   //Listas para alertas rojas.

    public Personal(String personalName, List<String> orangeAlerts, List<String> redAlerts, Alert alerta) {   //Constructor del personal.
        this.personalName = personalName;
        this.orangeAlerts = orangeAlerts;
        this.redAlerts = redAlerts;
        alerta.attach(this);
    }

    public void addOrangeAlert(String newOrangeAlert) {   //Método para añadir a la lista de laertas naranjas.
        orangeAlerts.add(newOrangeAlert);
    }

    public void addRedAlert(String newRedAlert) {   //Método para añadir a la lista de laertas rojas.
        redAlerts.add(newRedAlert);
    }

    public void deleteLists() {   //Método para borrar toda la lista de alertas rojas y alertas naranjas.
        redAlerts.removeAll(redAlerts);
        orangeAlerts.removeAll(orangeAlerts);
    }

    public String informe() {   //Método para crear el informe a partir de los datos guardados en sus respectivas listas.
        String redList = (""), orangeList = ("");

        for(String redPos :redAlerts) {
            redList = redList + redPos;
        }
        for(String orangePos :orangeAlerts) {
            orangeList = orangeList + orangePos;
        }

        if (redAlerts.isEmpty() && orangeAlerts.isEmpty()) {
            return ("Informe vacío, el personal no necesita trabajar.");

        } else if (redAlerts.isEmpty()) {
            return ("Alertas de " + personalName + "\n" +
                    "Alertas NARANJAS:\n" +
                    orangeList);

        } else if (orangeAlerts.isEmpty()) {
            return ("Alertas de " + personalName + "\n" +
                    "Alertas ROJAS:\n" +
                    redList);

        } else {
            return ("Alertas de " + personalName + "\n" +
                    "Alertas ROJAS:\n" +
                    redList +
                    "Alertas NARANJAS:\n" +
                    orangeList);
        }
    }

    @Override
    public void update(double levelValue, int alertType, double newValue, Sensor sensor, Alert Alerta) {    //Update para Personal que añade a la lista datos para el informe.
        if(alertType == 0){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            addOrangeAlert("*\tAlerta NARANJA:\n\t" +
                    sensor.getTanqueSensor().getTankName() + ", " + sensor.getTanqueSensor().getTankLocation() +
                    "\n\tControl de "+ Alerta.getNameAlert() + ": parámetro "+ sensor.getLevelName() + ", nivel " + levelValue +
                    "\n\t" + dateFormat.format(Calendar.getInstance().getTime()) + "\n\n");
        } else if(alertType == 1) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            addRedAlert("*\tAlerta ROJA:\n\t" +
                    sensor.getTanqueSensor().getTankName() + ", " + sensor.getTanqueSensor().getTankLocation() +
                    "\n\tControl de "+ Alerta.getNameAlert() + ": parámetro "+ sensor.getLevelName() + ", nivel " + levelValue +
                    "\n\t" + dateFormat.format(Calendar.getInstance().getTime()) + "\n\n");
        }
    }
}