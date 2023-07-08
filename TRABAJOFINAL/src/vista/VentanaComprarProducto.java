package vista;

import datos.ProcedimientoProducto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaComprarProducto extends JFrame {
    private JTextField txtIdProducto;
    private JTextField txtCantidad;
    private JTextArea txtResultado;

    public VentanaComprarProducto() {
        setTitle("Comprar Producto");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel lblIdProducto = new JLabel("ID Producto:");
        txtIdProducto = new JTextField();
        JLabel lblCantidad = new JLabel("Cantidad:");
        txtCantidad = new JTextField();
        JButton btnComprar = new JButton("Comprar");
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);

        panel.add(lblIdProducto);
        panel.add(txtIdProducto);
        panel.add(lblCantidad);
        panel.add(txtCantidad);
        panel.add(btnComprar);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        btnComprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idProducto = txtIdProducto.getText();
                int cantidad = Integer.parseInt(txtCantidad.getText());

                ResultSet resultado = ProcedimientoProducto.comprarProducto(idProducto, cantidad);
                try {
                    if (resultado.next()) {
                        String mensaje = "Compra realizada correctamente.\n" +
                                "ID Producto: " + resultado.getString("idProducto") + "\n" +
                                "Nombre: " + resultado.getString("nombre") + "\n" +
                                "Descripción: " + resultado.getString("descripcion") + "\n" +
                                "Marca: " + resultado.getString("marca") + "\n" +
                                "Precio: " + resultado.getDouble("precio") + "\n" +
                                "Stock: " + resultado.getInt("stock");
                        txtResultado.setText(mensaje);
                    } else {
                        txtResultado.setText("No se pudo realizar la compra. Verifica el ID del producto y la cantidad.");
                    }
                } catch (SQLException ex) {
                    txtResultado.setText("Error al realizar la compra: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        VentanaComprarProducto ventana = new VentanaComprarProducto();
        ventana.setVisible(true);
    }
}
