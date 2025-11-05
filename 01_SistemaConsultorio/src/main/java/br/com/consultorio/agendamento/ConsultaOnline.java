package br.com.consultorio.agendamento;

import br.com.consultorio.paciente.Paciente;

public class ConsultaOnline extends Consulta {

    private String linkVideoChamada;

    public ConsultaOnline(String data, String hora, String linkVideoChamada) {
        super(data, hora);
        this.linkVideoChamada = linkVideoChamada;
    }

    public void enviarLink() {
        System.out.println("Link para a consulta online: " + linkVideoChamada);
    }

    @Override
    public void agendarConsulta() {
        Paciente p = getPaciente();
        String pacienteNome = (p != null) ? p.getNome() : "Sem paciente";
        System.out.println("Consulta online agendada para " + pacienteNome +
                " no dia " + getData() + " as " + getHora() + ".");
    }

    public String getLinkVideoChamada() {
        return linkVideoChamada;
    }

    public void setLinkVideoChamada(String linkVideoChamada) {
        this.linkVideoChamada = linkVideoChamada;
    }
}
