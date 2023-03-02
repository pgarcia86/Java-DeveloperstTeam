package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
	
	private String hostname;
	private String puerto = "3306";
	private String nombreDB;
	private String usuario;
	private String password;
	private QueriesSQL queries;
	
	public ConexionBaseDatos(String hostname, String nombreDB, String usuario, String password) {
		this.hostname = hostname;
		this.nombreDB = nombreDB;
		this.usuario = usuario;
		this.password = password;
		this.queries = new QueriesSQL();
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
		queries.ejecutar(conexion, "DROP DATABASE IF EXISTS " + this.nombreDB);
		queries.ejecutar(conexion, "CREATE DATABASE " + this.nombreDB + " CHARACTER SET utf8mb4");
		queries.ejecutar(conexion, "USE " + this.nombreDB);
		
		queries.ejecutar(conexion, "CREATE TABLE tipo_producto(id_tipo_producto INT PRIMARY KEY, descripcion VARCHAR(20))");
		queries.ejecutar(conexion, "CREATE TABLE productos(id_producto INT UNIQUE PRIMARY KEY, id_tipo_producto INT NOT NULL, cantidad INT NOT NULL, precio DOUBLE NOT NULL, \r\n"
				+ "	FOREIGN KEY(id_tipo_producto) REFERENCES tipo_producto(id_tipo_producto))");
		queries.ejecutar(conexion, "CREATE TABLE arbol(id_arbol INT UNIQUE PRIMARY KEY, altura DOUBLE NOT NULL, precio DOUBLE NOT NULL, cantidad INT NOT NULL, id_tipo INT NOT NULL, \r\n"
				+ "	FOREIGN KEY(id_tipo) REFERENCES tipo_producto(id_tipo_producto))");
		queries.ejecutar(conexion, "CREATE TABLE flor(id_flor INT UNIQUE PRIMARY KEY, color VARCHAR(20) NOT NULL, precio DOUBLE NOT NULL, cantidad INT NOT NULL, id_tipo INT NOT NULL, \r\n"
				+ "	FOREIGN KEY(id_tipo) REFERENCES tipo_producto(id_tipo_producto))");
		queries.ejecutar(conexion, "CREATE TABLE decoracion(id_decoracion INT UNIQUE PRIMARY KEY, material VARCHAR(20) NOT NULL, precio DOUBLE NOT NULL, cantidad INT NOT NULL, id_tipo INT NOT NULL, \r\n"
				+ "	FOREIGN KEY(id_tipo) REFERENCES tipo_producto(id_tipo_producto))");
		queries.ejecutar(conexion, "CREATE TABLE comandas(id_comanda INT NOT NULL PRIMARY KEY, Dia DATE NOT NULL)");
		queries.ejecutar(conexion, "CREATE TABLE detalle_comanda(id_comanda INT NOT NULL, id_producto INT, cantidad INT,\r\n"
				+ "	FOREIGN KEY(id_comanda) REFERENCES comandas(id_comanda), FOREIGN KEY(id_producto) REFERENCES productos(id_producto))");
		queries.actualizar(conexion, "INSERT INTO tipo_producto(id_tipo_producto, descripcion) VALUES(1, \"Arbol\")");
		queries.actualizar(conexion, "INSERT INTO tipo_producto(id_tipo_producto, descripcion) VALUES(2, \"Flor\")");
		queries.actualizar(conexion, "INSERT INTO tipo_producto(id_tipo_producto, descripcion) VALUES(3, \"Decoracion\")");
		return conexion;
	}
	
	
	public Connection conectarDB() {
			
		String url = "jdbc:mysql://" + hostname + ":" + puerto + "/" + nombreDB + "?useSSL=false";
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
}
