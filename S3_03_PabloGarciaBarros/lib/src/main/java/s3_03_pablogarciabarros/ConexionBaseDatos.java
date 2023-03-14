package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos extends QueriesSQL{
	
	private String hostname;
	private String puerto = "3306";
	private String nombreBD;
	private String usuario;
	private String password;
	
	public ConexionBaseDatos(String hostname, String nombreDB, String usuario, String password) {
		this.hostname = hostname;
		this.nombreBD = nombreDB;
		this.usuario = usuario;
		this.password = password;
	}
	
	
	public Connection conectarNuevaBD() {
		String url = "jdbc:mysql://" + hostname + ":" + puerto + "/?useSSL=false";
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection(url, usuario, password);
			System.out.println("Conectado al servicio");
		}
		catch(SQLException e) {
			System.out.println("No se pudo conectar al servicio");
		}		
		return conexion;
	}
	

	public Connection crearNuevaDB() {		
		
		Connection conexion = conectarNuevaBD();
		this.borrarBD(conexion, this.nombreBD);
		this.crearBD(conexion, this.nombreBD);
		this.usarBD(conexion, this.nombreBD);
		
		ProductoraFabricaTabla.getFabrica(1).nuevaTabla().crearTabla(conexion);
		ProductoraFabricaTabla.getFabrica(1).nuevaTabla().insertarTipoProducto(conexion, 1, "Arbol");
		ProductoraFabricaTabla.getFabrica(1).nuevaTabla().insertarTipoProducto(conexion, 2, "Flor");
		ProductoraFabricaTabla.getFabrica(1).nuevaTabla().insertarTipoProducto(conexion, 3, "Decoracion");
		
		ProductoraFabricaTabla.getFabrica(2).nuevaTabla().crearTabla(conexion);
		ProductoraFabricaTabla.getFabrica(3).nuevaTabla().crearTabla(conexion);
		ProductoraFabricaTabla.getFabrica(4).nuevaTabla().crearTabla(conexion);
		ProductoraFabricaTabla.getFabrica(5).nuevaTabla().crearTabla(conexion);
		ProductoraFabricaTabla.getFabrica(6).nuevaTabla().crearTabla(conexion);
		ProductoraFabricaTabla.getFabrica(7).nuevaTabla().crearTabla(conexion);	
		
		return conexion;
	}
	
	
	public Connection conectarDB() {
			
		String url = "jdbc:mysql://" + hostname + ":" + puerto + "/" + nombreBD + "?useSSL=false";
		Connection conexion = null;		
		try {
			conexion = DriverManager.getConnection(url, usuario, password);
			System.out.println("Conectado al servicio");
		}
		catch(SQLException e) {
			System.out.println("No se pudo conectar al servicio");
		}		
		return conexion;
	}


	@Override
	public Connection borrarBD(Connection conexion, String nombreBD) {
		try {
			conexion.createStatement().execute("DROP DATABASE IF EXISTS " + nombreBD);
		}
		catch(SQLException e) {
			System.out.println("ERROR - NO SE PUEDE BORRAR LA BD");
		}
		return conexion;
	}


	@Override
	public Connection crearBD(Connection conexion, String nombreBD) {
		try {
			conexion.createStatement().execute("CREATE DATABASE " + nombreBD + " CHARACTER SET utf8mb4");
		}
		catch(SQLException e) {
			System.out.println("ERROR - NO SE PUEDE CREAR LA BD");
		}
		return conexion;
	}


	@Override
	public Connection usarBD(Connection conexion, String nombreBD) {
		try {
			System.out.println("USE " + nombreBD);
			conexion.createStatement().execute("USE " + nombreBD);
		}
		catch(SQLException e) {
			System.out.println("ERROR - NO SE PUEDE USAR LA BD");
		}
		return conexion;
	}
	
	


	@Override	
	public void insertarTipoProducto(Connection conexion, int idTipoProducto, String descripcion) {
		// TODO Auto-generated method stub		
	}
	


	@Override
	public void insertar(Connection conexion) {
		// TODO Auto-generated method stub		
	}


	@Override
	public Connection crearTabla(Connection conexion) {
		// TODO Auto-generated method stub
		return null;
	}
}
