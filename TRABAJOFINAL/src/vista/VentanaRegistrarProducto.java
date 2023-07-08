package vista;

import datos.ProcedimientoProducto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaRegistrarProducto extends JFrame {
    private JTextField txtIdProducto;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtMarca;
    private JTextField txtPrecio;
    private JTextField txtStock;

    public VentanaRegistrarProducto() {
        setTitle("Registrar Producto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JLabel lblIdProducto = new JLabel("ID Producto:");
        txtIdProducto = new JTextField();
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        JLabel lblDescripcion = new JLabel("Descripción:");
        txtDescripcion = new JTextField();
        JLabel lblMarca = new JLabel("Marca:");
        txtMarca = new JTextField();
        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField();
        JLabel lblStock = new JLabel("Stock:");
        txtStock = new JTextField();
        JButton btnRegistrar = new JButton("Registrar");

        panel.add(lblIdProducto);
        panel.add(txtIdProducto);
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblDescripcion);
        panel.add(txtDescripcion);
        panel.add(lblMarca);
        panel.add(txtMarca);
        panel.add(lblPrecio);
        panel.add(txtPrecio);
        panel.add(lblStock);
        panel.add(txtStock);
        panel.add(btnRegistrar);

        add(panel, BorderLayout.CENTER);

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idProducto = txtIdProducto.getText();
                String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();
                String marca = txtMarca.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                int stock = Integer.parseInt(txtStock.getText());

                ProcedimientoProducto.registrarProducto(idProducto, nombre, descripcion, marca, precio, stock);

                JOptionPane.showMessageDialog(null, "Producto registrado correctamente");
                limpiarCampos();
            }
        });
    }

    private void limpiarCampos() {
        txtIdProducto.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtMarca.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
    }

    public static void main(String[] args) {
        VentanaRegistrarProducto ventana = new VentanaRegistrarProducto();
        ventana.setVisible(true);
    }
}