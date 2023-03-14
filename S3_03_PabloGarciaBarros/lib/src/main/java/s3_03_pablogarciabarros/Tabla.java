package s3_03_pablogarciabarros;

import java.sql.Connection;


//Es el equivalente a Producto
public abstract class Tabla extends QueriesSQL{
	static {
		System.out.println("PROCESO DE CREACION DE LAS TABLAS");
	}
	
	@Override
	public abstract Connection crearTabla(Connection conexion);
}