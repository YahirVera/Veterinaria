package vista;

import datos.ProcedimientoProducto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaActualizarProducto extends JFrame {
    private JTextField txtIdProducto;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtMarca;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JButton btnBuscar;
    private JButton btnActualizar;

    public VentanaActualizarProducto() {
        setTitle("Actualizar Producto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JLabel lblIdProducto = new JLabel("ID Producto:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblDescripcion = new JLabel("Descripción:");
        JLabel lblMarca = new JLabel("Marca:");
        JLabel lblPrecio = new JLabel("Precio:");
        JLabel lblStock = new JLabel("Stock:");

        txtIdProducto = new JTextField();
        txtNombre = new JTextField();
        txtDescripcion = new JTextField();
        txtMarca = new JTextField();
        txtPrecio = new JTextField();
        txtStock = new JTextField();

        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");

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
        panel.add(btnBuscar);
        panel.add(btnActualizar);

        add(panel, BorderLayout.NORTH);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idProducto = txtIdProducto.getText();
                ResultSet resultado = ProcedimientoProducto.buscarProducto(idProducto);
                try {
                    if (resultado.next()) {
                        txtNombre.setText(resultado.getString("nombre"));
                        txtDescripcion.setText(resultado.getString("descripcion"));
                        txtMarca.setText(resultado.getString("marca"));
                        txtPrecio.setText(String.valueOf(resultado.getDouble("precio")));
                        txtStock.setText(String.valueOf(resultado.getInt("stock")));
                    } else {
                        JOptionPane.showMessageDialog(VentanaActualizarProducto.this, "No se encontró el producto con ID: " + idProducto, "Producto no encontrado", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(VentanaActualizarProducto.this, "Error al buscar producto: " + ex.getMessage(), "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idProducto = txtIdProducto.getText();
                String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();
                String marca = txtMarca.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                int stock = Integer.parseInt(txtStock.getText());

                ProcedimientoProducto.actualizarProducto(idProducto, nombre, descripcion, marca, precio, stock);

                JOptionPane.showMessageDialog(VentanaActualizarProducto.this, "Producto actualizado correctamente", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);

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
        VentanaActualizarProducto ventana = new VentanaActualizarProducto();
        ventana.setVisible(true);
    }
}