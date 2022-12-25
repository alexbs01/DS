package e4;

public class Algoritmos{

    public enum Ubicacion {
        ESPAÑA(190),
        PORTUGAL(201),
        FRANCIA(55),
        ALEMANIA(301)
    }

    public Ubicacion ubicacion;
    public String nombre, libreria;
    public Algoritmos(String nombre, Ubicacion ubicacion, String libreria) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.libreria = libreria;
    }


}
