package pruebas;
class Pruebas {
    private int a, b; // Declaración de atributos
    public Pruebas() { // Método constructor, debe llamarse igual que la clase
        a = 5;
        b = 10;
    }

    public int suma() {
        return a + b;
    }

    public static void main(String[] args) {
        Pruebas sumaO = new Pruebas();
        System.out.println(sumaO.suma());
    }
}