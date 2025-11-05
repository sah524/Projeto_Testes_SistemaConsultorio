package br.com.consultorio.paciente;

import br.com.consultorio.config.Conexao;
import java.sql.*;
import java.util.Scanner;

public class PacienteMenu {

    private Scanner sc = new Scanner(System.in);

    public void cadastrarPaciente() {
        System.out.print("Digite o nome do paciente: ");
        String nome = sc.nextLine().trim();

        System.out.print("Digite o CPF do paciente: ");
        String cpf = sc.nextLine().trim();

        System.out.print("Digite o telefone do paciente: ");
        String telefone = sc.nextLine().trim();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            System.out.println("Todos os campos são obrigatórios.");
            return;
        }

        try (Connection con = Conexao.getConexao()) {
            String sql = "INSERT INTO paciente (nome, cpf, telefone) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, cpf);
            ps.setString(3, telefone);
            ps.executeUpdate();
            System.out.println("Paciente cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }

    public void listarPacientes() {
        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM paciente")) {

            System.out.println("====================================");
            System.out.println("         LISTA DE PACIENTES");
            System.out.println("====================================");

            boolean vazio = true;
            while (rs.next()) {
                vazio = false;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("Telefone: " + rs.getString("telefone"));
                System.out.println("------------------------------------");
            }

            if (vazio) {
                System.out.println("Nenhum paciente encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        }
    }

    public void excluirPaciente() {
        System.out.print("Digite o ID do paciente que deseja excluir: ");
        String idStr = sc.nextLine().trim();

        if (idStr.isEmpty()) {
            System.out.println("Campo ID não pode estar vazio.");
            return;
        }

        int idPaciente;
        try {
            idPaciente = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        try (Connection con = Conexao.getConexao()) {
            String sql = "DELETE FROM paciente WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPaciente);
            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                System.out.println("Paciente excluído com sucesso!");
            } else {
                System.out.println("Paciente não encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir paciente: " + e.getMessage());
        }
    }

    public void atualizarPaciente() {
        System.out.print("Digite o ID do paciente que deseja atualizar: ");
        String idStr = sc.nextLine().trim();

        if (idStr.isEmpty()) {
            System.out.println("Campo ID não pode estar vazio.");
            return;
        }

        int idPaciente;
        try {
            idPaciente = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        try (Connection con = Conexao.getConexao()) {
            String sqlBusca = "SELECT * FROM paciente WHERE id = ?";
            PreparedStatement psBusca = con.prepareStatement(sqlBusca);
            psBusca.setInt(1, idPaciente);
            ResultSet rs = psBusca.executeQuery();

            if (!rs.next()) {
                System.out.println("Paciente não encontrado.");
                return;
            }

            System.out.println("Nome atual: " + rs.getString("nome"));
            System.out.println("CPF: " + rs.getString("cpf"));
            System.out.println("Telefone atual: " + rs.getString("telefone"));

            System.out.print("Digite o novo nome (ou pressione Enter para manter): ");
            String novoNome = sc.nextLine().trim();
            if (novoNome.isEmpty()) novoNome = rs.getString("nome");

            System.out.print("Digite o novo telefone (ou pressione Enter para manter): ");
            String novoTelefone = sc.nextLine().trim();
            if (novoTelefone.isEmpty()) novoTelefone = rs.getString("telefone");

            String sqlAtualiza = "UPDATE paciente SET nome = ?, telefone = ? WHERE id = ?";
            PreparedStatement psAtualiza = con.prepareStatement(sqlAtualiza);
            psAtualiza.setString(1, novoNome);
            psAtualiza.setString(2, novoTelefone);
            psAtualiza.setInt(3, idPaciente);

            int linhas = psAtualiza.executeUpdate();
            if (linhas > 0) {
                System.out.println("Paciente atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar paciente.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar paciente: " + e.getMessage());
        }
    }

    public boolean existePaciente(int idPaciente) {
        try (Connection con = Conexao.getConexao()) {
            String sql = "SELECT * FROM paciente WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPaciente);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Erro ao verificar paciente: " + e.getMessage());
            return false;
        }
    }
}
