package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
	
	public String driver = "com.mysql.cj.jdbc.Driver";
	public String hostname = "localhost";
	public String puerto = "3306";
	public String nombreDB = "universidad";
	public String username = "root";
	public String password = "PGB44pgb@@";
	public String url = "jdbc:mysql://" + hostname + ":" + puerto + "/" + nombreDB + "?useSSL=false";
	
	public Connection conectarBD() {
		
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection(url, username, password);
			System.out.println("Conectado al servicio");
		}
		catch(SQLException e) {
			System.out.println("No se pudo conectar al servicio");
		}
		
		return conexion;
	}
	
	

}
