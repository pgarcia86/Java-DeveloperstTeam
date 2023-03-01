package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueriesSQL {
	
	public void insertar(Connection conexion, String query) {
		
		try {
			PreparedStatement insertarValores = conexion.prepareStatement(query);
			insertarValores.executeUpdate(query);
		}
		catch(SQLException e) {
			System.out.println("No se pudo insertar");
		}		
	}
	
	public void retirar(Connection conexion, String query) {
		try {
			PreparedStatement consulta = conexion.prepareStatement(query);
			consulta.executeUpdate(query);
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}
	}
	
	public int cantidad(Connection conexion, String query) {
			
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
