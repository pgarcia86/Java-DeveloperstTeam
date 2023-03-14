package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.SQLException;


public class TablaProducto extends Tabla{
	
	static {
		System.out.println("PROCESO DE CREACION DE LA TABLA PRODUCTO");
	}

	@Override
	public Connection crearTabla(Connection conexion) {
		try {			
			conexion.createStatement().execute("CREATE TABLE producto(id_producto INT UNIQUE PRIMARY KEY, id_tipo_producto INT NOT NULL, " +
					"cantidad INT NOT NULL, precio DOUBLE NOT NULL, FOREIGN KEY(id_tipo_producto) REFERENCES tipo_producto(id_tipo_producto))");
		}
		catch(SQLException e) {
			System.out.println("ERROR - AL CREAR TABLA PRODUCTO");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertar(Connection conexion) {
		// TODO Auto-generated method stub
		
	}


}
