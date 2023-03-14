package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.SQLException;

public class TablaDetalleComanda extends Tabla{
	
	static {
		System.out.println("PROCESO DE CREACION DE LA TABLA DETALLE DE COMANDAS");
	}

	@Override
	public Connection crearTabla(Connection conexion) {
		try {			
			conexion.createStatement().execute("CREATE TABLE detalle_comanda(id_comanda INT NOT NULL, id_producto INT, cantidad INT,\r\n"
					+ "	FOREIGN KEY(id_comanda) REFERENCES comandas(id_comanda), FOREIGN KEY(id_producto) REFERENCES producto(id_producto))");
		}
		catch(SQLException e) {
			System.out.println("ERROR - AL CREAR TABLA DETALLE COMANDA");
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
