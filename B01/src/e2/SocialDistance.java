package e2;

public class SocialDistance {
    /**
     * Given the layout of a class with available sites marked with an ’A’ and
     * invalid sites marked with a ’. ’, returns the resulting layout with the
     * sites occupied by the students marked with a ’#’ following two rules :
     * - Students occupy an empty seat if there are no other adjacent students .
     * - A student leaves a seat empty if he/ she has 4 or more adjacent students .
     * @param layout The initial layout .
     * @return The resulting layout .
     * @throws IllegalArgumentException if the initial layout is invalid (is null ,
     * is ragged , includes characters other than ’.’ or ’A ’)).
     */

    /* Comprueba si es una distribución válida, mirando si es rectángular, cuadrada o si tiene caracteres correctos,
     * también está cubierto para si no se introduce ningún aula */
    public static boolean isValidRoom(char [][] layout) {
        boolean isValidRoom = false;
        int boxesNumber = 0;

        if(layout == null) {
            return false;
        }

        for(int column = 0; column < layout.length; column++) {
            for(int row = 0; row < layout[column].length; row++) {
                boxesNumber++;
                if(layout[column][row] != '.' && layout[column][row] != 'A') {
                    return false;
                }
            }
        }
        if(boxesNumber == (layout.length * layout[0].length)) {
            isValidRoom = true;
        }
        return isValidRoom;
    }

    // Retorna true, cuando el número máximo de personas es superior a las contadas en sus cercanías
    public static boolean areTherePeopleNear(char [][] layout, int row, int column, int maxPeople) {
        boolean areTherePeopleNear = false;
        int count = 0;

        if(row == 0 && column == 0) { // Esquina superior izquierda
            count = (layout[row    ][column + 1] == '#')? count + 1 : count;
            count = (layout[row + 1][column + 1] == '#')? count + 1 : count;
            count = (layout[row + 1][column    ] == '#')? count + 1 : count;

        } else if(row == 0 && column == layout[row].length - 1) { // Esquina superior derecha
            count = (layout[row    ][column - 1] == '#')? count + 1 : count;
            count = (layout[row + 1][column - 1] == '#')? count + 1 : count;
            count = (layout[row + 1][column    ] == '#')? count + 1 : count;

        } else if(row == layout.length - 1 && column == 0) { // Esquina inferior izquierda
            count = (layout[row - 1][column    ] == '#')? count + 1 : count;
            count = (layout[row - 1][column + 1] == '#')? count + 1 : count;
            count = (layout[row    ][column + 1] == '#')? count + 1 : count;

        } else if(row == layout.length - 1 && column == layout[row].length - 1) { // Esquina inferior derecha
            count = (layout[row - 1][column    ] == '#')? count + 1 : count;
            count = (layout[row - 1][column - 1] == '#')? count + 1 : count;
            count = (layout[row    ][column - 1] == '#')? count + 1 : count;

        } else if(row == 0 && column != layout[row].length - 1) { // Lado superior
            count = (layout[row + 1][column - 1] == '#')? count + 1 : count;
            count = (layout[row + 1][column    ] == '#')? count + 1 : count;
            count = (layout[row + 1][column + 1] == '#')? count + 1 : count;
            count = (layout[row    ][column - 1] == '#')? count + 1 : count;
            count = (layout[row    ][column + 1] == '#')? count + 1 : count;

        } else if(row == layout.length - 1 && column != layout[row].length - 1) { // Lado inferior
            count = (layout[row - 1][column - 1] == '#')? count + 1 : count;
            count = (layout[row - 1][column    ] == '#')? count + 1 : count;
            count = (layout[row - 1][column + 1] == '#')? count + 1 : count;
            count = (layout[row    ][column - 1] == '#')? count + 1 : count;
            count = (layout[row    ][column + 1] == '#')? count + 1 : count;

        } else if(column == 0) { // Lado izquierdo
            count = (layout[row - 1][column + 1] == '#')? count + 1 : count;
            count = (layout[row    ][column + 1] == '#')? count + 1 : count;
            count = (layout[row + 1][column + 1] == '#')? count + 1 : count;
            count = (layout[row - 1][column    ] == '#')? count + 1 : count;
            count = (layout[row + 1][column    ] == '#')? count + 1 : count;

        } else if(column == layout[row].length - 1) { // Lado derecho
            count = (layout[row - 1][column - 1] == '#')? count + 1 : count;
            count = (layout[row    ][column - 1] == '#')? count + 1 : count;
            count = (layout[row + 1][column - 1] == '#')? count + 1 : count;
            count = (layout[row - 1][column    ] == '#')? count + 1 : count;
            count = (layout[row + 1][column    ] == '#')? count + 1 : count;

        } else { // Cualquier casilla que no está en un extremo del aula (Ni esquinas ni laterales)
            count = (layout[row    ][column + 1] == '#')? count + 1 : count;
            count = (layout[row + 1][column + 1] == '#')? count + 1 : count;
            count = (layout[row + 1][column    ] == '#')? count + 1 : count;
            count = (layout[row - 1][column    ] == '#')? count + 1 : count;
            count = (layout[row - 1][column - 1] == '#')? count + 1 : count;
            count = (layout[row    ][column - 1] == '#')? count + 1 : count;
            count = (layout[row + 1][column - 1] == '#')? count + 1 : count;
            count = (layout[row - 1][column + 1] == '#')? count + 1 : count;
        }

        if(count > maxPeople) {
            areTherePeopleNear = true;
        }

        return areTherePeopleNear; // Retornamos cuantos asientos ocupados hay alrededor
    }

    // Levanta a la gente a la persona cuando hay 4 o más personas cerca
    public static void standUpPeople(char [][] layout, int row, int column, char [][] previousLayout) {
        if(layout[row][column] == '#' && areTherePeopleNear(previousLayout, row, column, 3)) {
            layout[row][column] = 'A';
        }
    }

    // Sienta a la persona cuando no tiene a nadie cerca
    public static void sitDownPeople(char [][] layout, int row, int column, char [][] previousLayout) {
        if(layout[row][column] == 'A' && !areTherePeopleNear(previousLayout, row, column, 0)) {
            layout[row][column] = '#';
        }
    }

    // Comprueba si dos arrays son idénticos
    public static boolean areArrayEquals(char [][] array1, char [][] array2) {

        for(int row = 0; row < array1.length; row ++) {
            for(int column = 0; column < array1[row].length; column++) {
                if(array1[row][column] != array2[row][column]) {
                    return false;
                }
            }
        }

        return true;
    }

    // Clona el array1 en el array2
    public static void cloneArray(char [][] array1, char [][] array2) {
        for(int row = 0; row < array1.length; row++) {
            for(int column = 0; column < array1[row].length; column++) {
                array2[row][column] = array1[row][column];
            }
        }
    }

    // Comprueba los layouts unidimensionales
   public static boolean strangeLayouts(char [][] layout) {
        boolean isValidLayout = false;

        if(layout.length * layout[0].length == layout.length) {
            isValidLayout = true;
        } else if(layout.length * layout[0].length == layout[0].length) {
            isValidLayout = true;
        }

        return isValidLayout;
    }

    // Método principal de SocialDistance, sienta y levanta a las personas hasta que están correctamente sentadas
    public static char [][] seatingPeople (char [][] layout ) {
        if(!isValidRoom(layout)) { // Si no es valida la habitación, retornará un error
            throw new IllegalArgumentException("Mesas incorrectas");
        }

        char [][] previousLayout = new char[layout.length][layout[0].length];
        char [][] previousLayout2 = new char[layout.length][layout[0].length];

        for(int column = 0; column < layout[0].length; column++) {
            for(int row = 0; row < layout.length; row++) {
                if(layout[row][column] == 'A') {
                    layout[row][column] = '#';
                }
            }
        }

        if(!strangeLayouts(layout)) { // Si no es layout un extraño, se ejecuta el bucle
            do {
                cloneArray(layout, previousLayout);
                for(int column = 0; column < layout[0].length; column++) { // Levanta a la gente que está muy junta
                    for(int row = 0; row < layout.length; row++) {
                        standUpPeople(layout, row, column, previousLayout);
                    }
                }

                cloneArray(layout, previousLayout2);

                for(int column = 0; column < layout[0].length; column++) { // Sienta a la gente si puede
                    for(int row = 0; row < layout.length; row++) {
                        sitDownPeople(layout, row, column, previousLayout2);
                    }
                }

                /* Este ciclo se repetirá mientras haya gente que se pueda sentar o levantar. Si tras este
                * proceso de levantar y sentar gente, hay la misma gente que al principio, se cortará el bucle
                * y retornará el layout final*/
            } while(!areArrayEquals(layout, previousLayout));
        }

        return layout;
    }
}
