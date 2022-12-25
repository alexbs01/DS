package e1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtEspecialistasTest {
    private static final int precioPorHora = 40;
    private static final int extraPorPeligrosidad = 1000;
    private static final int horasDeEspec1 = 30, horasDeEspec2 = 20;

    ArtEspecialistas espec1 = new ArtEspecialistas("Isabel", "II", "22222222B", "987654321", "Cielo", horasDeEspec1, true);
    ArtEspecialistas espec2 = new ArtEspecialistas("Illo", "Juan", "33333333C", "159753258", "Andalûh", horasDeEspec2, false);

    @Test
    void getSalary() {
        assertEquals(espec1.getWorkedHours() * precioPorHora + extraPorPeligrosidad, espec1.getSalary());
        assertEquals(espec2.getWorkedHours() * precioPorHora, espec2.getSalary());
    }

    @Test
    void printSalary() {
        assertEquals("Isabel II (Stunt performer with extra for danger): 2200.0 euro\n", espec1.printSalary());
        assertEquals("Illo Juan (Stunt performer without extra for danger): 800.0 euro\n", espec2.printSalary());
    }
}