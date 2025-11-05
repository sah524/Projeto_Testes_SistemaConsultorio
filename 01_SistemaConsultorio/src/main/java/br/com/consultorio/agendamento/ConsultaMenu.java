package br.com.consultorio.agendamento;

import br.com.consultorio.config.Conexao;
import br.com.consultorio.paciente.PacienteMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConsultaMenu {

    private Scanner sc = new Scanner(System.in);
    private PacienteMenu pacienteMenu = new PacienteMenu();

    public void iniciar() {
        System.out.println("====================================");
        System.out.println("       CADASTRO DE CONSULTA");
        System.out.println("====================================");

        int idPaciente;
        while (true) {
            System.out.print("Digite o ID do paciente: ");
            String idPacienteStr = sc.nextLine().trim();
            try {
                idPaciente = Integer.parseInt(idPacienteStr);
                if (!pacienteMenu.existePaciente(idPaciente)) {
                    System.out.println("Paciente nao encontrado.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID do paciente invalido.");
            }
        }

        String data;
        do {
            System.out.print("Digite a data da consulta (dd/MM/yyyy): ");
            data = sc.nextLine().trim();
            if (!dataValida(data)) {
                System.out.println("Data invalida. Use o formato dd/MM/yyyy e valores reais.");
            }
        } while (!dataValida(data));

        String hora;
        do {
            System.out.print("Digite a hora da consulta (HH:mm): ");
            hora = sc.nextLine().trim();
            if (!horaValida(hora)) {
                System.out.println("Hora invalida. Use formato HH:mm com valores reais.");
            }
        } while (!horaValida(hora));

        String tipo;
        do {
            System.out.print("Digite o tipo da consulta (Presencial/Online): ");
            tipo = sc.nextLine().trim();
            if (!tipo.equalsIgnoreCase("Presencial") && !tipo.equalsIgnoreCase("Online")) {
                System.out.println("Tipo de consulta invalido.");
            }
        } while (!tipo.equalsIgnoreCase("Presencial") && !tipo.equalsIgnoreCase("Online"));

        Integer consultorio = null;
        if (tipo.equalsIgnoreCase("Presencial")) {
            do {
                System.out.print("Digite o numero do consultorio (0 a 10): ");
                String consultorioStr = sc.nextLine().trim();
                try {
                    consultorio = Integer.parseInt(consultorioStr);
                    if (consultorio < 0 || consultorio > 10) {
                        System.out.println("Numero de consultorio invalido. Deve ser de 0 a 10.");
                        consultorio = null;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada invalida. Digite um numero de 0 a 10.");
                }
            } while (consultorio == null);
        }

        String linkVideo = null;
        if (tipo.equalsIgnoreCase("Online")) {
            linkVideo = gerarLinkVideo();
        }

        try (Connection con = Conexao.getConexao()) {
            String sql = "INSERT INTO consulta(paciente_id, data_consulta, hora, tipo, link_video, consultorio) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPaciente);
            ps.setString(2, data);
            ps.setString(3, hora);
            ps.setString(4, tipo);
            ps.setString(5, linkVideo);
            if (consultorio != null) {
                ps.setInt(6, consultorio);
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }
            ps.executeUpdate();

            System.out.println("Consulta cadastrada com sucesso!");
            if (linkVideo != null) {
                System.out.println("Link de video gerado: " + linkVideo);
            }
            if (consultorio != null) {
                System.out.println("Consultorio: " + consultorio);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar consulta: " + e.getMessage());
        }
    }

    private boolean dataValida(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date d = sdf.parse(data);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean horaValida(String hora) {
        String[] partes = hora.split(":");
        if (partes.length != 2) return false;
        try {
            int h = Integer.parseInt(partes[0]);
            int m = Integer.parseInt(partes[1]);
            return h >= 0 && h < 24 && m >= 0 && m < 60;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String gerarLinkVideo() {
        return "https://meet.example.com/" + System.currentTimeMillis();
    }

    public void listarConsultas() {
        System.out.println("====================================");
        System.out.println("        LISTA DE CONSULTAS");
        System.out.println("====================================");

        String sql = "SELECT * FROM consulta";

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean encontrou = false;

            while (rs.next()) {
                encontrou = true;
                System.out.println("ID Consulta: " + rs.getInt("id"));
                System.out.println("ID Paciente: " + rs.getInt("paciente_id"));
                System.out.println("Data: " + rs.getString("data_consulta"));
                System.out.println("Hora: " + rs.getString("hora"));
                System.out.println("Tipo: " + rs.getString("tipo"));
                System.out.println("Link Video: " + rs.getString("link_video"));
                System.out.println("Consultorio: " + rs.getInt("consultorio"));
                System.out.println("------------------------------------");
            }

            if (!encontrou) {
                System.out.println("Nenhuma consulta encontrada");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas: " + e.getMessage());
        }
    }

    public void atualizarConsulta() {
        System.out.println("====================================");
        System.out.println("         ATUALIZAR CONSULTA");
        System.out.println("====================================");

        System.out.print("Digite o ID da consulta que deseja atualizar: ");
        String idStr = sc.nextLine().trim();

        if (idStr.isEmpty()) {
            System.out.println("Campo ID nao pode estar vazio.");
            return;
        }

        int idConsulta;
        try {
            idConsulta = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
            return;
        }

        try (Connection con = Conexao.getConexao()) {
            String verificaSql = "SELECT * FROM consulta WHERE id = ?";
            PreparedStatement psVerifica = con.prepareStatement(verificaSql);
            psVerifica.setInt(1, idConsulta);
            ResultSet rs = psVerifica.executeQuery();

            if (!rs.next()) {
                System.out.println("Consulta nao encontrada.");
                return;
            }

            String novaData;
            do {
                System.out.print("Nova data (dd/MM/yyyy): ");
                novaData = sc.nextLine().trim();
                if (!dataValida(novaData)) {
                    System.out.println("Data invalida. Tente novamente:");
                }
            } while (!dataValida(novaData));

            String novaHora;
            do {
                System.out.print("Nova hora (HH:mm): ");
                novaHora = sc.nextLine().trim();
                if (!horaValida(novaHora)) {
                    System.out.println("Hora invalida. Tente novamente:");
                }
            } while (!horaValida(novaHora));

            String novoTipo;
            do {
                System.out.print("Novo tipo (Presencial/Online): ");
                novoTipo = sc.nextLine().trim();
                if (!novoTipo.equalsIgnoreCase("Presencial") && !novoTipo.equalsIgnoreCase("Online")) {
                    System.out.println("Tipo invalido. Digite Presencial ou Online:");
                }
            } while (!novoTipo.equalsIgnoreCase("Presencial") && !novoTipo.equalsIgnoreCase("Online"));

            Integer consultorio = null;
            if (novoTipo.equalsIgnoreCase("Presencial")) {
                do {
                    System.out.print("Digite o numero do consultorio (0 a 10): ");
                    String consultorioStr = sc.nextLine().trim();
                    try {
                        consultorio = Integer.parseInt(consultorioStr);
                        if (consultorio < 0 || consultorio > 10) {
                            System.out.println("Numero de consultorio invalido. Deve ser de 0 a 10.");
                            consultorio = null;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada invalida. Digite um numero de 0 a 10.");
                    }
                } while (consultorio == null);
            }

            String linkVideo = null;
            if (novoTipo.equalsIgnoreCase("Online")) {
                linkVideo = gerarLinkVideo();
            }

            String updateSql = "UPDATE consulta SET data_consulta = ?, hora = ?, tipo = ?, link_video = ?, consultorio = ? WHERE id = ?";
            PreparedStatement psUpdate = con.prepareStatement(updateSql);
            psUpdate.setString(1, novaData);
            psUpdate.setString(2, novaHora);
            psUpdate.setString(3, novoTipo);
            psUpdate.setString(4, linkVideo);
            if (consultorio != null) {
                psUpdate.setInt(5, consultorio);
            } else {
                psUpdate.setNull(5, java.sql.Types.INTEGER);
            }
            psUpdate.setInt(6, idConsulta);
            int linhas = psUpdate.executeUpdate();

            if (linhas > 0) {
                System.out.println("Consulta atualizada com sucesso!");
                if (linkVideo != null) {
                    System.out.println("Novo link de video: " + linkVideo);
                }
                if (consultorio != null) {
                    System.out.println("Consultorio: " + consultorio);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar consulta: " + e.getMessage());
        }
    }

    public void excluirConsulta() {
        System.out.print("Digite o ID da consulta que deseja excluir: ");
        String idStr = sc.nextLine().trim();

        if (idStr.isEmpty()) {
            System.out.println("Campo ID nao pode estar vazio.");
            return;
        }

        int idConsulta;
        try {
            idConsulta = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("ID invalido.");
            return;
        }

        String sql = "DELETE FROM consulta WHERE id = ?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idConsulta);
            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                System.out.println("Consulta excluida com sucesso!");
            } else {
                System.out.println("Consulta nao encontrada!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir consulta: " + e.getMessage());
        }
    }
}
