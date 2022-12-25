package e3;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public interface LoginStrategy {
    /**
     * Comprueba que el userName ingresado siga los requisitos para el email o el teléfono
     * @param userName
     * @return True si el usuario contiene un @ en un email, o si solo tiene 9 números en un telefónico
     */
    boolean validateId(String userName);

    //boolean checkPasswd(String userName, String password);

    /**
     * Comprueba si el userName introducido es válido para la estrategia de login preferida,
     * luego comprueba que esete nombre de usuario esté guardado en los login, y después que
     * si está, sea la misma contraseña que la que se introdujo.
     *
     * @param userName Identificador del usuario
     * @param password Contraseña del usuario
     * @return True si el usuario logró auntenticarse
     */
    boolean userIsAuthenticated(String userName, String password);
}
