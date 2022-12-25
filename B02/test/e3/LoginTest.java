package e3;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class LoginTest {
    HashMap<String, String> usuariosConLogin = new HashMap<>();

    Login logins = new Login(usuariosConLogin);

    void insertarUsuarios() {
        usuariosConLogin.put("a.becerra@udc.es", "abc123.");
        usuariosConLogin.put("981233120", "abc123.");
        usuariosConLogin.put("adrian_rc", "abc123.");
        usuariosConLogin.put("alexbs01", "abc123.");
    }

    @Test
    void checkPasswd() {
        insertarUsuarios();
        assertTrue(logins.checkPasswd("a.becerra@udc.es", "abc123."));
        assertFalse(logins.checkPasswd("981233120", "passwdIncorrect"));
    }

    @Test
    void authenticateUser() {
        insertarUsuarios();

        // Los usuarios, contraseña y tipo de logeo correctos
        assertTrue(logins.authenticateUser("a.becerra@udc.es", "abc123.", IdPreferido.EMAIL));
        assertTrue(logins.authenticateUser("981233120", "abc123.", IdPreferido.TELEPHONE));

        // Usuarios y tipos de logeo correcto, pero contraseña incorrecta
        assertFalse(logins.authenticateUser("a.becerra@udc.es", "passwordIncorrect", IdPreferido.EMAIL));
        assertFalse(logins.authenticateUser("981233120", "passwordIncorrect", IdPreferido.TELEPHONE));

        // Usuario incorrecto, pero contraseña y tipo de logeo correcto
        assertFalse(logins.authenticateUser("emailSinArroba.com", "abc123.", IdPreferido.EMAIL));
        assertFalse(logins.authenticateUser("981000abc", "abc123.", IdPreferido.TELEPHONE));

        // Usuario y contraseña correctas, pero tipo de logeo incorrecto
        assertFalse(logins.authenticateUser("a.becerra@udc.es", "abc123.", IdPreferido.TELEPHONE));
        assertFalse(logins.authenticateUser("981233120", "abc123.", IdPreferido.EMAIL));

        // Usuario, contraseña y tipo de logeo correctos, pero no están registrasdos en el HashMap
        assertFalse(logins.authenticateUser("adrian.rego@udc.es", "abc123.", IdPreferido.EMAIL));
        assertFalse(logins.authenticateUser("981000000", "abc123.", IdPreferido.TELEPHONE));

        // Usuario y contraseña correcta, pero tipo de inicio de sesión incorrecto
        assertFalse(logins.authenticateUser("adrian_rc", "abc123.", IdPreferido.TELEPHONE));

        // Todo correcto
        assertTrue(logins.authenticateUser("adrian_rc", "abc123.", IdPreferido.NOMBRE_USUARIO));
        assertTrue(logins.authenticateUser("alexbs01", "abc123.", IdPreferido.NOMBRE_USUARIO));

        // Usuario y tipo de inicio de sesión correctos, pero contraseña incorrecta
        assertFalse(logins.authenticateUser("alexbs01", "incorrectPassword", IdPreferido.NOMBRE_USUARIO));

    }
}