package br.com.consultorio.paciente;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PacienteTest {

    @Test
    void testPaciente() {
        // Criando paciente
        Paciente paciente = new Paciente("Carlos Souza", "123.456.789-00", "(61) 91234-5678");

        // ==========================
        // Testando getters
        // ==========================
        assertEquals("Carlos Souza", paciente.getNome());
        assertEquals("123.456.789-00", paciente.getCpf());
        assertEquals("(61) 91234-5678", paciente.getTelefone());

        // ==========================
        // Testando setters
        // ==========================
        paciente.setNome("Carlos S.");
        paciente.setTelefone("(61) 98765-4321");
        assertEquals("Carlos S.", paciente.getNome());
        assertEquals("(61) 98765-4321", paciente.getTelefone());

        // ==========================
        // Testando método específico
        // ==========================
        paciente.esperarAtendimento(); // Deve imprimir mensagem de espera
    }
}

