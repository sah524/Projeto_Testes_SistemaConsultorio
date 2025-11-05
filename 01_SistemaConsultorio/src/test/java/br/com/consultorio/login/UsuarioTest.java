package br.com.consultorio.login;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    //CENÁRIO: Inserir login correto e incorreto
    void testUsuario() {
        Usuario u = new Usuario("admin", "1234");
        assertEquals("admin", u.getLogin());
        assertEquals("1234", u.getSenha());
    }
}

