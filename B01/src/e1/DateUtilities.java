package e1;

import java.util.HashMap;

public class DateUtilities {
    /**
     * Indicates whether a year is a leap year . A leap year is divisible by 4,
     * unless it is divisible by 100 , in which case it must be divisible by 400
     * in order to be considered a leap year (e.g., 1900 is not a leap year ,
     * but 2000 is) = > See the JUnit seminar for an example .
     *
     * @param year the given year
     * @return True if the given year is a leap year , false otherwise .
     */

    public static boolean isLeap(int year) {
        /* Un año es bisiesto si es divisible entre 4, excepto si es divisible entre 100,
         * pero si es divisible entre 400 si lo será */

        boolean isALeapYear = false; // Declaramos un boolean que almacenará el resultado para la salida

        // Comprobamos si es bisiesto con condicionales
        if((year % 4) == 0) {
            if((year % 100) != 0 || (year % 400) == 0) {
                isALeapYear = true;
            }
        }

        return isALeapYear;
    }

    /**
     * Indicates the number of days of a given month . As the number of days in
     * the month of February depends on the year , it is also necessary to pass
     * the year as an argument .
     *
     * @param month The given month
     * @param year  The given year
     * @return The number of days of that month in that year .
     * @throws IllegalArgumentException if the month is not valid .
     */
    public static int numberOfDays(int month, int year) {
        // Retorna el número de días del mes introducido, valorando también si es bisiesto o no

        int numberOfDays; // Inicializamos la variable que guardará el número de días del mes

        switch(month) { //Según el mes, se le asigna el número de días a numberOfDays
            case 1, 3, 5, 7, 8, 10, 12:
                numberOfDays = 31;
                break;

            case 4, 6, 9, 11:
                numberOfDays = 30;
                break;

            case 2:
                /* Como febrero es especial, se asigna un número de días en función de si es bisiesto
                 * o no, utilizando la función anteriormente programada*/
                if(isLeap(year)) {
                    numberOfDays = 29;
                } else {
                    numberOfDays = 28;
                }
                break;

            default:
                //// Aquí va una exception
                throw new IllegalArgumentException("Mes debe estar entre 1 y 12");
        }

        return numberOfDays;
    }

    /**
     * The ISO date format is a standard format that displays the dates
     * starting with the year , followed by the month and the day , i.e. ,
     * "YYYY -MM -DD ". For example , in that format the text " July 28 , 2006"
     * would be represented as "2006 -07 -28".
     * The " convertToISO " method converts a date in the " Month DD , AAAA "
     * format to its ISO representation . For simplicity , let us assume that
     * the values are correctly formatted even if the date is invalid
     * (e.g., " February 31 , 2006" is correctly formatted but it is not a valid date )
     *
     * @param dateText Date in textual format ( USA style ).
     * @return A string with the given date in ISO format .
     */

    public static String convertToISODate(String dateText) {
        HashMap<String, String> month = new HashMap<>(); // <String, String>

        month.put("January", "01"); // Asignamos a cada mes su numero correspondiente
        month.put("February", "02");
        month.put("March", "03");
        month.put("April", "04");
        month.put("May", "05");
        month.put("June", "06");
        month.put("July", "07");
        month.put("August", "08");
        month.put("September", "09");
        month.put("October", "10");
        month.put("November", "11");
        month.put("December", "12");

        String[] slices = dateText.split(" "); //regex = regular expressions
        slices[1] = slices[1].replace(",", ""); // Elimina la coma que está en el String del mes
        return (slices[2] + "-" + month.get(slices[0]) + "-" + slices[1]); // String ISODate = slices[3] + "-" + month.get(slices[0]) + "-" + slices[1];

        //return ISODate;
    }

    /**
     * Given a String representing an ISO - formatted date , the methods checks
     * its validity . This includes checking for non - valid characters , erroneous
     * string lengths , and the validity of the date itself (i.e. , checking the
     * number of days of the month ).
     *
     * @param ISODate A date in ISO format
     * @return True if the ISO - formatted date is a valid date , False otherwise .
     */
    public static boolean checkISODate(String ISODate) {
        String[] slices = ISODate.split("-"); // Corta el string por los guiones

        // Se usará para checkear que los tres campos de una fecha están dentro de los parámetros establecidos
        boolean validity = false;

        if(slices.length != 3) { // Nos aseguramos de que la fecha en formato ISO tengo solo los tres campos
            return false;
        }

        int[] numbers = new int[slices.length];

        try { // Se transforma el String en en 3 números
            for(int i = 0; i < slices.length; i++) {
                numbers[i] = Integer.parseInt(slices[i]); // Casteamos el string de números, en números enteros
            }
        }
        catch(NumberFormatException ex) {
            ex.printStackTrace();
            return false;
        }

        // Checkea que los años, meses y días son correctos
        if((numbers[0] > 0) && (numbers[0] < 10000)) { // numbers[0] son los años
            if((numbers[1] > 0) && (numbers[1] < 13)) { // numbers[1] son los meses
                if((numbers[2] > 0) && (numbers[2] <= numberOfDays(numbers[1], numbers[0]))) { // numbers[2] los días
                    validity = true;
                }
            }
        }

        return validity;
    }
}
