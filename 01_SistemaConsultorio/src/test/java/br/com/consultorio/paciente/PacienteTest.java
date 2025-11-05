package br.com.consultorio.paciente;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PacienteTest {

    @Test
    void testPaciente() {
        // Cenário 1: Verificar criação e leitura dos dados do paciente
        Paciente paciente = new Paciente("Ana Silva", "123.456.789-00", "(61) 91234-5678");
        assertEquals("Ana Silva", paciente.getNome());
        assertEquals("123.456.789-00", paciente.getCpf());
        assertEquals("(61) 91234-5678", paciente.getTelefone());

        // Cenário 2: Atualizar dados do paciente e verificar se foram alterados corretamente
        paciente.setNome("Carlos Souza");
        paciente.setTelefone("(61) 99876-5432");
        assertEquals("Carlos Souza", paciente.getNome());
        assertEquals("(61) 99876-5432", paciente.getTelefone());

        // Teste adicional: chamar método de ação do paciente
        paciente.esperarAtendimento(); // apenas garante que o método executa sem erro
    }
}
