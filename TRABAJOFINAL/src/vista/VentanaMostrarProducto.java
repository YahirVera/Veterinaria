package vista;

import datos.ProcedimientoProducto;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaMostrarProducto extends JFrame {
    private JTextArea txtResultado;

    public VentanaMostrarProducto() {
        setTitle("Mostrar Productos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Lista de Productos");
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        add(panel);

        ResultSet resultado = ProcedimientoProducto.mostrarProductos();
        try {
            while (resultado.next()) {
                String idProducto = resultado.getString("idproducto");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");
                String marca = resultado.getString("marca");
                double precio = resultado.getDouble("precio");
                int stock = resultado.getInt("stock");

                txtResultado.append("ID Producto: " + idProducto + "\n");
                txtResultado.append("Nombre: " + nombre + "\n");
                txtResultado.append("Descripción: " + descripcion + "\n");
                txtResultado.append("Marca: " + marca + "\n");
                txtResultado.append("Precio: " + precio + "\n");
                txtResultado.append("Stock: " + stock + "\n");
                txtResultado.append("--------------------\n");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(VentanaMostrarProducto.this, "Error al mostrar productos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        VentanaMostrarProducto ventana = new VentanaMostrarProducto();
        ventana.setVisible(true);
    }
}