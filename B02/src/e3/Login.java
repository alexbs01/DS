package e3;

import java.util.*;

public class Login {

    Map<String, String> inicioSesion;

    public Login(Map<String, String> inicioSesion) {
        this.inicioSesion = inicioSesion;
    }

    /**
     * Comprueba si el userName está en el mapa de usuarios del login, si lo está comprueba
     * si la contraseña guardada en el mapa es igual a la introducida por el usuario para autenticarse.
     *
     * @param userName Nombre de usuario introducido
     * @param password Contraseña introducida para el usuario
     * @return True si la contraseña y el usuario introducidos se corresponde con los almacenados
     */
    public boolean checkPasswd(String userName, String password) {
        // Si el usuario existe en el mapa && La contraseña del mapa coincide con la introducida -> True
        return this.inicioSesion.containsKey(userName) && Objects.equals(this.inicioSesion.get(userName), password);
    }

    /**
     * En función del idPreferido se escoge con que se va a inciar sesión el usuario,
     * si por email o por número telefónico. Si el usuario se puede autenticar,
     * se generará un código para el segundo factor de autenticación, en caso de que
     * sea un email se generará un código de 6 dígitos, si es por teléfono será
     * un código de 2 dígitos o 4 letras.
     *
     * @param userName Nombre de usuario introducido por el usuario
     * @param password Contraseña introducida por el usuario
     * @param idPreferido Tipo de identificación elegido
     * @return True si el usuario logra autenticarse, False en caso contrario
     */
    public boolean authenticateUser(String userName, String password, IdPreferido idPreferido) {
        if(idPreferido == IdPreferido.EMAIL) {
            Email email = new Email(inicioSesion);
            return email.userIsAuthenticated(userName, password);

        } else if(idPreferido == IdPreferido.TELEPHONE) {
            Telephone telephone = new Telephone(inicioSesion);
            return telephone.userIsAuthenticated(userName, password);

        } else if(idPreferido == IdPreferido.NOMBRE_USUARIO) {
            NombreDeUsuario nombreDeUsuario = new NombreDeUsuario(inicioSesion);
            return  nombreDeUsuario.userIsAuthenticated(userName, password);

        } else {
            return false;
        }
    }

}
