package vista;

import datos.ProcedimientoMascota;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaMostrarMascota extends JFrame {
    private JTextArea textAreaMascotas;

    public VentanaMostrarMascota() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mostrar Mascotas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        textAreaMascotas = new JTextArea();
        textAreaMascotas.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaMascotas);

        JButton botonMostrar = new JButton("Mostrar");
        botonMostrar.addActionListener(e -> mostrarMascotas());

        add(scrollPane, BorderLayout.CENTER);
        add(botonMostrar, BorderLayout.SOUTH);
    }

    private void mostrarMascotas() {
        ResultSet resultado = ProcedimientoMascota.mostrarMascota();
        if (resultado != null) {
            try {
                StringBuilder sb = new StringBuilder();
                while (resultado.next()) {
                    String idMascota = resultado.getString("idmascota");
                    String raza = resultado.getString("raza");
                    String fechaNacimiento = resultado.getString("fecha_nacimiento");
                    String clienteId = resultado.getString("cliente_idcliente");
                    String sexo = resultado.getString("sexo");

                    sb.append("ID Mascota: ").append(idMascota).append("\n");
                    sb.append("Raza: ").append(raza).append("\n");
                    sb.append("Fecha de Nacimiento: ").append(fechaNacimiento).append("\n");
                    sb.append("ID Cliente: ").append(clienteId).append("\n");
                    sb.append("Sexo: ").append(sexo).append("\n");
                    sb.append("------------------------------\n");
                }
                textAreaMascotas.setText(sb.toString());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al mostrar las mascotas: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error al mostrar las mascotas");
        }
    }

    public static void main(String[] args) {
        VentanaMostrarMascota ventana = new VentanaMostrarMascota();
        ventana.setVisible(true);
    }
}