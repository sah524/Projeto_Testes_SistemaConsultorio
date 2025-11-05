package br.com.consultorio.login;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.*;
import br.com.consultorio.config.Conexao;

public class LoginMenu {

    private Scanner sc = new Scanner(System.in);

    public Usuario realizarLogin() {
        System.out.println("====================================");
        System.out.println("             LOGIN");
        System.out.println("====================================");
        System.out.print("Digite seu login: ");
        String login = sc.nextLine().trim();
        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine().trim();

        if (login.isEmpty() || senha.isEmpty()) {
            System.out.println("Campos login e senha nao podem estar vazios.");
            return null;
        }

        try (Connection con = Conexao.getConexao()) {
            String sqlUser = "SELECT * FROM usuario WHERE login = ?";
            PreparedStatement psUser = con.prepareStatement(sqlUser);
            psUser.setString(1, login);
            ResultSet rsUser = psUser.executeQuery();

            if (rsUser.next()) {
                String senhaBanco = rsUser.getString("senha");
                if (senhaBanco.equals(senha)) {
                    return new Usuario(login, senha);
                } else {
                    System.out.println("Usuario ou senha incorretos.");
                    return null;
                }
            } else {
                System.out.println("Usuario nao encontrado.");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro no login: " + e.getMessage());
            return null;
        }
    }

    public Usuario cadastrarUsuario() {
        System.out.print("Digite um nome de usuario: ");
        String login = sc.nextLine().trim();

        String senha;
        while (true) {
            System.out.print("Digite uma senha (mínimo 6 caracteres, contendo letras maiúsculas, minúsculas, números e símbolos): ");
            senha = sc.nextLine().trim();

            if (senha.isEmpty()) {
                System.out.println("Campo senha nao pode estar vazio.");
                continue;
            }

            if (!senhaForte(senha)) {
                System.out.println("Senha fraca! Tente uma senha mais forte.");
                System.out.println("Dica: use pelo menos 6 caracteres, com letras maiúsculas, minúsculas, números e símbolos (ex: @, #, !, $)");
            } else {
                break;
            }
        }

        if (login.isEmpty()) {
            System.out.println("Campo login nao pode estar vazio.");
            return null;
        }

        try (Connection con = Conexao.getConexao()) {
            String sqlVerifica = "SELECT * FROM usuario WHERE login = ?";
            PreparedStatement psVerifica = con.prepareStatement(sqlVerifica);
            psVerifica.setString(1, login);
            ResultSet rs = psVerifica.executeQuery();

            if (rs.next()) {
                System.out.println("Usuario ja cadastrado.");
                return null;
            }

            String sql = "INSERT INTO usuario(login, senha) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, senha);
            ps.executeUpdate();
            System.out.println("Usuario cadastrado com sucesso!");
            return new Usuario(login, senha);

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuario: " + e.getMessage());
            return null;
        }
    }

    private boolean senhaForte(String senha) {
        if (senha.length() < 6) return false;
        Pattern letraMaiuscula = Pattern.compile("[A-Z]");
        Pattern letraMinuscula = Pattern.compile("[a-z]");
        Pattern numero = Pattern.compile("[0-9]");
        Pattern simbolo = Pattern.compile("[^a-zA-Z0-9]");

        return letraMaiuscula.matcher(senha).find() &&
               letraMinuscula.matcher(senha).find() &&
               numero.matcher(senha).find() &&
               simbolo.matcher(senha).find();
    }

    public void excluirUsuario() {
        System.out.print("Digite o login do usuario que deseja excluir: ");
        String login = sc.nextLine().trim();

        if (login.isEmpty()) {
            System.out.println("Campo login nao pode estar vazio.");
            return;
        }

        String sql = "DELETE FROM usuario WHERE login = ?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, login);
            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                System.out.println("Usuario excluido com sucesso!");
            } else {
                System.out.println("Usuario nao encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir usuario: " + e.getMessage());
        }
    }

    public void atualizarUsuario() {
        System.out.print("Digite o login do usuario que deseja atualizar: ");
        String login = sc.nextLine().trim();

        try (Connection con = Conexao.getConexao()) {
            String sqlBusca = "SELECT * FROM usuario WHERE login = ?";
            PreparedStatement psBusca = con.prepareStatement(sqlBusca);
            psBusca.setString(1, login);
            ResultSet rs = psBusca.executeQuery();

            if (!rs.next()) {
                System.out.println("Usuario não encontrado.");
                return;
            }

            System.out.println("Senha atual: " + rs.getString("senha"));
            System.out.print("Digite a nova senha (ou pressione Enter para manter): ");
            String novaSenha = sc.nextLine().trim();
            if (novaSenha.isEmpty()) novaSenha = rs.getString("senha");

            String sqlUpdate = "UPDATE usuario SET senha = ? WHERE login = ?";
            PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
            psUpdate.setString(1, novaSenha);
            psUpdate.setString(2, login);
            int linhas = psUpdate.executeUpdate();

            if (linhas > 0) {
                System.out.println("Usuario atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar usuario.");
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
