package e1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PeliculaTest {
    private final List<TechTeam> trabajadores1Tech = new ArrayList<>();
    private final List<ArtisticTeam> trabajadores1Art = new ArrayList<>();
    private final List<TechTeam> trabajadores2Tech = new ArrayList<>();
    private final List<ArtisticTeam> trabajadores2Art = new ArrayList<>();

    TecProductores prod1 = new TecProductores("Santiago", "Segura", "56001369N", "986412365", "Española", 170);
    TecProductores prod2 = new TecProductores("José", "Mota", "32001497K", "789365103", "Española", 198);
    TecGuionistas guion1 = new TecGuionistas("Monkey", "D. Luffy", "15934482P", "468363045", "East Blue", 124, true);
    TecGuionistas guion2 = new TecGuionistas("Clive", "Rosfield", "46998031K", "666367696", "Valisthea", 194, false);
    TecMusicos music1 = new TecMusicos("Kurt", "Cobain", "59663217B", "412654789", "Estadounidense", 120);
    TecMusicos music2 = new TecMusicos("Txus", "Di Fellatio", "39669890Q", "635786412", "Euskera", 321);
    TecDirectores direct1 = new TecDirectores("Ibai", "Llanos", "79853216P", "981456002", "Euskera", 221, 10);
    TecDirectores direct2 = new TecDirectores("Dr.", "Strange", "46998031K", "666367696", "Multiverso", 320, 1);

    ArtDobladores dobl1 = new ArtDobladores("Camilo", "Sesto", "77777777P", "789465300", "Española", 187);
    ArtDobladores dobl2 = new ArtDobladores("Jose Luis", "Perales", "88888888Q", "003154789", "Española", 76);
    ArtEspecialistas espec1 = new ArtEspecialistas("Isabel", "II", "22222222B", "987654321", "Cielo", 156, true);
    ArtEspecialistas espec2 = new ArtEspecialistas("Illo", "Juan", "33333333C", "159753258", "Andalûh", 260, false);
    ArtInterpretes interp1 = new ArtInterpretes("Silvester", "Stalone", "44444444D", "951753654", "Estadounidense", 124, ArtInterpretes.roles.principal);
    ArtInterpretes interp21 = new ArtInterpretes("Ka Chin", "Ka Chan", "55555555E", "167943248", "China", 65, ArtInterpretes.roles.secundario);
    ArtInterpretes interp22 = new ArtInterpretes("Nikito", "Nitoko", "66666666F", "284673915", "Japonesa", 20, ArtInterpretes.roles.extra);

    /*Inserta los trabajadores en su lista correspondiente*/
    public void insertarTrabajadores() {
        trabajadores1Tech.add(prod1);
        trabajadores1Tech.add(guion1);
        trabajadores1Tech.add(direct1);
        trabajadores1Tech.add(music1);
        trabajadores1Art.add(dobl1);
        trabajadores1Art.add(espec1);
        trabajadores1Art.add(interp1);

        trabajadores2Tech.add(prod2);
        trabajadores2Tech.add(guion2);
        trabajadores2Tech.add(direct2);
        trabajadores2Tech.add(music2);
        trabajadores2Art.add(dobl2);
        trabajadores2Art.add(espec2);
        trabajadores2Art.add(interp21);
        trabajadores2Art.add(interp22);
    }

    Pelicula p1 = new Pelicula("Fantasía Final XVIII", 160000, trabajadores1Art, trabajadores1Tech);
    Pelicula p2 = new Pelicula("Las increíbles aventuras del chico de goma y su tripulación pirata", 90000000, trabajadores2Art, trabajadores2Tech);

    @Test
    void getRecaudacion() {
        assertEquals(160000, p1.getRecaudacion());
        assertEquals(90000000, p2.getRecaudacion());
    }
    @Test
    void printSalaries() {
        insertarTrabajadores();
        assertEquals("""
                Santiago Segura (Producer): 15300.0 euro
                Monkey D. Luffy (Screenwriter, original screenplay): 12680.0 euro
                Ibai Llanos (Director, 10 years of experience): 32100.0 euro
                Kurt Cobain (Musician): 7200.0 euro
                Camilo Sesto (Dubber): 3740.0 euro
                Isabel II (Stunt performer with extra for danger): 7240.0 euro
                Silvester Stalone (Actor protagonist): 74400.0 euro
                The total payroll for Fantasía Final XVIII is 152660.0 euro""", p1.printSalaries());
    }

    @Test
    void printRoyalties() {
        insertarTrabajadores();
        assertEquals("""
                Santiago Segura (Producer): 3200.0 euro
                Monkey D. Luffy (Screenwriter): 8000.0 euro
                Ibai Llanos (Director): 8000.0 euro
                Kurt Cobain (Musician): 6400.0 euro
                The total royalties for Fantasía Final XVIII is 25600.0 euro""", p1.printRoyalties());
    }

}