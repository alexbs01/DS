package e3;

import java.lang.Math;

public class MfaFourLetters implements MfaStrategy{

    @Override
    public String generateCode() { // Genera un código de 4 letras
        StringBuilder code = new StringBuilder();

        // El 65 en el código ASCII se corresponde con la 'A' y el 90 con la 'Z'
        int minimum = 65, maximum = 90;

        for(int i = 0; i < 4; i++) { // Se genera un número aleatorio entre 65 y 90 y se pasa a char (Su caracter asociado)
            code.append((char) (Math.random() * (maximum - minimum + 1) + minimum));
        }

        return code.toString();
    }
}
