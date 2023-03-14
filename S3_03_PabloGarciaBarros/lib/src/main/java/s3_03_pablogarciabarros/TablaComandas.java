package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.SQLException;

public class TablaComandas extends Tabla{
	
	static {
		System.out.println("PROCESO DE CREACION DE LA TABLA COMANDA");
	}

	@Override
	public Connection crearTabla(Connection conexion) {
		try {			
			conexion.createStatement().execute("CREATE TABLE comandas(id_comanda INT NOT NULL PRIMARY KEY, Dia DATE NOT NULL)");
		}
		catch(SQLException e) {
			System.out.println("ERROR - AL CREAR TABLA COMANDAS");
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
