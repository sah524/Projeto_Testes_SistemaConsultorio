package br.com.consultorio.agendamento;

import br.com.consultorio.interfaces.IAgendavel;

// Classe que representa uma consulta e implementa a interface IAgendavel
public class Consulta implements IAgendavel {

    private String data; // Data da consulta
    private String hora; // Hora da consulta

    // Construtor 
    public Consulta(String data, String hora) {
        this.data = data;
        this.hora = hora;
    }

    // Método da interface que agenda a consulta
    @Override
    public void agendarConsulta() {
        System.out.println("Consulta agendada para " + data + " às " + hora + ".");
    }

    // Retorna a data
    public String getData() {
        return data;
    }

    // Define a data
    public void setData(String data) {
        this.data = data;
    }

    // Retorna a hora
    public String getHora() {
        return hora;
    }

    // Define a hora
    public void setHora(String hora) {
        this.hora = hora;
    }
}
