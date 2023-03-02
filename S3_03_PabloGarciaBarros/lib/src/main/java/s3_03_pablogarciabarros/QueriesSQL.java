package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//En esta clase dejo todas las consultas correspondietes a la base de datos

public class QueriesSQL {
	
	//Recibe por parametro el query a ejecutar y ejecuta
	public boolean actualizar(Connection conexion, String query) {		
		try {
			PreparedStatement insertarValores = conexion.prepareStatement(query);
			insertarValores.executeUpdate(query);
			return true;
		}
		catch(SQLException e) {
			System.out.println("No se pudo hacer la consulta - actualizar");
			return false;
		}		
	}
	
	
	public int getStock(Connection conexion, String query) {			
		int cantidad = 0;		
		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(query);
			while(resultado.next()) {
				return resultado.getInt(1);
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}		
		return cantidad;
	}
	
	
	public int getId(Connection conexion, String query) {			
		int cantidad = 0;		
		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(query);
			while(resultado.next()) {
				return resultado.getInt(1);
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}		
		return cantidad;
	}
	
	
	public Connection ejecutar(Connection conexion, String query) {
		try {
			conexion.createStatement().execute(query);
		}
		catch(SQLException e) {
			System.out.println("ERROR");
		}
		return conexion;
	}
	
	
	
	public Connection crearDB(Connection conexion, String borrarDB, String query, String usarDB) {		
		try {
			conexion.createStatement().execute(borrarDB);
			conexion.createStatement().execute(query);			
			conexion.createStatement().execute(usarDB);
		}
		catch(SQLException e) {
			System.out.println("No se puede crear la BD");
		}
		return conexion;
	}
	
	//Recibe por parametro los valores y se conecta al servicio SQL
	public Connection conectar(String hostname, String puerto, String nombreDB, String usuario, String password) {		
		Connection conexion = null;
		String url = "jdbc:mysql://" + hostname + ":" + puerto + "/" + nombreDB + "?useSSL=false";	
		try {
			conexion = DriverManager.getConnection(url, usuario, password);
			System.out.println("Conectado al servicio");
		}
		catch(SQLException e) {
			System.out.println("No se pudo conectar al servicio");
		}		
		return conexion;
	}
	
	
	public void crearTabla(Connection conexion, String query) {
		try {
			conexion.prepareStatement(query).execute();
		}
		catch(SQLException e) {
			System.out.println("No se pude crear la tabla");
		}
	}

	
	public ResultSet ejecutarQuery(Connection conexion, String query) {
		
		ResultSet resultado = null;
		
		try {
			Statement consulta = conexion.createStatement();
			resultado = consulta.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}		
		return resultado;
		
	}
}
