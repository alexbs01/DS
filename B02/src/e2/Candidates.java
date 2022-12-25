package e2;

import java.util.Iterator;

public class Candidates<T> {

    public Candidates() {}

    /**
     * A partir de un iterador con una lista de concursantes con un tipo de desplazamiento y
     * un número de saltos, indica cuál de todos los concursantes es el ganador
     *
     * @param lista - Lista de los candidatos
     * @param k - Número de saltos entre eliminación y eliminación
     * @return Concursante ganador
     */
    public T selectCandidates(TVRealityList<T> lista, int k) {
        int i = 1;

        for(Iterator<T> j = lista.iterator(); j.hasNext() && lista.listaConcursantes.size() > 1; i++) {
            j.next();

            if(i % k == 0) {
                j.remove();
            }
        }

        return lista.listaConcursantes.get(0);
    }
}
