package br.com.consultorio.agendamento;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConsultaTest {

    @Test
    void testAgendarConsulta() {
        // Criando a instância da consulta
        Consulta consulta = new Consulta("14/06/2025", "10:00");

        // Testar getters
        assertEquals("14/06/2025", consulta.getData());
        assertEquals("10:00", consulta.getHora());

        // Testar setters
        consulta.setData("15/06/2025");
        consulta.setHora("11:00");
        assertEquals("15/06/2025", consulta.getData());
        assertEquals("11:00", consulta.getHora());
    }
}