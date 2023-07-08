package vista;


import javax.swing.*;

import datos.Conexion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VentanaActualizarUsuario extends JFrame {
    private JLabel lblIdCliente;
    private JTextField txtIdCliente;
    private JButton btnBuscar;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblApellido;
    private JTextField txtApellido;
    private JLabel lblDni;
    private JTextField txtDni;
    private JLabel lblTelefono;
    private JTextField txtTelefono;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JLabel lblTipo;
    private JTextField txtTipo;
    private JButton btnActualizar;

    public VentanaActualizarUsuario() {
        setTitle("Actualizar Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(8, 2, 10, 10));

        lblIdCliente = new JLabel("ID Cliente:");
        txtIdCliente = new JTextField(10);
        btnBuscar = new JButton("Buscar");

        lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(20);
        lblApellido = new JLabel("Apellido:");
        txtApellido = new JTextField(20);
        lblDni = new JLabel("DNI:");
        txtDni = new JTextField(20);
        lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField(20);
        lblEmail = new JLabel("Email:");
        txtEmail = new JTextField(20);
        lblTipo = new JLabel("Tipo:");
        txtTipo = new JTextField(20);
        btnActualizar = new JButton("Actualizar");

        panelFormulario.add(lblIdCliente);
        panelFormulario.add(txtIdCliente);
        panelFormulario.add(new JLabel()); // Espacio en blanco
        panelFormulario.add(btnBuscar);
        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(lblApellido);
        panelFormulario.add(txtApellido);
        panelFormulario.add(lblDni);
        panelFormulario.add(txtDni);
        panelFormulario.add(lblTelefono);
        panelFormulario.add(txtTelefono);
        panelFormulario.add(lblEmail);
        panelFormulario.add(txtEmail);
        panelFormulario.add(lblTipo);
        panelFormulario.add(txtTipo);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnActualizar);

        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuario();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });

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

                txtNombre.setText(nombre);
                txtApellido.setText(apellido);
                txtDni.setText(dni);
                txtTelefono.setText(telefono);
                txtEmail.setText(email);
                txtTipo.setText(tipo);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al buscar usuario");
        }
    }

    private void actualizarUsuario() {
        String idCliente = txtIdCliente.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String dni = txtDni.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        String tipo = txtTipo.getText();

        try {
            Connection conn = Conexion.obtenerConexion();
            CallableStatement stmt = conn.prepareCall("{CALL ActualizarCliente(?, ?, ?, ?, ?, ?, ?)}");
            stmt.setString(1, idCliente);
            stmt.setString(2, nombre);
            stmt.setString(3, apellido);
            stmt.setString(4, dni);
            stmt.setString(5, telefono);
            stmt.setString(6, email);
            stmt.setString(7, tipo);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");

            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaActualizarUsuario();
            }
        });
    }
}