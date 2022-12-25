package e1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TecMusicosTest {
    private static final int precioPorHora = 60;
    private static final int horasDeMusic1 = 150, horasDeMusic2 = 197;

    TecMusicos music1 = new TecMusicos("Kurt", "Cobain", "59663217B", "412654789", "Estadounidense", horasDeMusic1);
    TecMusicos music2 = new TecMusicos("Txus", "Di Fellatio", "39669890Q", "635786412", "Euskera", horasDeMusic2);

    @Test
    void getSalary() {
        assertEquals(precioPorHora * music1.getWorkedHours(), music1.getSalary());
        assertEquals(precioPorHora * music2.getWorkedHours(), music2.getSalary());
    }

    @Test
    void printSalary() {
        assertEquals("Kurt Cobain (Musician): 9000.0 euro\n", music1.printSalary());
        assertEquals("Txus Di Fellatio (Musician): 11820.0 euro\n", music2.printSalary());
    }

}