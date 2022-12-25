package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtInterpretesTest {
    private static final int precioPorHora = 200;
    private static final int horasDeInterp1 = 130, horasDeInterp2 = 80, horasDeInterp3 = 50;

    ArtInterpretes interp1 = new ArtInterpretes("Silvester", "Stalone", "44444444D", "951753654", "Estadounidense", horasDeInterp1, ArtInterpretes.roles.principal);
    ArtInterpretes interp2 = new ArtInterpretes("Ka Chin", "Ka Chan", "55555555E", "167943248", "China", horasDeInterp2, ArtInterpretes.roles.secundario);
    ArtInterpretes interp3 = new ArtInterpretes("Nikito", "Nitoko", "66666666F", "284673915", "Japonesa", horasDeInterp3, ArtInterpretes.roles.extra);

    @Test
    void getSalary() {
        assertEquals(horasDeInterp1 * precioPorHora * 3, interp1.getSalary());
        assertEquals(horasDeInterp2 * precioPorHora, interp2.getSalary());
        assertEquals(horasDeInterp3 * precioPorHora, interp3.getSalary());
    }

    @Test
    void printSalary() {
        assertEquals("Silvester Stalone (Actor protagonist): 78000.0 euro\n", interp1.printSalary());
        assertEquals("Ka Chin Ka Chan (Actor secondary): 16000.0 euro\n", interp2.printSalary());
        assertEquals("Nikito Nitoko (Extra): 10000.0 euro\n", interp3.printSalary());
    }
}