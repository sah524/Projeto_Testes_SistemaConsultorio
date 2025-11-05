package br.com.consultorio.agendamento;

import br.com.consultorio.paciente.Paciente;

public class ConsultaPresencial extends Consulta {

    private int consultorio;

    public ConsultaPresencial(String data, String hora, int consultorio) {
        super(data, hora);
        this.consultorio = consultorio;
    }

    public void verificarConsultorio() {
        if (consultorio < 1 || consultorio > 10) {
            System.out.println("Consultorio inexistente. Falha no agendamento da consulta.");
        } else {
            System.out.println("Consulta presencial marcada no consultorio numero " + consultorio + ".");
        }
    }

    @Override
    public void agendarConsulta() {
        Paciente p = getPaciente();
        String pacienteNome = (p != null) ? p.getNome() : "Sem paciente";
        System.out.println("Consulta presencial agendada para " + pacienteNome +
                " no dia " + getData() + " as " + getHora() + ".");
    }

    public int getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(int consultorio) {
        this.consultorio = consultorio;
    }

    public void consultarMedico() {
        System.out.println("Consultando medico...");
    }
}
