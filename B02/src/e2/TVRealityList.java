package e2;

import java.util.Iterator;
import java.util.List;

public class TVRealityList<T> implements Iterable<T> {
    List<T> listaConcursantes;

    public enum Recorrido {
        circular, rebote
    }

    Recorrido tipoIterador;

    public TVRealityList(List<T> listaConcursantes, Recorrido tipoIterador) {
        this.tipoIterador = tipoIterador;
        this.listaConcursantes = listaConcursantes;
    }

    @Override
    public Iterator<T> iterator() {
        if(tipoIterador == Recorrido.circular) {
            return (new RecorridoCircular<>(listaConcursantes));

        } else {
            return (new RecorridoRebote<>(listaConcursantes));
        }
    }
}