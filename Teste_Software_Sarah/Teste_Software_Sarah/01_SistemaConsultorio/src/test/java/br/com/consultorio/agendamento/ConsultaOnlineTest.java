package br.com.consultorio.agendamento;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConsultaOnlineTest {

    @Test
    void testConsultaOnline() {
        // Criando a consulta online
        ConsultaOnline consultaOnline = new ConsultaOnline("16/06/2025", "10:00", "https://meet.link/consulta123");

        // Testar getters
        assertEquals("16/06/2025", consultaOnline.getData());
        assertEquals("10:00", consultaOnline.getHora());
        assertEquals("https://meet.link/consulta123", consultaOnline.getLinkVideoChamada());

        // Testar setters
        consultaOnline.setData("17/06/2025");
        consultaOnline.setHora("11:30");
        consultaOnline.setLinkVideoChamada("https://meet.link/consulta456");
        assertEquals("17/06/2025", consultaOnline.getData());
        assertEquals("11:30", consultaOnline.getHora());
        assertEquals("https://meet.link/consulta456", consultaOnline.getLinkVideoChamada());

        // Testar agendarConsulta() - só para garantir que não lança exceção
        consultaOnline.agendarConsulta();

        // Testar enviarLink() - só para garantir que não lança exceção
        consultaOnline.enviarLink();
    }
}