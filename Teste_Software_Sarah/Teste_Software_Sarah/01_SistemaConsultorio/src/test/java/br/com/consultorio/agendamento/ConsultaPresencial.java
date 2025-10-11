package br.com.consultorio.agendamento;

public class ConsultaPresencial extends Consulta {

    private int consultorio;  // minúsculo

    public ConsultaPresencial(String data, String hora, int consultorio) {
        super(data, hora);
        this.consultorio = consultorio;
    }

    public void consultarMedico() {  // minúsculo inicial
        if (consultorio > 10) {
            System.out.println("Consultório inexistente. Falha no agendamento da consulta.");
        } else {
            System.out.println("Consulta presencial no consultório número " + consultorio + ".");
        }
    }

    @Override
    public void agendarConsulta() {
        super.agendarConsulta();
        System.out.println("Consulta Presencial agendada para data " + getData() + " às " + getHora() + ".");
    }

    public int getLocal() {
        return consultorio;
    }

    public void setLocal(int consultorio) {
        this.consultorio = consultorio;
    }
}
