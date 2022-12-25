package e2;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Tipo de iterador en el cual, una vez se llega al final de la lista de
 * concursantes, vuelve al principio yendo en sentido contrario
 * @param <E> Iterador genérico
 */
public class RecorridoRebote<E> implements Iterator<E> {
    List<E> lista;
    int iterador = 0, preiterador = 0;
    boolean IzqDerch = true, hiceNext = false;
    public RecorridoRebote(List<E> lista) {
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

        if(IzqDerch && iterador == lista.size() - 1) {
            iterador = lista.size() - 2;
            IzqDerch = false;

        } else if (!IzqDerch && iterador == 0) {
            iterador = 1;
            IzqDerch = true;

        } else {
            if (IzqDerch) {
                iterador++;
            } else {
                iterador--;
            }
        }

        hiceNext = true;

        return lista.get(iterador);
    }

    @Override
    public void remove() {
        if(hiceNext) {
            lista.remove(preiterador);
            hiceNext = false;

            if(IzqDerch) {
                iterador--;
            }

            preiterador = iterador;

        } else {
            throw new IllegalStateException();
        }
    }
}
