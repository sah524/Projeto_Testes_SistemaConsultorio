package br.com.consultorio.paciente;

import br.com.consultorio.config.Conexao;
import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteMenuTest {

    @Test
    @Order(1)
    //CENARIO1: cadastrar um novo paciente
    void testCadastrarPaciente() throws SQLException {
        String nome = "TesteAPI";
        String cpf = "00011122233";
        String telefone = "99999-0000";

        try (Connection con = Conexao.getConexao()) {
            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO paciente (nome, cpf, telefone) VALUES (?, ?, ?)"
            );
            pst.setString(1, nome);
            pst.setString(2, cpf);
            pst.setString(3, telefone);
            int linhas = pst.executeUpdate();
            assertTrue(linhas > 0);
        }
    }

    @Test
    @Order(2)
    //CENARIO1: Listar
    void testListarPaciente() throws SQLException {
        try (Connection con = Conexao.getConexao()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM paciente WHERE nome='TesteAPI'");
            assertTrue(rs.next(), "O paciente TesteAPI deve existir no banco.");
        }
    }

    @Test
    @Order(3)
    //CENARIO3: Excluir paciente cadastrado
    void testExcluirPaciente() throws SQLException {
        try (Connection con = Conexao.getConexao()) {
            PreparedStatement pst = con.prepareStatement("DELETE FROM paciente WHERE nome=?");
            pst.setString(1, "TesteAPI");
            int linhas = pst.executeUpdate();
            assertTrue(linhas > 0);
        }
    }
}

