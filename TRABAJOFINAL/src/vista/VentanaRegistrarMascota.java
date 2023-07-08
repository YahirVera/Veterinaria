package vista;

import datos.ProcedimientoMascota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistrarMascota extends JFrame {
    private JTextField textFieldIdMascota;
    private JTextField textFieldRaza;
    private JTextField textFieldFechaNacimiento;
    private JTextField textFieldSexo;

    public VentanaRegistrarMascota() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registrar Mascota");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(5, 2));

        JLabel labelIdMascota = new JLabel("ID Mascota:");
        textFieldIdMascota = new JTextField();
        JLabel labelRaza = new JLabel("Raza:");
        textFieldRaza = new JTextField();
        JLabel labelFechaNacimiento = new JLabel("Fecha de Nacimiento:");
        textFieldFechaNacimiento = new JTextField();
        JLabel labelSexo = new JLabel("Sexo:");
        textFieldSexo = new JTextField();

        panelFormulario.add(labelIdMascota);
        panelFormulario.add(textFieldIdMascota);
        panelFormulario.add(labelRaza);
        panelFormulario.add(textFieldRaza);
        panelFormulario.add(labelFechaNacimiento);
        panelFormulario.add(textFieldFechaNacimiento);
        panelFormulario.add(labelSexo);
        panelFormulario.add(textFieldSexo);

        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idMascota = textFieldIdMascota.getText();
                String raza = textFieldRaza.getText();
                String fechaNacimiento = textFieldFechaNacimiento.getText();
                String sexo = textFieldSexo.getText();

                ProcedimientoMascota.registrarMascota(idMascota, raza, fechaNacimiento, sexo);
                JOptionPane.showMessageDialog(null, "Mascota registrada exitosamente");
                limpiarCampos();
            }
        });

        add(panelFormulario, BorderLayout.CENTER);
        add(botonRegistrar, BorderLayout.SOUTH);
    }

    private void limpiarCampos() {
        textFieldIdMascota.setText("");
        textFieldRaza.setText("");
        textFieldFechaNacimiento.setText("");
        textFieldSexo.setText("");
    }

    public static void main(String[] args) {
        VentanaRegistrarMascota ventana = new VentanaRegistrarMascota();
        ventana.setVisible(true);
    }
}