package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtDobladoresTest {
    private static final int precioPorHora = 20;
    private static final int horasDeDobl1 = 30, horasDeDobl2 = 20;

    ArtDobladores dobl1 = new ArtDobladores("Camilo", "Sesto", "77777777P", "789465300", "Española", horasDeDobl1);
    ArtDobladores dobl2 = new ArtDobladores("Jose Luis", "Perales", "88888888Q", "003154789", "Española", horasDeDobl2);

    @Test
    void getSalary() {
        assertEquals(dobl1.getWorkedHours() * precioPorHora, dobl1.getSalary());
        assertEquals(dobl2.getWorkedHours() * precioPorHora, dobl2.getSalary());
    }

    @Test
    void printSalary() {
        assertEquals("Camilo Sesto (Dubber): 600.0 euro\n", dobl1.printSalary());
        assertEquals("Jose Luis Perales (Dubber): 400.0 euro\n", dobl2.printSalary());
    }
}