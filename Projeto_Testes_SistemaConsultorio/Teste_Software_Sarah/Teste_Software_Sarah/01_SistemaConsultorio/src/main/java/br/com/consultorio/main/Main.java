package br.com.consultorio.main;

import br.com.consultorio.paciente.Paciente;
import br.com.consultorio.medico.Medico;
import br.com.consultorio.agendamento.Consulta;
import br.com.consultorio.agendamento.ConsultaOnline;
import br.com.consultorio.agendamento.ConsultaPresencial;

public class Main {
    public static void main(String[] args) {

        try {
            // Criando paciente
            Paciente paciente1 = new Paciente("Ana Silva", "123.456.789-00", "(61) 91234-5678");
            paciente1.esperarAtendimento();

            // Criando médico
            Medico medico1 = new Medico("Dr. Joao", "Cardiologia");
            medico1.atenderPacientes();

            System.out.println("--------------------------------------------------");

            // Consulta comum
            Consulta consulta1 = new Consulta("14/06/2026", "19:00");
            consulta1.agendarConsulta();

            System.out.println("--------------------------------------------------");

            // Consulta presencial
            ConsultaPresencial consultaPresencial = new ConsultaPresencial("15/06/2025", "14:30", 12);
            consultaPresencial.agendarConsulta();
            consultaPresencial.verificarConsultorio();

            System.out.println("--------------------------------------------------");

            // Consulta online
            ConsultaOnline consultaOnline = new ConsultaOnline("16/06/2025", "10:00", "https://meet.link/consulta123");
            consultaOnline.agendarConsulta();
            consultaOnline.enviarLink();

            System.out.println("--------------------------------------------------");

        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a execucao do sistema: " + e);
        }
    }
}

