package br.com.consultorio.agendamento;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConsultaOnlineTest {

    @Test
    void testConsultaOnline() {
        // Criando uma consulta online
        ConsultaOnline consultaOnline = new ConsultaOnline("17/06/2025", "11:30", "https://meet.link/consulta456");

        // ==========================
        // Testando getters
        // ==========================
        assertEquals("17/06/2025", consultaOnline.getData());
        assertEquals("11:30", consultaOnline.getHora());
        assertEquals("https://meet.link/consulta456", consultaOnline.getLinkVideoChamada());

        // ==========================
        // Testando setters
        // ==========================
        consultaOnline.setData("18/06/2025");
        consultaOnline.setHora("12:00");
        consultaOnline.setLinkVideoChamada("https://meet.link/consulta789");

        assertEquals("18/06/2025", consultaOnline.getData());
        assertEquals("12:00", consultaOnline.getHora());
        assertEquals("https://meet.link/consulta789", consultaOnline.getLinkVideoChamada());

        // ==========================
        // Testando métodos específicos
        // ==========================
        consultaOnline.agendarConsulta(); // Deve executar sem erros
        consultaOnline.enviarLink();      // Deve imprimir o link da consulta
    }
}
