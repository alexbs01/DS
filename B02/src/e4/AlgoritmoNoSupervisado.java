package e4;

public class AlgoritmoNoSupervisado extends Algoritmos{

    tipoNoSupervisado tipo;

    public AlgoritmoNoSupervisado(String nombre, Ubicacion ubicacion, String libreria) {
        super(nombre, ubicacion, libreria);
    }

    enum tipoNoSupervisado {
        clustering, asociacion
    }
}
