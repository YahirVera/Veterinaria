package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("PETCOMPANION");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JLabel lblTitulo = new JLabel("PETCOMPANION");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnGestionarProductos = new JButton("Gestionar Productos");
        btnGestionarProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaGestionProducto ventana = new VentanaGestionProducto();
                ventana.setVisible(true);
            }
        });
        panelBotones.add(btnGestionarProductos);

        JButton btnGestionarUsuarios = new JButton("Gestionar Usuarios");
        btnGestionarUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaGestionUsuario ventana = new VentanaGestionUsuario();
                ventana.setVisible(true);
            }
        });
        panelBotones.add(btnGestionarUsuarios);

        JButton btnAdoptarGestionarMascotas = new JButton("Adoptar/Gestionar Mascotas");
        btnAdoptarGestionarMascotas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaGestionMascota ventana = new VentanaGestionMascota();
                ventana.setVisible(true);
            }
        });
        panelBotones.add(btnAdoptarGestionarMascotas);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        getContentPane().add(panelPrincipal);
    }

    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
    }
}