package vista;

import datos.ProcedimientoProducto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaBuscarProducto extends JFrame {
    private JTextField txtIdProducto;
    private JTextArea txtResultado;

    public VentanaBuscarProducto() {
        setTitle("Buscar Producto");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel lblIdProducto = new JLabel("ID Producto:");
        txtIdProducto = new JTextField();
        JButton btnBuscar = new JButton("Buscar");
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);

        panel.add(lblIdProducto);
        panel.add(txtIdProducto);
        panel.add(btnBuscar);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idProducto = txtIdProducto.getText();
                ResultSet resultado = ProcedimientoProducto.buscarProducto(idProducto);
                try {
                    if (resultado.next()) {
                        String infoProducto = "ID Producto: " + resultado.getString("idproducto") + "\n";
                        infoProducto += "Nombre: " + resultado.getString("nombre") + "\n";
                        infoProducto += "Descripción: " + resultado.getString("descripcion") + "\n";
                        infoProducto += "Marca: " + resultado.getString("marca") + "\n";
                        infoProducto += "Precio: " + resultado.getDouble("precio") + "\n";
                        infoProducto += "Stock: " + resultado.getInt("stock");
                        txtResultado.setText(infoProducto);
                    } else {
                        txtResultado.setText("No se encontró el producto con ID: " + idProducto);
                    }
                } catch (SQLException ex) {
                    txtResultado.setText("Error al buscar producto: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        VentanaBuscarProducto ventana = new VentanaBuscarProducto();
        ventana.setVisible(true);
    }
}