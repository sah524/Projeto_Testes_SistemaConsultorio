package br.com.consultorio.config;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste de integração com o banco de dados.
 *Para verificar se a classe Conexao esta retornando uma conexao valida.
 */
public class ConexaoTest {

    @Test
    void testConexaoComBanco() {
        try (Connection con = Conexao.getConexao()) {
           
            assertNotNull(con, "A conexao nao deve ser nula.");
            
            assertFalse(con.isClosed(), "A conexao deve estar aberta.");
            
            System.out.println("Conexao com o banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            fail(" Falha ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
