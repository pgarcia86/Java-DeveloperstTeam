package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
	
	public String driver = "com.mysql.cj.jdbc.Driver";
	public String hostname = "localhost";
	public String puerto = "3306";
	public String nombreDB;
	public String username = "root";
	public String password = "PGB44pgb@@";
	
	public Connection conectarNuevaBD() {
		String url = "jdbc:mysql://" + hostname + ":" + puerto + "/?useSSL=false";
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
	
	public Connection conectarDB(String nombre) {
		
		this.nombreDB = nombre;
		
		String url = "jdbc:mysql://" + hostname + ":" + puerto + "/" + nombreDB + "?useSSL=false";
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
