package br.com.consultorio.agendamento;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConsultaPresencialTest {

    @Test
    void testConsultaPresencial() {
        // Criando uma consulta presencial
        ConsultaPresencial consulta = new ConsultaPresencial("18/06/2025", "14:00", 5);

        // ==========================
        // Testando getters
        // ==========================
        assertEquals("18/06/2025", consulta.getData());
        assertEquals("14:00", consulta.getHora());
        assertEquals(5, consulta.getLocal());

        // ==========================
        // Testando setters
        // ==========================
        consulta.setData("19/06/2025");
        consulta.setHora("15:30");
        consulta.setLocal(8);

        assertEquals("19/06/2025", consulta.getData());
        assertEquals("15:30", consulta.getHora());
        assertEquals(8, consulta.getLocal());

        // ==========================
        // Testando métodos específicos
        // ==========================
        consulta.agendarConsulta();       // Deve executar sem erros
        consulta.verificarConsultorio();  // Deve verificar número do consultório

        // Testando consultório inexistente
        consulta.setLocal(12);
        consulta.verificarConsultorio();  // Deve imprimir mensagem de falha
    }
}
