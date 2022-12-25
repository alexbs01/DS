package e2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActuadoresTest {

    //Creo un tanque.
    String tankName = "Piscina de las focas";
    String tankLocation = "Exterior";
    List<Sensor> tankSensors = new ArrayList<>();
    Tank tank1 = new Tank(tankName, tankLocation, tankSensors);

    //Creo una lista de alertas.
    List<Alert> alertsSensor1 = new ArrayList<>();

    //Creo un sensor y lo asocio al tanque anterior y una lista de alertas creada anteriormente.
    Sensor sensor1 = new Sensor(1000, "oxigeno focas", alertsSensor1, tank1);

    //Creo la alerta que controlara el oxigeno.
    Alert alert1 = new Alert("Mantenimiento Focas", 500, 2000, 0, 5000, sensor1);

    //Introduzco el actuador en la lista de observers.
    Actuadores actuadores = new Actuadores("Los actuadores no se activan.", alert1);

    @Test
    void actuadoresTest() { //Test de Actuadores con los datos del enunciado.

        sensor1.updateValue(1000);
        assertEquals("Los actuadores no se activan.", actuadores.getInfo());

        sensor1.updateValue(3000);
        assertEquals("Los actuadores se activan por alerta naranja.", actuadores.getInfo());

        sensor1.updateValue(4000);
        assertEquals("Los actuadores se activan por alerta naranja.", actuadores.getInfo());

        sensor1.updateValue(-1000);
        assertEquals("Los actuadores se activan por alerta rojo.", actuadores.getInfo());
    }
}