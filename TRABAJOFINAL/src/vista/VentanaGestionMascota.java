package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaGestionMascota extends JFrame {
    public VentanaGestionMascota() {
        setTitle("Gestión de Mascotas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);

        JButton btnBuscarMascota = new JButton("Buscar Mascota");
        panel.add(btnBuscarMascota);
        btnBuscarMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaBuscarMascota ventana = new VentanaBuscarMascota();
                ventana.setVisible(true);
            }
        });

        JButton btnRegistrarMascota = new JButton("Registrar Mascota");
        panel.add(btnRegistrarMascota);
        btnRegistrarMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaRegistrarMascota ventana = new VentanaRegistrarMascota();
                ventana.setVisible(true);
            }
        });

        JButton btnActualizarMascota = new JButton("Actualizar Mascota");
        panel.add(btnActualizarMascota);
        btnActualizarMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaActualizarMascota ventana = new VentanaActualizarMascota();
                ventana.setVisible(true);
            }
        });

        JButton btnMostrarMascota = new JButton("Mostrar Mascota");
        panel.add(btnMostrarMascota);
        btnMostrarMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaMostrarMascota ventana = new VentanaMostrarMascota();
                ventana.setVisible(true);
            }
        });

        JButton btnAdoptarMascota = new JButton("Adoptar Mascota");
        panel.add(btnAdoptarMascota);
        btnAdoptarMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaCrearAdopcion ventana = new VentanaCrearAdopcion();
                ventana.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        VentanaGestionMascota ventana = new VentanaGestionMascota();
        ventana.setVisible(true);
    }
}