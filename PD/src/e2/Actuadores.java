package e2;

public class Actuadores implements Observer {
    public String info; //Info donde guardare que tipo de actuadores se activaron.

    public Actuadores(String info, Alert alerta) {  //Constructor de Actuadores
        this.info = info;
        alerta.attach(this);
    }

    public void trabajoActuadores(int alertType, double newValue, Sensor sensor) {  //Método que actualiza el valor del parametro del Sensor relacionado.
        if(alertType == 0) {                                                        //y que guarda en info que actuador se uso.
            sensor.updateValue(newValue);
            info = ("Los actuadores se activan por alerta naranja.");
        } else if(alertType == 1) {
            sensor.updateValue(newValue);
            info = ("Los actuadores se activan por alerta rojo.");
        }else {
            info = ("Los actuadores no se activan.");
        }
    }

    public String getInfo() {   //Getter de info.
        return info;
    }

    @Override
    public void update(double levelValue, int alertType, double newValue, Sensor sensor, Alert Alerta) {    //update de Actuadores que llama a trabajoActuadores.
        trabajoActuadores(alertType, newValue, sensor);
    }
}