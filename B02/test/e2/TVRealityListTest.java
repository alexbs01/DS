package e2;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TVRealityListTest {
    final private List<String> listaConcursantes1 = new ArrayList<>();
    final private List<String> listaConcursantes2 = new ArrayList<>();
    final private List<String> listaConcursantes3 = new ArrayList<>();
    final private List<String> listaConcursantes4 = new ArrayList<>();

    final private List<String> listaConcursantes5 = new ArrayList<>();
    final private List<String> listaConcursantes6 = new ArrayList<>();

    final private List<String> listaConcursantes7 = new ArrayList<>();
    final private List<String> listaConcursantes8 = new ArrayList<>();
    Candidates<String> candidates = new Candidates<>();
    void insertarConcursantes() {
        listaConcursantes1.add("concursante1");
        listaConcursantes1.add("concursante2");
        listaConcursantes1.add("concursante3");
        listaConcursantes1.add("concursante4");
        listaConcursantes1.add("concursante5");

        listaConcursantes2.add("concursante1");
        listaConcursantes2.add("concursante2");
        listaConcursantes2.add("concursante3");
        listaConcursantes2.add("concursante4");
        listaConcursantes2.add("concursante5");

        listaConcursantes3.add("concursante1");
        listaConcursantes3.add("concursante2");

        listaConcursantes4.add("concursante1");
        listaConcursantes4.add("concursante2");

        listaConcursantes5.add("concursante1");
        listaConcursantes5.add("concursante2");
        listaConcursantes5.add("concursante3");
        listaConcursantes5.add("concursante4");
        listaConcursantes5.add("concursante5");

        listaConcursantes6.add("concursante1");
        listaConcursantes6.add("concursante2");
        listaConcursantes6.add("concursante3");
        listaConcursantes6.add("concursante4");
        listaConcursantes6.add("concursante5");

        listaConcursantes7.add("concursante1");
        listaConcursantes7.add("concursante2");
        listaConcursantes7.add("concursante3");

        listaConcursantes8.add("concursante1");
        listaConcursantes8.add("concursante2");
        listaConcursantes8.add("concursante3");
    }

    TVRealityList<String> primerConcurso = new TVRealityList<>(listaConcursantes1, TVRealityList.Recorrido.circular);
    TVRealityList<String> segundoConcurso = new TVRealityList<>(listaConcursantes2, TVRealityList.Recorrido.rebote);

    TVRealityList<String> tercerConcurso = new TVRealityList<>(listaConcursantes3, TVRealityList.Recorrido.circular);
    TVRealityList<String> cuartoConcurso = new TVRealityList<>(listaConcursantes4, TVRealityList.Recorrido.rebote);

    TVRealityList<String> quintoConcurso = new TVRealityList<>(listaConcursantes5, TVRealityList.Recorrido.circular);
    TVRealityList<String> sextoConcurso = new TVRealityList<>(listaConcursantes6, TVRealityList.Recorrido.rebote);

    TVRealityList<String> septimoConcurso = new TVRealityList<>(listaConcursantes7, TVRealityList.Recorrido.circular);
    TVRealityList<String> octavoConcurso = new TVRealityList<>(listaConcursantes8, TVRealityList.Recorrido.rebote);

    @Test
    void getConcursante() {
        insertarConcursantes();

        assertEquals("concursante4", candidates.selectCandidates(primerConcurso, 3));
        assertEquals("concursante1", candidates.selectCandidates(segundoConcurso, 3));

        assertEquals("concursante1", candidates.selectCandidates(tercerConcurso, 2));
        assertEquals("concursante1", candidates.selectCandidates(cuartoConcurso, 2));

        assertEquals("concursante4", candidates.selectCandidates(quintoConcurso, 7));
        assertEquals("concursante1", candidates.selectCandidates(sextoConcurso, 7));

        assertEquals("concursante3", candidates.selectCandidates(septimoConcurso, 2));
        assertEquals("concursante3", candidates.selectCandidates(octavoConcurso, 2));

    }
}