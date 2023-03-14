package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.SQLException;


//ES ANALOGA A DECORACION

public class TablaDecoracion extends Tabla{
	
	static {
		System.out.println("PROCESO DE CREACION DE LAS TABLA DECORACION");
	}

	@Override
	public Connection crearTabla(Connection conexion) {
		try {			
			conexion.createStatement().execute("CREATE TABLE decoracion(id_decoracion INT UNIQUE PRIMARY KEY, material VARCHAR(20) NOT NULL, " +
				"precio DOUBLE NOT NULL, cantidad INT NOT NULL, id_tipo INT NOT NULL, FOREIGN KEY(id_tipo) REFERENCES tipo_producto(id_tipo_producto))");
		}
		catch(SQLException e) {
			System.out.println("ERROR - AL CREAR TABLA DECORACION");
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
