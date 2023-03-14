package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.SQLException;

//TablaArbol es a Tabla lo que Arbol es a Producto

//ES ANALOGA A ARBOL

public class TablaArbol extends Tabla{	

	
	static {
		System.out.println("PROCESO DE CREACION DE LAS TABLA ARBOL");
	}
	
	public TablaArbol() {
		super();
	}

	@Override
	public Connection crearTabla(Connection conexion) {
		try {			
			conexion.createStatement().execute("CREATE TABLE arbol(id_arbol INT UNIQUE PRIMARY KEY, altura VARCHAR(20) NOT NULL, precio DOUBLE NOT NULL, cantidad INT NOT NULL, id_tipo INT NOT NULL, \r\n"
					+ "	FOREIGN KEY(id_tipo) REFERENCES tipo_producto(id_tipo_producto))");
		}
		catch(SQLException e) {
			System.out.println("ERROR - AL CREAR TABLA ARBOL");
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
	
	
