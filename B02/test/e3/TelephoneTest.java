package e3;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TelephoneTest {
    HashMap<String, String> usuariosConLogin = new HashMap<>();
    Telephone telephone = new Telephone(usuariosConLogin);

    void insertarUsuarios() {
        usuariosConLogin.put("a.becerra@udc.es", "abc123.");
        usuariosConLogin.put("981233120", "abc123.");
    }

    @Test
    void validateId() {
        assertFalse(telephone.validateId("a.becerra@udc.es"));
        assertTrue(telephone.validateId("981233120"));
    }

    @Test
    void userIsAuthenticated() {
        insertarUsuarios();
        assertFalse(telephone.userIsAuthenticated("a.becerra@udc.es", "abc123."));
        assertTrue(telephone.userIsAuthenticated("981233120", "abc123."));
        assertFalse(telephone.userIsAuthenticated("981233120", "incorrectPassword"));
    }
}