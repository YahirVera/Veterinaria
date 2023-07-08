package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaGestionProducto extends JFrame {
    public VentanaGestionProducto() {
        setTitle("VENTANA DE GESTIÓN DE PRODUCTOS");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JButton btnRegistrarProducto = new JButton("Registrar Producto");
        JButton btnBuscarProducto = new JButton("Buscar Producto");
        JButton btnActualizarProducto = new JButton("Actualizar Producto");
        JButton btnEliminarProducto = new JButton("Eliminar Producto");
        JButton btnMostrarProducto = new JButton("Mostrar Producto");
        JButton btnComprarProducto = new JButton("Comprar Producto");

        panel.add(btnRegistrarProducto);
        panel.add(btnBuscarProducto);
        panel.add(btnActualizarProducto);
        panel.add(btnEliminarProducto);
        panel.add(btnMostrarProducto);
        panel.add(btnComprarProducto);

        add(panel, BorderLayout.CENTER);

        btnRegistrarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaRegistrarProducto ventana = new VentanaRegistrarProducto();
                ventana.setVisible(true);
            }
        });

        btnBuscarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaBuscarProducto ventana = new VentanaBuscarProducto();
                ventana.setVisible(true);
            }
        });

        btnActualizarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaActualizarProducto ventana = new VentanaActualizarProducto();
                ventana.setVisible(true);
            }
        });

        btnEliminarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaEliminarProducto ventana = new VentanaEliminarProducto();
                ventana.setVisible(true);
            }
        });

        btnMostrarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaMostrarProducto ventana = new VentanaMostrarProducto();
                ventana.setVisible(true);
            }
        });

        btnComprarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaComprarProducto ventana = new VentanaComprarProducto();
                ventana.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        VentanaGestionProducto ventana = new VentanaGestionProducto();
        ventana.setVisible(true);
    }
}