package vista;

import datos.ProcedimientoProducto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaEliminarProducto extends JFrame {
    private JTextField txtIdProducto;
    private JButton btnBuscar;
    private JButton btnEliminar;

    public VentanaEliminarProducto() {
        setTitle("Eliminar Producto");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel lblIdProducto = new JLabel("ID Producto:");
        txtIdProducto = new JTextField();
        btnBuscar = new JButton("Buscar");
        btnEliminar = new JButton("Eliminar");

        panel.add(lblIdProducto);
        panel.add(txtIdProducto);
        panel.add(btnBuscar);
        panel.add(btnEliminar);

        add(panel, BorderLayout.CENTER);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idProducto = txtIdProducto.getText();
                ResultSet resultado = ProcedimientoProducto.buscarProducto(idProducto);
                try {
                    if (resultado.next()) {
                        JOptionPane.showMessageDialog(VentanaEliminarProducto.this, "Producto encontrado:\n" +
                                "ID Producto: " + resultado.getString("idproducto") + "\n" +
                                "Nombre: " + resultado.getString("nombre") + "\n" +
                                "Descripción: " + resultado.getString("descripcion") + "\n" +
                                "Marca: " + resultado.getString("marca") + "\n" +
                                "Precio: " + resultado.getDouble("precio") + "\n" +
                                "Stock: " + resultado.getInt("stock"), "Producto encontrado", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(VentanaEliminarProducto.this, "No se encontró el producto con ID: " + idProducto, "Producto no encontrado", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(VentanaEliminarProducto.this, "Error al buscar producto: " + ex.getMessage(), "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idProducto = txtIdProducto.getText();
                ProcedimientoProducto.eliminarProducto(idProducto);

                JOptionPane.showMessageDialog(VentanaEliminarProducto.this, "Producto eliminado correctamente", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);

                txtIdProducto.setText("");
            }
        });
    }

    public static void main(String[] args) {
        VentanaEliminarProducto ventana = new VentanaEliminarProducto();
        ventana.setVisible(true);
    }
}