package vista;

import datos.ProcedimientoMascota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaActualizarMascota extends JFrame {
    private JTextField textFieldIdMascota;
    private JTextField textFieldRaza;
    private JTextField textFieldFechaNacimiento;
    private JTextField textFieldClienteId;
    private JTextField textFieldSexo;

    public VentanaActualizarMascota() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Actualizar Mascota");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(6, 2));

        JLabel labelIdMascota = new JLabel("ID Mascota:");
        textFieldIdMascota = new JTextField();
        JLabel labelRaza = new JLabel("Raza:");
        textFieldRaza = new JTextField();
        JLabel labelFechaNacimiento = new JLabel("Fecha de Nacimiento:");
        textFieldFechaNacimiento = new JTextField();
        JLabel labelClienteId = new JLabel("ID Cliente:");
        textFieldClienteId = new JTextField();
        JLabel labelSexo = new JLabel("Sexo:");
        textFieldSexo = new JTextField();

        panelFormulario.add(labelIdMascota);
        panelFormulario.add(textFieldIdMascota);
        panelFormulario.add(labelRaza);
        panelFormulario.add(textFieldRaza);
        panelFormulario.add(labelFechaNacimiento);
        panelFormulario.add(textFieldFechaNacimiento);
        panelFormulario.add(labelClienteId);
        panelFormulario.add(textFieldClienteId);
        panelFormulario.add(labelSexo);
        panelFormulario.add(textFieldSexo);

        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idMascota = textFieldIdMascota.getText();

                ResultSet resultado = ProcedimientoMascota.buscarMascota(idMascota);
                if (resultado != null) {
                    try {
                        if (resultado.next()) {
                            textFieldRaza.setText(resultado.getString("raza"));
                            textFieldFechaNacimiento.setText(resultado.getString("fecha_nacimiento"));
                            textFieldClienteId.setText(resultado.getString("cliente_idcliente"));
                            textFieldSexo.setText(resultado.getString("sexo"));
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró la mascota con el ID especificado");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al obtener los datos de la mascota: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error al buscar la mascota");
                }
            }
        });

        JButton botonActualizar = new JButton("Actualizar");
        botonActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idMascota = textFieldIdMascota.getText();
                String raza = textFieldRaza.getText();
                String fechaNacimiento = textFieldFechaNacimiento.getText();
                String clienteId = textFieldClienteId.getText();
                String sexo = textFieldSexo.getText();

                ProcedimientoMascota.actualizarMascota(idMascota, raza, fechaNacimiento, clienteId, sexo);
                JOptionPane.showMessageDialog(null, "Mascota actualizada exitosamente");
                limpiarCampos();
            }
        });

        add(panelFormulario, BorderLayout.CENTER);
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonBuscar);
        panelBotones.add(botonActualizar);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void limpiarCampos() {
        textFieldIdMascota.setText("");
        textFieldRaza.setText("");
        textFieldFechaNacimiento.setText("");
        textFieldClienteId.setText("");
        textFieldSexo.setText("");
    }

    public static void main(String[] args) {
        VentanaActualizarMascota ventana = new VentanaActualizarMascota();
        ventana.setVisible(true);
    }
}