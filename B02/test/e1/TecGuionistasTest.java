package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TecGuionistasTest {
    private static final int precioPorHora = 70;
    private static final int precioPorOriginal = 4000;
    private static final boolean original1 = true, original2 = false;
    private static final int horasDeDirect1 = 7, horasDeDirect2 = 16;

    TecGuionistas guion1 = new TecGuionistas("Monkey D.", "Luffy", "15934482P", "468363045", "East Blue", horasDeDirect1, original1);
    TecGuionistas guion2 = new TecGuionistas("Clive", "Rosfield", "46998031K", "666367696", "Valisthea", horasDeDirect2, original2);

    @Test
    void getSalary() {
        assertEquals(precioPorHora * guion1.getWorkedHours() + precioPorOriginal , guion1.getSalary());
        assertEquals(precioPorHora * guion2.getWorkedHours(), guion2.getSalary());
    }

    @Test
    void printSalary() {
        assertEquals("Monkey D. Luffy (Screenwriter, original screenplay): 4490.0 euro\n", guion1.printSalary());
        assertEquals("Clive Rosfield (Screenwriter, non original screenplay): 1120.0 euro\n", guion2.printSalary());
    }

}