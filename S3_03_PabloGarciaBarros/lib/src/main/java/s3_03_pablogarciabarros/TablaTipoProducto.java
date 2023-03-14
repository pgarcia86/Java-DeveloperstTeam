package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TablaTipoProducto extends Tabla{
	
	public TablaTipoProducto() {
		super();
	}	
	
	@Override
	public Connection crearTabla(Connection conexion) {
		try {			
			conexion.createStatement().execute("CREATE TABLE tipo_producto(id_tipo_producto INT PRIMARY KEY, descripcion VARCHAR(20))");
		}
		catch(SQLException e) {
			System.out.println("ERROR - AL CREAR TABLA TIPO PRODUCTO");
		}
		return conexion;	
	}

	@Override
	public Connection borrarBD(Connection conexion, String nombreBD) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection crearBD(Connection conexion, String nombreBD) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection usarBD(Connection conexion, String nombreBD) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertarTipoProducto(Connection conexion, int idTipoProducto, String descripcion) {
		try {
			PreparedStatement insertarValores = conexion.prepareStatement("INSERT INTO tipo_producto (id_tipo_producto, descripcion) VALUES(" + idTipoProducto + ", '" + descripcion + "')");
			insertarValores.executeUpdate("INSERT INTO tipo_producto (id_tipo_producto, descripcion) VALUES(" + idTipoProducto + ", '" + descripcion + "')");
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta - insertarTipoProducto");
		}
	}

	@Override
	public void insertar(Connection conexion) {
		// TODO Auto-generated method stub
		
	}
}	