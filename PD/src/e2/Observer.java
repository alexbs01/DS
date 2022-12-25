package e2;

public interface Observer { //Interfaz de Observer que en este programa se implementa en Actuadores y Personal.
    void update(double levelValue, int alertType, double newValue, Sensor sensor, Alert alerta);
}