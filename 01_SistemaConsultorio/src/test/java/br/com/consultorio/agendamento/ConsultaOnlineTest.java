package br.com.consultorio.agendamento;

import br.com.consultorio.paciente.Paciente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConsultaOnlineTest {

    @Test
    //CENARIO: ENTRADA DE DADOS
    void testConsultaOnline() {
        ConsultaOnline co = new ConsultaOnline("22/10/2025", "16:00", "https://linkvideo.com/123");
        Paciente p = new Paciente("Lucas", "11122233344", "99999-1111");
        co.setPaciente(p);

        assertEquals("22/10/2025", co.getData());
        assertEquals("16:00", co.getHora());
        assertEquals("https://linkvideo.com/123", co.getLinkVideoChamada());
        assertEquals(p, co.getPaciente());
    }
}
