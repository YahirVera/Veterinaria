package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcedimientoProducto {
    public static void registrarProducto(String idProducto, String nombre, String descripcion, String marca, double precio, int stock) {
        try {
            Connection conn = Conexion.obtenerConexion();
            String query = "{CALL RegistrarProducto(?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1, idProducto);
            stmt.setString(2, nombre);
            stmt.setString(3, descripcion);
            stmt.setString(4, marca);
            stmt.setDouble(5, precio);
            stmt.setInt(6, stock);
            stmt.executeUpdate();
            System.out.println("Producto registrado correctamente");
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al registrar producto: " + ex.getMessage());
        }
    }

    public static ResultSet buscarProducto(String idProducto) {
        ResultSet rs = null;
        try {
            Connection conn = Conexion.obtenerConexion();
            String query = "{CALL BuscarProducto(?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1, idProducto);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error al buscar producto: " + ex.getMessage());
        }
        return rs;
    }

    public static void actualizarProducto(String idProducto, String nombre, String descripcion, String marca, double precio, int stock) {
        try {
            Connection conn = Conexion.obtenerConexion();
            String query = "{CALL ActualizarProducto(?, ?, ?, ?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1, idProducto);
            stmt.setString(2, nombre);
            stmt.setString(3, descripcion);
            stmt.setString(4, marca);
            stmt.setDouble(5, precio);
            stmt.setInt(6, stock);
            stmt.executeUpdate();
            System.out.println("Producto actualizado correctamente");
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar producto: " + ex.getMessage());
        }
    }

    public static void eliminarProducto(String idProducto) {
        try {
            Connection conn = Conexion.obtenerConexion();
            String query = "{CALL EliminarProducto(?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1, idProducto);
            stmt.executeUpdate();
            System.out.println("Producto eliminado correctamente");
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar producto: " + ex.getMessage());
        }
    }

    public static ResultSet mostrarProductos() {
        ResultSet rs = null;
        try {
            Connection conn = Conexion.obtenerConexion();
            String query = "{CALL MostrarProductos()}";
            CallableStatement stmt = conn.prepareCall(query);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error al mostrar productos: " + ex.getMessage());
        }
        return rs;
    }

    public static ResultSet comprarProducto(String idProducto, int cantidad) {
        try {
            Connection conn = Conexion.obtenerConexion();
            String query = "{CALL ComprarProducto(?, ?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1, idProducto);
            stmt.setInt(2, cantidad);
            stmt.executeUpdate();
            System.out.println("Compra realizada correctamente");
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al comprar producto: " + ex.getMessage());
        }
		return null;
    }
}