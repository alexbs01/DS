package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TecProductoresTest {
    private static final int precioPorHora = 90;
    private static final int horasDeProd1 = 145, horasDeProd2 = 173;

    TecProductores prod1 = new TecProductores("Santiago", "Segura", "56001369N", "986412365", "Española", horasDeProd1);
    TecProductores prod2 = new TecProductores("José", "Mota", "39669890Q", "789365103", "Española", horasDeProd2);

    @Test
    void getSalary() {
        assertEquals(precioPorHora * prod1.getWorkedHours(), prod1.getSalary());
        assertEquals(precioPorHora * prod2.getWorkedHours(), prod2.getSalary());
    }

    @Test
    void printSalary() {
        assertEquals("Santiago Segura (Producer): 13050.0 euro\n", prod1.printSalary());
        assertEquals("José Mota (Producer): 15570.0 euro\n", prod2.printSalary());
    }

}