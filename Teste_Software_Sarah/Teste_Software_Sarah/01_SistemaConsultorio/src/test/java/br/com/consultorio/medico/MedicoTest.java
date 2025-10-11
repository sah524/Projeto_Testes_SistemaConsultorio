package br.com.consultorio.medico;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedicoTest {

    @Test
    void testMedico() {
        // Criando médico
        Medico medico = new Medico("Dra. Maria", "Cardiologia");

        // ==========================
        // Testando getters
        // ==========================
        assertEquals("Dra. Maria", medico.getNome());
        assertEquals("Cardiologia", medico.getEspecialidade());

        // ==========================
        // Testando setters
        // ==========================
        medico.setNome("Dra. M. Silva");
        medico.setEspecialidade("Clínica Geral");
        assertEquals("Dra. M. Silva", medico.getNome());
        assertEquals("Clínica Geral", medico.getEspecialidade());

        // ==========================
        // Testando método específico
        // ==========================
        medico.atenderPacientes(); // Deve imprimir mensagem de atendimento
    }
}
