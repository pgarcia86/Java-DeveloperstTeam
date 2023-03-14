package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//En esta clase dejo todas las consultas correspondietes a la base de datos - Aqui solo hay SQL manejando los objetos 

public abstract class QueriesSQL {
	
	public abstract void insertar(Connection conexion);
	
	public abstract void insertarTipoProducto(Connection conexion, int idTipoProducto, String descripcion);
	
	public abstract Connection borrarBD(Connection conexion, String nombreBD);	
	
	public abstract Connection crearBD(Connection conexion, String nombreBD);	
	
	public abstract Connection usarBD(Connection conexion, String nombreBD);	
	
	public abstract Connection crearTabla(Connection conexion) ;
	
}
