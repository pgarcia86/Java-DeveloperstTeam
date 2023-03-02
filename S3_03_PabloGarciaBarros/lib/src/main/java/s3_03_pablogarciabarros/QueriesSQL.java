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
	
	
	public int getStock(Connection conexion, int id) {			
		int cantidad = 0;		
		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery("SELECT cantidad FROM producto WHERE id_producto = " + id
					);
			while(resultado.next()) {
				return resultado.getInt(1);
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}		
		return cantidad;
	}
	
	
	public int getIdProducto(Connection conexion, int id) {			
		int cantidad = 0;		
		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery("SELECT id_producto FROM producto WHERE id_producto = " + id);
			while(resultado.next()) {
				return resultado.getInt(1);
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - get");
		}		
		return cantidad;
	}
	
	
	public int getIdTipoProducto(Connection conexion, int id) {
		int cantidad = 0;		
		try {
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery("SELECT id_tipo_producto FROM producto WHERE id_producto = " + id);
			while(resultado.next()) {
				return resultado.getInt(1);
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - getid tipo producto");
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
	
	
	public boolean sumarStock(Connection conexion, int id, int cant, String nombreTabla) {
				
		try {
			PreparedStatement insertarValores = conexion.prepareStatement("UPDATE " + nombreTabla + " SET cantidad = (cantidad + " + cant + ") WHERE id_" + nombreTabla + " = " + id);
			insertarValores.executeUpdate("UPDATE " + nombreTabla + " SET cantidad = (cantidad + " + cant + ") WHERE id_" + nombreTabla + " = " + id);
			return true;
		}
		catch(SQLException e) {
			System.out.println("No se pudo hacer la consulta - sumarStock");
			return false;
		}
	}

	
	public boolean restarStock(Connection conexion, int id, int cant, String nombreTabla) {
		
		try {
			PreparedStatement insertarValores = conexion.prepareStatement("UPDATE " + nombreTabla + " SET cantidad = (cantidad - " + cant + ") WHERE id_" + nombreTabla +" = " + id);
			insertarValores.executeUpdate("UPDATE " + nombreTabla + " SET cantidad = (cantidad - " + cant + ") WHERE id_" + nombreTabla +" = " + id);
			return true;
		}
		catch(SQLException e) {
			System.out.println("No se pudo hacer la consulta - restarStock");
			return false;
		}
		
	}
	
	
	public boolean insertarDetalleComanda(Connection conexion, int idComanda, int id, int cant) {
		try {
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO detalle_comanda(id_comanda, id_producto, cantidad) VALUES(" + 
				idComanda + ", " + id + ", " + cant + ")");
			insertarValores.executeUpdate("INSERT INTO detalle_comanda(id_comanda, id_producto, cantidad) VALUES(" + 
					idComanda + ", " + id + ", " + cant + ")");
			return true;
		}
		catch(SQLException e) {
			System.out.println("No se puede insertar el detalle en la comanda");
			return false;
		}
		
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
