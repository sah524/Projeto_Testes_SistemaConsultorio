package br.com.consultorio.e2e;

import org.junit.jupiter.api.*;
import java.sql.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

//CENARIO: Simular todo fluxo do sistema
public class SistemaE2ETest {

    private static Connection connection;
    private static int userId;
    private static int pacienteId;
    private static int consultaId;

    @BeforeAll
    static void setup() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/consultorio_db",
                "root",
                "sarah"
            );
            System.out.println("Conexao com banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco: " + e.getMessage());
        }
    }

    @Test
    @Order(1)
    void fluxoCompletoCLITest() throws SQLException {
        // Gerar login aleatório
        String loginAleatorio = "testeUser_" + System.currentTimeMillis();

        // Cadastrar usuário
        PreparedStatement psUser = connection.prepareStatement(
            "INSERT INTO usuario (login, senha) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        psUser.setString(1, loginAleatorio);
        psUser.setString(2, "12345");
        psUser.executeUpdate();
        ResultSet rsUser = psUser.getGeneratedKeys();
        Assertions.assertTrue(rsUser.next());
        userId = rsUser.getInt(1);
        System.out.println("Usuario cadastrado com sucesso: " + loginAleatorio);

        // Cadastrar paciente
        PreparedStatement psPaciente = connection.prepareStatement(
            "INSERT INTO paciente (nome, cpf, telefone) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        psPaciente.setString(1, "Paciente Teste");
        psPaciente.setString(2, "11122233344");
        psPaciente.setString(3, "61999990000");
        psPaciente.executeUpdate();
        ResultSet rsPaciente = psPaciente.getGeneratedKeys();
        Assertions.assertTrue(rsPaciente.next());
        pacienteId = rsPaciente.getInt(1);
        System.out.println("Paciente cadastrado com sucesso: Paciente Teste");

        // Cadastrar consulta
        PreparedStatement psConsulta = connection.prepareStatement(
            "INSERT INTO consulta (data_consulta, hora, tipo, consultorio, link_video, paciente_id) VALUES (?, ?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS);
        psConsulta.setString(1, "2025-10-20");
        psConsulta.setString(2, "14:00");
        psConsulta.setString(3, "Retorno");
        psConsulta.setInt(4, 101);
        psConsulta.setString(5, "https://meet.test/consulta");
        psConsulta.setInt(6, pacienteId);
        psConsulta.executeUpdate();
        ResultSet rsConsulta = psConsulta.getGeneratedKeys();
        Assertions.assertTrue(rsConsulta.next());
        consultaId = rsConsulta.getInt(1);
        System.out.println("Consulta cadastrada com sucesso (ID: " + consultaId + ")");
    }
    
    @AfterAll
    static void cleanup() {
        try {
            // Apagar consulta
            if (consultaId != 0) {
                connection.createStatement().executeUpdate("DELETE FROM consulta WHERE id = " + consultaId);
                System.out.println("Consulta de teste removida com sucesso!");
            }

            // Apagar paciente
            if (pacienteId != 0) {
                connection.createStatement().executeUpdate("DELETE FROM paciente WHERE id = " + pacienteId);
                System.out.println("Paciente de teste removido com sucesso!");
            }

            // Apagar usuário
            if (userId != 0) {
                connection.createStatement().executeUpdate("DELETE FROM usuario WHERE id = " + userId);
                System.out.println("Usuario de teste removido com sucesso!");
            }

            connection.close();
            System.out.println("Conexao encerrada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao limpar dados de teste: " + e.getMessage());
        }
    }
}
