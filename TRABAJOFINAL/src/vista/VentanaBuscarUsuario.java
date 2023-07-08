package vista;

import javax.swing.*;

import datos.Conexion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VentanaBuscarUsuario extends JFrame {
    private JLabel lblIdCliente;
    private JTextField txtIdCliente;
    private JButton btnBuscar;

    public VentanaBuscarUsuario() {
        setTitle("Buscar Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        lblIdCliente = new JLabel("ID Cliente:");
        txtIdCliente = new JTextField(10);
        btnBuscar = new JButton("Buscar");

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });

        add(lblIdCliente);
        add(txtIdCliente);
        add(btnBuscar);

        setVisible(true);
    }

    private void buscarUsuario() {
        String idCliente = txtIdCliente.getText();

        try {
            Connection conn = Conexion.obtenerConexion();
            CallableStatement stmt = conn.prepareCall("{CALL BuscarCliente(?)}");
            stmt.setString(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String dni = rs.getString("dni");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String tipo = rs.getString("tipo");

                JOptionPane.showMessageDialog(null, "Información del cliente:\n" +
                        "Nombre: " + nombre + "\n" +
                        "Apellido: " + apellido + "\n" +
                        "DNI: " + dni + "\n" +
                        "Teléfono: " + telefono + "\n" +
                        "Email: " + email + "\n" +
                        "Tipo: " + tipo);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al buscar usuario " + ex.getMessage() );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaBuscarUsuario();
            }
        });
    }
}