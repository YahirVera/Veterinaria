package vista;

import datos.ProcedimientoMascota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaBuscarMascota extends JFrame {
    private JTextField textFieldIdMascota;
    private JTextArea textAreaResultado;

    public VentanaBuscarMascota() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Buscar Mascota");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(2, 2));

        JLabel labelIdMascota = new JLabel("ID Mascota:");
        textFieldIdMascota = new JTextField();

        panelFormulario.add(labelIdMascota);
        panelFormulario.add(textFieldIdMascota);

        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idMascota = textFieldIdMascota.getText();

                ResultSet resultado = ProcedimientoMascota.buscarMascota(idMascota);
                if (resultado != null) {
                    try {
                        if (resultado.next()) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("ID Mascota: ").append(resultado.getString("idmascota")).append("\n");
                            sb.append("Raza: ").append(resultado.getString("raza")).append("\n");
                            sb.append("Fecha de Nacimiento: ").append(resultado.getString("fecha_nacimiento")).append("\n");
                            sb.append("Cliente ID: ").append(resultado.getString("cliente_idcliente")).append("\n");
                            sb.append("Sexo: ").append(resultado.getString("sexo")).append("\n");
                            textAreaResultado.setText(sb.toString());
                        } else {
                            textAreaResultado.setText("No se encontró la mascota con el ID especificado");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al obtener los datos de la mascota: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error al buscar la mascota");
                }
            }
        });

        textAreaResultado = new JTextArea();
        textAreaResultado.setEditable(false);

        add(panelFormulario, BorderLayout.NORTH);
        add(new JScrollPane(textAreaResultado), BorderLayout.CENTER);
        add(botonBuscar, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        VentanaBuscarMascota ventana = new VentanaBuscarMascota();
        ventana.setVisible(true);
    }
}