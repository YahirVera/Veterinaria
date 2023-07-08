package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ProcedimientoAdopcion {
    
    public static void crearAdopcion(String idAdopcion, String idMascota, String idCliente) {
        try {
            Connection conn = Conexion.obtenerConexion();
            CallableStatement cstmt = conn.prepareCall("{CALL CrearAdopcion(?, ?, ?)}");
            cstmt.setString(1, idAdopcion);
            cstmt.setString(2, idMascota);
            cstmt.setString(3, idCliente);
            cstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error al crear la adopción: " + e.getMessage());
        }
    }
    
}