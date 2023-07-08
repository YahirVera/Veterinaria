package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public static String driver="com.mysql.cj.jdbc.Driver";
	public static String base="jdbc:mysql://localhost:3306/petcompanion";
	public static String usuario="root";
	public static String clave="";
	public static Connection conn;
	
	public void conectar() throws Exception {
		Class.forName(driver);
		conn=DriverManager.getConnection(
				base,usuario,clave);

	}
    public void desconectar() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
    
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(base, usuario, clave);
    }
}