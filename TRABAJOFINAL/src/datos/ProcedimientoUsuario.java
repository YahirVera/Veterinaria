package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcedimientoUsuario {
    public static void agregarUsuario(String cliente_id, String nombre, String apellido, String dni, String telefono,String email, String tipo) {
        try (Connection conn = Conexion.obtenerConexion()) {
            String sql = "CALL AgregarCliente(?, ?, ?, ?, ?, ?, ?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, cliente_id);
            stmt.setString(2, nombre);
            stmt.setString(3, apellido);
            stmt.setString(4, dni);
            stmt.setString(5, telefono);
            stmt.setString(6, email);
            stmt.setString(7, tipo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarUsuario(String cliente_id) {
        try (Connection conn = Conexion.obtenerConexion()) {
            String sql = "CALL EliminarCliente(?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, cliente_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void buscarUsuario(String cliente_id) {
        try (Connection conn = Conexion.obtenerConexion()) {
            String sql = "CALL BuscarCliente(?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, cliente_id);
            stmt.executeQuery();
            // Procesar el resultado de la búsqueda
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ResultSet obtenerResultadoBuscarUsuario(String cliente_id) {
        ResultSet resultado = null;
        try (Connection conn = Conexion.obtenerConexion()) {
            String sql = "CALL BuscarCliente(?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, cliente_id);
            resultado = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public static void actualizarUsuario(String cliente_id, String nombre, String apellido, String dni, String telefono,String email, String tipo) {
        try (Connection conn = Conexion.obtenerConexion()) {
            String sql = "CALL ActualizarCliente(?, ?, ?, ?, ?, ?, ?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, cliente_id);
            stmt.setString(2, nombre);
            stmt.setString(3, apellido);
            stmt.setString(4, dni);
            stmt.setString(5, telefono);
            stmt.setString(6, email);
            stmt.setString(7, tipo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}