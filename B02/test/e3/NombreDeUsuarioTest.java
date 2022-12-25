package e3;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class NombreDeUsuarioTest {
    HashMap<String, String> usuariosConLogin = new HashMap<>();
    NombreDeUsuario nombreDeUsuario = new NombreDeUsuario(usuariosConLogin);

    void insertarUsuarios() {
        usuariosConLogin.put("a.becerra@udc.es", "abc123.");
        usuariosConLogin.put("adian_rc", "abc123.");
        usuariosConLogin.put("adian-rc", "abc123.");
        usuariosConLogin.put("alexbs01", "abc123.");
    }

    @Test
    void validateId() {
        assertFalse(nombreDeUsuario.validateId("a.becerra@udc.es"));
        assertTrue(nombreDeUsuario.validateId("adian_rc"));
        assertFalse(nombreDeUsuario.validateId("adian-rc"));
        assertTrue(nombreDeUsuario.validateId("alexbs01"));
    }

    @Test
    void userIsAuthenticated() {
        insertarUsuarios();
        assertFalse(nombreDeUsuario.userIsAuthenticated("a.becerra@udc.es", "abc123."));
        assertTrue(nombreDeUsuario.userIsAuthenticated("adian_rc", "abc123."));
        assertFalse(nombreDeUsuario.userIsAuthenticated("adian-rc", "abc123."));
        assertFalse(nombreDeUsuario.userIsAuthenticated("alexbs01", "incorrectPassword"));
        assertTrue(nombreDeUsuario.userIsAuthenticated("alexbs01", "abc123."));
    }

}