package e4;

public class AlgoritmoSupervisado extends Algoritmos{

    tipoSupervisado tipo;

    public AlgoritmoSupervisado(String nombre, Ubicacion ubicacion, String libreria) {
        super(nombre, ubicacion, libreria);
    }

    enum tipoSupervisado {
        clasificacion, regresion
    }

}

