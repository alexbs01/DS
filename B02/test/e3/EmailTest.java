package e3;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {
    HashMap<String, String> usuariosConLogin = new HashMap<>();
    Email email = new Email(usuariosConLogin);

    void insertarUsuarios() {
        usuariosConLogin.put("a.becerra@udc.es", "abc123.");
        usuariosConLogin.put("981233120", "abc123.");
    }

    @Test
    void validateId() {
        assertTrue(email.validateId("a.becerra@udc.es"));
        assertFalse(email.validateId("981233120"));
    }

    @Test
    void userIsAuthenticated() {
        insertarUsuarios();
        assertTrue(email.userIsAuthenticated("a.becerra@udc.es", "abc123."));
        assertFalse(email.userIsAuthenticated("981233120", "abc123."));
        assertFalse(email.userIsAuthenticated("a.becerra@udc.es", "incorrectPassword"));
    }
}