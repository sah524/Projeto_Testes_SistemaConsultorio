package br.com.consultorio.agendamento;

import br.com.consultorio.config.Conexao;
import br.com.consultorio.paciente.PacienteMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsultaMenu {

    private Scanner sc = new Scanner(System.in);
    private PacienteMenu pacienteMenu = new PacienteMenu();

    public void iniciar() {
        System.out.println("====================================");
        System.out.println("       CADASTRO DE CONSULTA");
        System.out.println("====================================");

        System.out.print("Digite o ID do paciente: ");
        int idPaciente = Integer.parseInt(sc.nextLine());

        if (!pacienteMenu.existePaciente(idPaciente)) {
            System.out.println("Paciente nao encontrado.");
            return;
        }

        String data;
        while (true) {
            System.out.print("Digite a data da consulta (dd/MM/yyyy): ");
            data = sc.nextLine().trim();
            if (!dataValida(data)) {
                System.out.println("Data invalida. Use o formato dd/MM/yyyy e valores reais.");
            } else {
                break;
            }
        }

        String hora;
        while (true) {
            System.out.print("Digite a hora da consulta (HH:mm): ");
            hora = sc.nextLine().trim();
            if (!horaValida(hora)) {
                System.out.println("Hora invalida. Use formato HH:mm com valores reais.");
            } else {
                break;
            }
        }

        String tipo;
        while (true) {
            System.out.print("Digite o tipo da consulta (Presencial/Online): ");
            tipo = sc.nextLine().trim();
            if (!tipo.equalsIgnoreCase("Presencial") && !tipo.equalsIgnoreCase("Online")) {
                System.out.println("Tipo de consulta invalido.");
            } else {
                break;
            }
        }

        String linkVideo = null;
        if (tipo.equalsIgnoreCase("Online")) {
            linkVideo = gerarLinkVideo();
        }

        try (Connection con = Conexao.getConexao()) {
            String sql = "INSERT INTO consulta(data_consulta, hora, tipo, paciente_id, link_video) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, data);
            ps.setString(2, hora);
            ps.setString(3, tipo);
            ps.setInt(4, idPaciente);
            ps.setString(5, linkVideo);
            ps.executeUpdate();
            System.out.println("Consulta cadastrada com sucesso!");
            if (linkVideo != null) {
                System.out.println("Link da consulta online: " + linkVideo);
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
        // Gera um link simples aleatório (pode ser melhorado depois)
        return "https://meet.example.com/" + System.currentTimeMillis();
    }
}
