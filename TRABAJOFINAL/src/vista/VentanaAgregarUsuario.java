package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import datos.Conexion;
import datos.ProcedimientoUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class VentanaAgregarUsuario extends JFrame {
  
    private JTextField txtIdCliente;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDni;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtTipo;
  
    public VentanaAgregarUsuario() {
        // Configurar la ventana
        setTitle("Agregar Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el panel principal
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        // Crear las etiquetas
        JLabel lblIdCliente = new JLabel("ID Cliente:");
        lblIdCliente.setBounds(20, 20, 80, 25);
        panel.add(lblIdCliente);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 80, 25);
        panel.add(lblNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(20, 100, 80, 25);
        panel.add(lblApellido);

        JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(20, 140, 80, 25);
        panel.add(lblDni);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 180, 80, 25);
        panel.add(lblTelefono);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 220, 80, 25);
        panel.add(lblEmail);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(20, 260, 80, 25);
        panel.add(lblTipo);

        // Crear los campos de texto
        txtIdCliente = new JTextField();
        txtIdCliente.setBounds(100, 20, 200, 25);
        panel.add(txtIdCliente);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 60, 200, 25);
        panel.add(txtNombre);

        txtApellido = new JTextField();
        txtApellido.setBounds(100, 100, 200, 25);
        panel.add(txtApellido);

        txtDni = new JTextField();
        txtDni.setBounds(100, 140, 200, 25);
        panel.add(txtDni);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(100, 180, 200, 25);
        panel.add(txtTelefono);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 220, 200, 25);
        panel.add(txtEmail);

        txtTipo = new JTextField();
        txtTipo.setBounds(100, 260, 200, 25);
        panel.add(txtTipo);

        // Crear el botón de agregar
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(150, 300, 100, 30);
        panel.add(btnAgregar);

        // Configurar el ActionListener para el botón agregar
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idCliente = txtIdCliente.getText();
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String dni = txtDni.getText();
                String telefono = txtTelefono.getText();
                String email = txtEmail.getText();
                String tipo = txtTipo.getText();

                try {
                    Connection conn = Conexion.obtenerConexion();
                    CallableStatement stmt = conn.prepareCall("{CALL AgregarCliente(?, ?, ?, ?, ?, ?, ?)}");
                    stmt.setString(1, idCliente);
                    stmt.setString(2, nombre);
                    stmt.setString(3, apellido);
                    stmt.setString(4, dni);
                    stmt.setString(5, telefono);
                    stmt.setString(6, email);
                    stmt.setString(7, tipo);

                    stmt.execute();

                    // Cerrar el statement y la conexión
                    stmt.close();
                    conn.close();

                    // Mostrar mensaje de usuario registrado
                    JOptionPane.showMessageDialog(null, "Usuario Registrado");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al agregar usuario" + ex.getMessage());
                }
            }
        });
    }
  
    public static void main(String[] args) {
        // Crear la ventana y hacerla visible
        VentanaAgregarUsuario ventana = new VentanaAgregarUsuario();
        ventana.setVisible(true);
    }
}