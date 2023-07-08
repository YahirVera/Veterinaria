package vista;

import javax.swing.*;

import datos.Conexion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VentanaEliminarUsuario extends JFrame {
    private JLabel lblIdCliente;
    private JTextField txtIdCliente;
    private JButton btnEliminar;

    public VentanaEliminarUsuario() {
        setTitle("Eliminar Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        lblIdCliente = new JLabel("ID Cliente:");
        txtIdCliente = new JTextField(10);
        btnEliminar = new JButton("Eliminar");

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });

        add(lblIdCliente);
        add(txtIdCliente);
        add(btnEliminar);

        setVisible(true);
    }

    private void eliminarUsuario() {
        String idCliente = txtIdCliente.getText();

        try {
            Connection conn = Conexion.obtenerConexion();
            CallableStatement stmt = conn.prepareCall("{CALL EliminarCliente(?)}");
            stmt.setString(1, idCliente);
            stmt.executeUpdate();
            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar usuario"+ ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaEliminarUsuario();
            }
        });
    }
}