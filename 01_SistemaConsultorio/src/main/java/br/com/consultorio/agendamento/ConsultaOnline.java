package br.com.consultorio.agendamento;

public class ConsultaOnline extends Consulta {

    private String linkVideoChamada;

    // Construtor
    public ConsultaOnline(String data, String hora, String linkVideoChamada) {
        super(data, hora); // chama o construtor da classe mãe (Consulta)
        this.linkVideoChamada = linkVideoChamada;
    }

    // Método específico dessa classe
    public void enviarLink() {
        System.out.println("Link para a consulta online: " + linkVideoChamada);
    }

    // Sobrescrevendo o método da interface
    @Override
    public void agendarConsulta() {
        super.agendarConsulta(); // mantém o comportamento da classe mãe
        System.out.println("Essa e uma consulta online agendada para " + getData() + " as " + getHora() + ".");
    }

    // Getter e Setter
    public String getLinkVideoChamada() {
        return linkVideoChamada;
    }

    public void setLinkVideoChamada(String linkVideoChamada) {
        this.linkVideoChamada = linkVideoChamada;
    }
}

