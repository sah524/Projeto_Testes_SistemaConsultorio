package br.com.consultorio.agendamento;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConsultaTest {

    @Test
    void testAgendarConsulta() {
        // Criando uma instância de Consulta
        Consulta consulta = new Consulta("14/06/2025", "10:00");

        // ==========================
        // Testando os getters
        // ==========================
        assertEquals("14/06/2025", consulta.getData(), "O getter getData() deve retornar a data correta");
        assertEquals("10:00", consulta.getHora(), "O getter getHora() deve retornar a hora correta");

        // ==========================
        // Testando os setters
        // ==========================
        consulta.setData("15/06/2025");
        consulta.setHora("11:00");

        // Verificando se os setters atualizaram corretamente os valores
        assertEquals("15/06/2025", consulta.getData(), "O setter setData() deve atualizar a data corretamente");
        assertEquals("11:00", consulta.getHora(), "O setter setHora() deve atualizar a hora corretamente");

        // ==========================
        // Testando agendamento
        // ==========================
        // Aqui podemos chamar agendarConsulta() para garantir que não lança exceção
        consulta.agendarConsulta();
    }
}
