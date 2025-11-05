package br.com.consultorio.agendamento;

import br.com.consultorio.interfaces.IAgendavel;
import br.com.consultorio.paciente.Paciente;

public class Consulta implements IAgendavel {

    private String data;
    private String hora;
    private Paciente paciente; // novo campo paciente

    public Consulta(String data, String hora) {
        this.data = data;
        this.hora = hora;
    }

    @Override
    public void agendarConsulta() {
        System.out.println("Consulta agendada para " + data + " as " + hora + ".");
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        String pacienteNome = (paciente != null) ? paciente.getNome() : "Sem paciente";
        return "Paciente: " + pacienteNome + " | Data: " + data + " | Hora: " + hora;
    }
}