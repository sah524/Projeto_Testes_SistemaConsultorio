package br.com.consultorio.agendamento;

import br.com.consultorio.paciente.Paciente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConsultaPresencialTest {

    @Test
    void testConsultorioValido() {
        ConsultaPresencial cp = new ConsultaPresencial("23/10/2025", "09:00", 5);
        Paciente p = new Paciente("Ana", "55566677788", "98888-7777");
        cp.setPaciente(p);

        assertEquals(5, cp.getConsultorio());
        assertEquals("Ana", cp.getPaciente().getNome());
    }

    @Test
    void testConsultorioInvalido() {
        ConsultaPresencial cp = new ConsultaPresencial("23/10/2025", "09:00", 16);
        assertTrue(cp.getConsultorio() > 10 || cp.getConsultorio() < 1);
    }
}
