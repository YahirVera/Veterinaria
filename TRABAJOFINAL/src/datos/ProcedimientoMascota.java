package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcedimientoMascota {
	public static void registrarMascota(String idmascota, String raza, String fechaNacimiento, String sexo) {
        try {
            Connection conn = Conexion.obtenerConexion();
            CallableStatement cstmt = conn.prepareCall("{CALL RegistrarMascota(?, ?, ?, ?)}");
            cstmt.setString(1, idmascota);
            cstmt.setString(2, raza);
            cstmt.setString(3, fechaNacimiento);
            cstmt.setString(4, sexo);
            cstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error al registrar la mascota: " + e.getMessage());
        }
    }


	 public static ResultSet mostrarMascota() {
	        ResultSet resultado = null;

	        try {
	            Connection conn = Conexion.obtenerConexion();
	            CallableStatement cstmt = conn.prepareCall("{CALL MostrarMascota()}");
	            resultado = cstmt.executeQuery();
	        } catch (SQLException e) {
	            System.err.println("Error al mostrar las mascotas: " + e.getMessage());
	        }

	        return resultado;
	    }
    
    
    public static ResultSet buscarMascota(String idMascota) {
        ResultSet resultado = null;
        try {
            Connection conn = Conexion.obtenerConexion();
            CallableStatement cstmt = conn.prepareCall("{CALL BuscarMascota(?)}");
            cstmt.setString(1, idMascota);
            resultado = cstmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al buscar la mascota: " + e.getMessage());
        }
        return resultado;
    }


    public static void actualizarMascota(String idMascota, String raza, String fechaNacimiento, String clienteId, String sexo) {
        try {
            Connection conn = Conexion.obtenerConexion();
            CallableStatement cstmt = conn.prepareCall("{CALL ActualizarMascota(?, ?, ?, ?, ?)}");
            cstmt.setString(1, idMascota);
            cstmt.setString(2, raza);
            cstmt.setString(3, fechaNacimiento);
            cstmt.setString(4, clienteId);
            cstmt.setString(5, sexo);
            cstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error al actualizar la mascota: " + e.getMessage());
        }
    }
}