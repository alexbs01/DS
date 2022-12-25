package e3;

import java.util.Map;

public class NombreDeUsuario extends Login implements LoginStrategy {

    public NombreDeUsuario(Map<String, String> inicioSesion) {
        super(inicioSesion);
    }

    @Override
    public boolean validateId(String userName) {
        return !(userName.contains(".") || userName.contains("-") || userName.matches("\\b[0-9]{9}\\b"));
    }

    @Override
    public boolean userIsAuthenticated(String userName, String password) {
        // Si es userName válido && Las contraseñas del mapa coinciden -> True
        boolean isAnUserAuthenticated = validateId(userName) && checkPasswd(userName, password);

        // Si el usuario y la contraseña son correctos se genera un segundo factor de autenticación
        if(isAnUserAuthenticated) {
            MfaFourLetters mfaFourLetters = new MfaFourLetters();
            mfaFourLetters.generateCode();
        }

        return isAnUserAuthenticated;
    }
}
