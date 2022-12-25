package e2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonalTest {

    String tankName = "Piscina de las focas";   //Creo el nombre de un tanque.
    String tankLocation = "Exterior";   //Creo el nombre de la localización del tanque.
    List<Sensor> tankSensors = new ArrayList<>();   //Creo una lista de sensores.
    Tank tank1 = new Tank(tankName, tankLocation, tankSensors); //Creo un tanque con el nombre del tanque, nombre de la localización y la lista de sensores.
    List<Alert> alertsSensor1 = new ArrayList<>();  //Creo una lista de alertas.
    Sensor sensor1 = new Sensor(1000, "Oxígeno", alertsSensor1, tank1); //Creo un sensor y lo asocio al tanque anterior y una lista de alertas creada anteriormente.
    Alert alert1 = new Alert("oxígeno focas", 500, 2000, 0, 5000, sensor1); //Creo la alerta que controlara el oxigeno.
    List<String> orangeAlerts = new ArrayList<>();  //Creo una lista para las alertas rojas y otra para las naranjas.
    List<String> redAlerts = new ArrayList<>();
    Personal personal1 = new Personal("Mantenimiento Focas", orangeAlerts, redAlerts, alert1);  //Creo un personal.
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //Creo un formato para el formato de la fecha.

    @Test
    void personalTest() {   //Test de Personal con los datos del enunciado.
        sensor1.updateValue(1000); //Provoca ninguna alerta.
        assertEquals("Informe vacío, el personal no necesita trabajar.", personal1.informe());

        sensor1.updateValue(4000); //Provoca alerta naranja.
        assertLinesMatch(("""
                             Alertas de Mantenimiento Focas
                             Alertas NARANJAS:
                             *\tAlerta NARANJA:
                             \tPiscina de las focas, Exterior
                             \tControl de oxígeno focas: parámetro Oxígeno, nivel 4000.0
                             \t\\d{4}-([0]\\d|1[0-2])-([0-2]\\d|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]
                             
                             """).lines()
                , personal1.informe().lines());

        /*assertLinesMatch(("""
                * Payment order: número de productos del carrito, hora del pedido
                Ejemplo: Order Number: 1111
                Phase: Paid order: 6 products -- date \\d{4}-([0]\\d|1[0-2])-([0-2]\\d|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]
                """).lines(), order1.screenInfo(order1).lines());*/

        sensor1.updateValue(3000); //Provoca alerta naranja.
        assertLinesMatch(("""
                                     Alertas de Mantenimiento Focas
                                     Alertas NARANJAS:
                                     *\tAlerta NARANJA:
                                     \tPiscina de las focas, Exterior
                                     \tControl de oxígeno focas: parámetro Oxígeno, nivel 4000.0
                                     \t\\d{4}-([0]\\d|1[0-2])-([0-2]\\d|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]
                                     
                                     *\tAlerta NARANJA:
                                     \tPiscina de las focas, Exterior
                                     \tControl de oxígeno focas: parámetro Oxígeno, nivel 3000.0
                                     \t\\d{4}-([0]\\d|1[0-2])-([0-2]\\d|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]
                                     
                                     """).lines()
                , personal1.informe().lines());

        sensor1.updateValue(-1000); //Provoca alerta roja.
        assertLinesMatch("""
                             Alertas de Mantenimiento Focas
                             Alertas ROJAS:
                             *\tAlerta ROJA:
                             \tPiscina de las focas, Exterior
                             \tControl de oxígeno focas: parámetro Oxígeno, nivel -1000.0
                             \t\\d{4}-([0]\\d|1[0-2])-([0-2]\\d|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]
                             
                             Alertas NARANJAS:
                             *\tAlerta NARANJA:
                             \tPiscina de las focas, Exterior
                             \tControl de oxígeno focas: parámetro Oxígeno, nivel 4000.0
                             \t\\d{4}-([0]\\d|1[0-2])-([0-2]\\d|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]
                             
                             *\tAlerta NARANJA:
                             \tPiscina de las focas, Exterior
                             \tControl de oxígeno focas: parámetro Oxígeno, nivel 3000.0
                             \t\\d{4}-([0]\\d|1[0-2])-([0-2]\\d|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]
                             
                             """.lines()
                , personal1.informe().lines());


        personal1.deleteLists();
        sensor1.updateValue(-1000); //Provoca alerta roja.
        assertLinesMatch(("""
                                     Alertas de Mantenimiento Focas
                                     Alertas ROJAS:
                                     *\tAlerta ROJA:
                                     \tPiscina de las focas, Exterior
                                     \tControl de oxígeno focas: parámetro Oxígeno, nivel -1000.0
                                     \t\\d{4}-([0]\\d|1[0-2])-([0-2]\\d|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]
                                     
                                     """).lines()
                , personal1.informe().lines());
    }
}
