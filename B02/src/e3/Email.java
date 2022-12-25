package e3;

import java.util.Map;

public class Email extends Login implements LoginStrategy {
    public Email(Map<String, String> inicioSesion) {
        super(inicioSesion);
    }

    @Override
    public boolean validateId(String userName) {
        // Si el nombre de usuario contiene un @ -> True
        return userName.contains("@");
    }

    @Override
    public boolean userIsAuthenticated(String userName, String password) {
        // Si es userName válido && Las contraseñas del mapa coinciden -> True
        boolean isAnUserAuthenticated = validateId(userName) && checkPasswd(userName, password);

        // Si el usuario y la contraseña son correctos se genera un segundo factor de autenticación
        if(isAnUserAuthenticated) {
            MfaSixDigits mfaSixDigits = new MfaSixDigits();
            mfaSixDigits.generateCode();
        }

        return isAnUserAuthenticated;
    }

}
