package br.com.consultorio.main;

import br.com.consultorio.config.Conexao;
import br.com.consultorio.login.LoginMenu;
import br.com.consultorio.login.Usuario;
import br.com.consultorio.paciente.PacienteMenu;
import br.com.consultorio.agendamento.ConsultaMenu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            LoginMenu loginMenu = new LoginMenu();
            PacienteMenu pacienteMenu = new PacienteMenu();
            ConsultaMenu consultaMenu = new ConsultaMenu();

            Usuario usuarioLogado = null;

            // Loop de login
            while (usuarioLogado == null) {
                usuarioLogado = loginMenu.realizarLogin(); // retorna objeto Usuario

                if (usuarioLogado == null) {
                    System.out.println("Usuario nao encontrado. Deseja criar uma conta? (S/N)");
                    String opcao = sc.nextLine();
                    if (opcao.equalsIgnoreCase("S")) {
                        usuarioLogado = loginMenu.cadastrarUsuario(); // retorna Usuario cadastrado
                    } else {
                        System.out.println("Retornando ao login...");
                    }
                }
            }

            // Conexao com o banco apenas UMA vez apos login
            try (Connection con = Conexao.getConexao()) {
                if (con != null) {
                    System.out.println("Conexao com o banco consultorio_db realizada com sucesso!");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao conectar no banco: " + e.getMessage());
                return;
            }

            System.out.println("Bem-vindo ao sistema " + usuarioLogado.getLogin() + "!");

            int escolha = -1;
            do {
                System.out.println("\n====================================");
                System.out.println("           MENU PRINCIPAL");
                System.out.println("====================================");
                System.out.println("1 - Cadastrar Paciente");
                System.out.println("2 - Cadastrar Consulta");
                System.out.println("3 - Listar Consultas");
                System.out.println("4 - Listar Pacientes");
                System.out.println("5 - Atualizar Paciente");
                System.out.println("6 - Excluir Paciente");  // Nova opção acrescentada
                System.out.println("7 - Excluir Consulta");
                System.out.println("8 - Excluir Usuario");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opcao: ");
                
                String entrada = sc.nextLine().trim();

                try {
                    escolha = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada invalida! Digite apenas numeros de 0 a 8.");
                    continue; // volta para o início do loop sem quebrar o programa
                }

                switch (escolha) {
                    case 1 -> pacienteMenu.cadastrarPaciente();
                    case 2 -> consultaMenu.iniciar();
                    case 3 -> consultaMenu.listarConsultas();
                    case 4 -> pacienteMenu.listarPacientes();
                    case 5 -> pacienteMenu.atualizarPaciente(); 
                    case 6 -> pacienteMenu.excluirPaciente();
                    case 7 -> consultaMenu.excluirConsulta();
                    case 8 -> loginMenu.excluirUsuario();
                    case 0 -> System.out.println("Saindo do sistema...");
                    default -> System.out.println("Opcao invalida! Digite um numero de 0 a 8.");
                }

            } while (escolha != 0);
        }
    }
}
