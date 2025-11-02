package br.com.consultorio.paciente;

public class Paciente {

    // Atributos
    private String nome;
    private final String cpf;
    private String telefone;

    // Construtor
    public Paciente(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    // Método
    public void esperarAtendimento() {
        System.out.println("Paciente " + nome + " aguardando atendimento...");
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}

