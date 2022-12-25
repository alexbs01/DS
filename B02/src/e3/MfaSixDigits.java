package e3;

import java.lang.Math;

public class MfaSixDigits implements MfaStrategy {

    @Override
    public String generateCode() { // Genera un número aleatorio de 6 cifras
        int minimum = 100000, maximum = 999999;
        int number = (int) (Math.random() * (maximum - minimum + 1) + minimum);

        return Integer.toString(number); // El integer se convierte a String
    }
}
