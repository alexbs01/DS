package e3;

import java.lang.Math;

public class MfaTwoDigits implements MfaStrategy {

    @Override
    public String generateCode() { // Genera un código de 2 dígitos
        int minimum = 10, maximum = 99;
        int number = (int) (Math.random() * (maximum - minimum + 1) + minimum);

        return Integer.toString(number); // Transforma en integer en un String
    }
}
