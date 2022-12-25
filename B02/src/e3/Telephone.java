package e3;

import java.util.Map;

public class Telephone extends Login implements LoginStrategy {
    public Telephone(Map<String, String> inicioSesion) {
        super(inicioSesion);
    }

    @Override
    public boolean validateId(String userName) {
        // Si el nombre de usuario solo son 9 dígitos -> True
        return userName.matches("\\b[0-9]{9}\\b");
    }

    @Override
    public boolean userIsAuthenticated(String userName, String password) {
        // Si es userName válido && Las contraseñas del mapa coinciden -> True
        boolean isAnUserAuthenticated = validateId(userName) && checkPasswd(userName, password);

        // Genera un número aleatorio entre 0 y 1, para decidir si mandar un código de 2 dígitos o 4 letras
        int minimum = 0, maximum = 1;
        int number = (int) (Math.random() * (maximum - minimum + 1) + minimum);

        // Si el usuario y la contraseña son correctos se genera un segundo factor de autenticación
        if(isAnUserAuthenticated && number == 0) {
            MfaTwoDigits mfaTwoDigits = new MfaTwoDigits();
            mfaTwoDigits.generateCode();

        } else if(isAnUserAuthenticated && number == 1) {
            MfaFourLetters mfaFourLetters = new MfaFourLetters();
            mfaFourLetters.generateCode();
        }

        return isAnUserAuthenticated;
    }

}
