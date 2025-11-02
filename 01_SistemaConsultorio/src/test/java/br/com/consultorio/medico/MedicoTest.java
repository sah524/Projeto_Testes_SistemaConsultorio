package br.com.consultorio.medico;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedicoTest {

    @Test
    void testMedico() {
        // Criando um médico
        Medico medico = new Medico("Dr. João", "Cardiologia");

        // Testar getters
        assertEquals("Dr. João", medico.getNome());
        assertEquals("Cardiologia", medico.getEspecialidade());

        // Testar setters
        medico.setNome("Dra. Maria");
        medico.setEspecialidade("Pediatria");
        assertEquals("Dra. Maria", medico.getNome());
        assertEquals("Pediatria", medico.getEspecialidade());

        // Testar o método que imprime mensagem
        medico.atenderPacientes(); // só garante que não lança exceção
    }
}