package br.com.consultorio.medico;

public class Medico {
// Atributos
private String nome;
private String especialidade;

// Método
public void atenderPacientes() {
System.out.println("Medico " + nome + " atendendo.");
}

// Construtor (obriga os atributos a serem inicializados)
public Medico(String nome, String especialidade) {
this.nome = nome;
this.especialidade = especialidade;
}

// Getters e Setters
public String getNome() {
return nome;
}

public void setNome(String nome) {
this.nome = nome;
}

public String getEspecialidade() {
return especialidade;
}

public void setEspecialidade(String especialidade) {
this.especialidade = especialidade;
}
}
