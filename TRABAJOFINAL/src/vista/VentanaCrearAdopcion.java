package vista;

import datos.ProcedimientoAdopcion;
import datos.ProcedimientoMascota;
import datos.ProcedimientoUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaCrearAdopcion extends JFrame {
    private JTextField textFieldIdCliente;
    private JTextField textFieldIdMascota;
    private JTextField textFieldIdAdopcion;

    public VentanaCrearAdopcion() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Crear Adopción");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(4, 2));

        JLabel labelIdCliente = new JLabel("ID Cliente:");
        textFieldIdCliente = new JTextField();
        JLabel labelIdMascota = new JLabel("ID Mascota:");
        textFieldIdMascota = new JTextField();
        JLabel labelIdAdopcion = new JLabel("ID Adopción:");
        textFieldIdAdopcion = new JTextField();

        JButton botonBuscarCliente = new JButton("Buscar Cliente");
        botonBuscarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idCliente = textFieldIdCliente.getText();
                ResultSet resultado = ProcedimientoUsuario.obtenerResultadoBuscarUsuario(idCliente);

                try {
                    if (resultado.next()) {
                        JOptionPane.showMessageDialog(null, "Cliente encontrado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al buscar cliente: " + ex.getMessage());
                }
            }
        });

        JButton botonBuscarMascota = new JButton("Buscar Mascota");
        botonBuscarMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idMascota = textFieldIdMascota.getText();
                ResultSet resultado = ProcedimientoMascota.buscarMascota(idMascota);

                try {
                    if (resultado.next()) {
                        JOptionPane.showMessageDialog(null, "Mascota encontrada");
                    } else {
                        JOptionPane.showMessageDialog(null, "Mascota no encontrada");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al buscar mascota: " + ex.getMessage());
                }
            }
        });

        panelFormulario.add(labelIdCliente);
        panelFormulario.add(textFieldIdCliente);
        panelFormulario.add(botonBuscarCliente);
        panelFormulario.add(new JPanel()); // Espacio vacío
        panelFormulario.add(labelIdMascota);
        panelFormulario.add(textFieldIdMascota);
        panelFormulario.add(botonBuscarMascota);
        panelFormulario.add(new JPanel()); // Espacio vacío
        panelFormulario.add(labelIdAdopcion);
        panelFormulario.add(textFieldIdAdopcion);

        JButton botonAdoptar = new JButton("Adoptar");
        botonAdoptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idCliente = textFieldIdCliente.getText();
                String idMascota = textFieldIdMascota.getText();
                String idAdopcion = textFieldIdAdopcion.getText();

                ProcedimientoAdopcion.crearAdopcion(idAdopcion, idMascota, idCliente);
                JOptionPane.showMessageDialog(null, "Adopción creada exitosamente");
                limpiarCampos();
            }
        });

        add(panelFormulario, BorderLayout.CENTER);
        add(botonAdoptar, BorderLayout.SOUTH);
    }

    private void limpiarCampos() {
        textFieldIdCliente.setText("");
        textFieldIdMascota.setText("");
        textFieldIdAdopcion.setText("");
    }

    public static void main(String[] args) {
        VentanaCrearAdopcion ventana = new VentanaCrearAdopcion();
        ventana.setVisible(true);
    }
}