package e2;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Tipo de iterador en el cual, una vez se llega al final de la lista de
 * concursantes, vuelve al principio para seguir contando
 * @param <E> Iterador genérico
 */
public class RecorridoCircular<E> implements Iterator<E> {
    List<E> lista;
    int iterador = 0, preiterador = 0;
    boolean hiceNext = false;

    public RecorridoCircular(List<E> lista) {
        this.lista = lista;
    }

    @Override
    public boolean hasNext() {
        return (!lista.isEmpty());
    }

    @Override
    public E next() {
        if(!hasNext()) {
            throw new NoSuchElementException();

        }

        preiterador = iterador;

        if(iterador == lista.size() - 1) {
            iterador = 0;

        } else {
            iterador++;

        }

        hiceNext = true;

        return lista.get(iterador);
    }

    @Override
    public void remove() {
        if(hiceNext) {
            lista.remove(preiterador);
            hiceNext = false;

            if(iterador != 0) {
                iterador--;
            }

            preiterador = iterador;

        } else {
            throw new IllegalStateException();
        }
    }
}
