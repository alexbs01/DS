package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TecDirectoresTest {
    private static final int precioPorHora = 100;
    private static final int precioPorAnoExperiencia = 1000;
    private static final int experience1 = 3, experience2 = 15;
    private static final int horasDeDirect1 = 50, horasDeDirect2 = 130;

    TecDirectores direct1 = new TecDirectores("Ibai", "Llanos", "79853216P", "981456002", "Euskera", horasDeDirect1, experience1);
    TecDirectores direct2 = new TecDirectores("Dr.", "Strange", "46998031K", "666367696", "Multiverso", horasDeDirect2, experience2);

    @Test
    void getSalary() {
        assertEquals(precioPorHora * direct1.getWorkedHours() + precioPorAnoExperiencia * direct1.getExperience(), direct1.getSalary());
        assertEquals(precioPorHora * direct2.getWorkedHours() + precioPorAnoExperiencia * direct2.getExperience(), direct2.getSalary());
    }

    @Test
    void printSalary() {
        assertEquals("Ibai Llanos (Director, 3 years of experience): 8000.0 euro\n", direct1.printSalary());
        assertEquals("Dr. Strange (Director, 15 years of experience): 28000.0 euro\n", direct2.printSalary());
    }

}