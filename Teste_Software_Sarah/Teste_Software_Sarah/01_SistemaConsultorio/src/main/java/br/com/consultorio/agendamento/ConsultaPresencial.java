package br.com.consultorio.agendamento;

public class ConsultaPresencial extends Consulta {

    private int consultorio;

    // Construtor
    public ConsultaPresencial(String data, String hora, int consultorio) {
        super(data, hora); // chama o construtor da classe mãe (Consulta)
        this.consultorio = consultorio;
    }

    // Método específico dessa classe
    public void verificarConsultorio() {
        if (consultorio > 10) {
            System.out.println("Consultorio inexistente. Falha no agendamento da consulta.");
        } else {
            System.out.println("Consulta presencial marcada no consultório número " + consultorio + ".");
        }
    }

    // Sobrescrevendo o método da interface (presumivelmente da classe mãe)
    @Override
    public void agendarConsulta() {
        super.agendarConsulta(); // executa o método original da classe mãe
        System.out.println("Essa é uma consulta presencial para o dia " + getData() + " às " + getHora() + ".");
    }

    // Getters e Setters
    public int getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(int consultorio) {
        this.consultorio = consultorio;
    }

    public int getLocal() {
        return consultorio;
    }

    public void setLocal(int consultorio) {
        this.consultorio = consultorio;
    }

    public void consultarMedico() {
        // Implementação futura, se necessário
        System.out.println("Consultando médico...");
    }
}
