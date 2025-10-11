package br.com.consultorio.paciente;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PacienteTest {

    @Test
    void testPaciente() {
        // Criando um paciente
        Paciente paciente = new Paciente("Ana Silva", "123.456.789-00", "(61) 91234-5678");

        // Testar getters
        assertEquals("Ana Silva", paciente.getNome());
        assertEquals("123.456.789-00", paciente.getCpf());
        assertEquals("(61) 91234-5678", paciente.getTelefone());

        // Testar setters
        paciente.setNome("Carlos Souza");
        paciente.setTelefone("(61) 99876-5432");
        assertEquals("Carlos Souza", paciente.getNome());
        assertEquals("(61) 99876-5432", paciente.getTelefone());

        // Testar método que imprime mensagem
        paciente.esperarAtendimento(); // só garante que não lança exceção
    }
}