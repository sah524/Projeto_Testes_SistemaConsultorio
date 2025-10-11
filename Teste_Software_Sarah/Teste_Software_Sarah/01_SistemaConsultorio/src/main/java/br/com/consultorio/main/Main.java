package br.com.consultorio.main;

import br.com.consultorio.paciente.Paciente;
import br.com.consultorio.medico.Medico;
import br.com.consultorio.agendamento.Consulta;
import br.com.consultorio.agendamento.ConsultaOnline;
import br.com.consultorio.agendamento.ConsultaPresencial;

public class Main {
    public static void main(String[] args) {

        try {
            // ========================================
            // Teste de Sistema (Sistema em funcionamento)
            // ========================================

            // Criando paciente (Teste Unitário de Paciente)
            Paciente paciente1 = new Paciente("Ana Silva", "123.456.789-00", "(61) 91234-5678");
            paciente1.esperarAtendimento(); // Método específico do Paciente
            // Tipo de teste: Unitário

            // Criando médico (Teste Unitário de Medico)
            Medico medico1 = new Medico("Dr. Joao", "Cardiologia");
            medico1.atenderPacientes(); // Método específico do Médico
            // Tipo de teste: Unitário

            System.out.println("--------------------------------------------------");

            // Consulta comum (Teste Unitário de Consulta)
            Consulta consulta1 = new Consulta("14/06/2026", "19:00");
            consulta1.agendarConsulta(); // Método da interface IaAgendavel
            // Tipo de teste: Unitário

            System.out.println("--------------------------------------------------");

            // Consulta presencial (Teste Unitário de ConsultaPresencial)
            ConsultaPresencial consultaPresencial = new ConsultaPresencial("15/06/2025", "14:30", 12);
            consultaPresencial.agendarConsulta();      // Teste método herdado
            consultaPresencial.verificarConsultorio(); // Teste método específico
            // Tipo de teste: Unitário

            System.out.println("--------------------------------------------------");

            // Consulta online (Teste Unitário de ConsultaOnline)
            ConsultaOnline consultaOnline = new ConsultaOnline("16/06/2025", "10:00", "https://meet.link/consulta123");
            consultaOnline.agendarConsulta(); // Método herdado + override
            consultaOnline.enviarLink();      // Método específico
            // Tipo de teste: Unitário

            System.out.println("--------------------------------------------------");

        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a execução do sistema: " + e);
        }
    }
}
