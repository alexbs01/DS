package e3;

import java.util.Arrays;

public record Triangle(int angle0, int angle1, int angle2) {

    // Crea el objeto triángulo, si sus ángulos suman más de 180 grados, retorna un error
    public Triangle {
        if((angle0 + angle1 + angle2) != 180) {
            throw new IllegalArgumentException("Angles do not sum 180 degrees");
        }
    }

    // Crea el triángulo con valores por defecto
    public Triangle(Triangle t) {
        this(t.angle0, t.angle1, t.angle2);
    }

    // Comprueba si es un triángulo rectángulo
    public boolean isRight() {
        return (angle0 == 90) || (angle1 == 90) || (angle2 == 90);
    }

    // Si los 3 ángulos son agudos, será un triángulo acutángulo
    public boolean isAcute() {
        return (angle0 < 90) && (angle1 < 90) && (angle2 < 90);
    }

    // Si uno de los ángulos es obtuso, será obtusángulo
    public boolean isObtuse() {
        return (angle0 > 90) || (angle1 > 90) || (angle2 > 90);
    }

    // Si los 3 ángulos son iguales, será un triángulo equilátero
    public boolean isEquilateral() {
        return angle0 == angle1 && angle0 == angle2;
    }


    // Si dós ángulos son iguales, es que es isosceles
    public boolean isIsosceles() {
        boolean angle0_angle1 = (angle0 == angle1 && angle0 != angle2);
        boolean angle0_angle2 = (angle0 == angle2 && angle0 != angle1);
        boolean angle1_angle2 = (angle1 == angle2 && angle1 != angle0);

        return angle0_angle1 || angle0_angle2 || angle1_angle2;
    }

    // Si todos los ángulos son distintos, quiere decir que es escaleno
    public boolean isScalene() {
        return (angle0 != angle1) && (angle0 != angle2) && (angle1 != angle2);
    }

    /**
     * Tests if two triangles are equal .
     * Two triangles are equal if their angles are the same ,
     * regardless of the order .
     * @param o The reference object with which to compare .
     * @return True if they are equal , false otherwise .
     */
    @Override
    public boolean equals(Object o) { // Coprueba si los dos hashcodes son iguales
        int hash0 = o.hashCode();
        int hash1 = this.hashCode();

        return hash0 == hash1;
    }

    /**
     * Hashcode function whose functioning is consistent with equals .
     * Two triangles have the same hashcode if their angles are the same ,
     * regardless of the order .
     * @return A value that represents the hashcode of the triangle .
     */
    @Override
    public int hashCode() { // Calcula el hashcode de un trángulo dado
        int hash = 1;
        int [] arrayAngles = {angle0, angle1, angle2}; // Creamos un array con los ángulos

        Arrays.sort(arrayAngles); // Ordenamos los elementos de menor a mayor

        // Creamos el hash para los ángulos introducidos
        hash = hash * 31 + arrayAngles[0];
        hash = hash * 31 + arrayAngles[1];
        hash = hash * 31 + arrayAngles[2];

        return hash;
    }
}
