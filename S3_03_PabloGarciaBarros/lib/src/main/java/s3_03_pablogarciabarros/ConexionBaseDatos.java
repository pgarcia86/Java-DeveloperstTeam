package s3_03_pablogarciabarros;

import java.sql.Connection;

public class ConexionBaseDatos {
	
	private String hostname;
	private String puerto = "3306";
	private String nombreDB;
	private String usuario;
	private String password;
	private QueriesSQL query;
	
	public ConexionBaseDatos(String hostname, String nombreDB, String usuario, String password) {
		this.hostname = hostname;
		this.nombreDB = nombreDB;
		this.usuario = usuario;
		this.password = password;
		this.query = new QueriesSQL();
	}
	
	
	public Connection conectarNuevaBD() {		
		
		String queryBorrarDB = "DROP DATABASE IF EXISTS " + this.nombreDB;		//Creo la sentencia SQL para eliminar la DB si existe	
		String queryCrear = "CREATE DATABASE " + this.nombreDB + " CHARACTER SET utf8mb4";	//Creo la sentencia para crear la base de datos
		String queryUsarDB = "USE " + this.nombreDB;
		
		Connection conexion = this.query.conectar(this.hostname, this.puerto, "", this.usuario, this.password);
		
		this.query.crearDB(conexion, queryBorrarDB, queryCrear, queryUsarDB);
		
		this.query.crearTabla(conexion, "CREATE TABLE tipo_producto(id_tipo_producto INT PRIMARY KEY, descripcion VARCHAR(20))");
		this.query.crearTabla(conexion, "CREATE TABLE productos(id_producto INT UNIQUE PRIMARY KEY, id_tipo_producto INT NOT NULL,"
			+ "cantidad INT NOT NULL, precio DOUBLE NOT NULL, FOREIGN KEY(id_tipo_producto) REFERENCES tipo_producto(id_tipo_producto))");
		this.query.crearTabla(conexion, "CREATE TABLE arbol(id_arbol INT UNIQUE PRIMARY KEY, altura DOUBLE NOT NULL, "
			+ "precio DOUBLE NOT NULL, cantidad INT NOT NULL, id_tipo INT NOT NULL, FOREIGN KEY(id_tipo) REFERENCES tipo_producto(id_tipo_producto))");
		this.query.crearTabla(conexion, "CREATE TABLE flor(id_flor INT UNIQUE PRIMARY KEY, color VARCHAR(20) NOT NULL, "
			+ "precio DOUBLE NOT NULL, cantidad INT NOT NULL, id_tipo INT NOT NULL, FOREIGN KEY(id_tipo) REFERENCES tipo_producto(id_tipo_producto))");
		this.query.crearTabla(conexion, "CREATE TABLE decoracion(id_decoracion INT UNIQUE PRIMARY KEY, material VARCHAR(20) NOT NULL, "
			+ "precio DOUBLE NOT NULL, cantidad INT NOT NULL, id_tipo INT NOT NULL, FOREIGN KEY(id_tipo) REFERENCES tipo_producto(id_tipo_producto))");
		this.query.crearTabla(conexion, "CREATE TABLE comandas(id_comanda INT NOT NULL PRIMARY KEY, Dia DATE NOT NULL)");
		this.query.crearTabla(conexion, "CREATE TABLE detalle_comanda(id_comanda INT NOT NULL, id_producto INT, cantidad INT,"
			+ "FOREIGN KEY(id_comanda) REFERENCES comandas(id_comanda), FOREIGN KEY(id_producto) REFERENCES productos(id_producto))");
		
		this.query.actualizar(conexion, "INSERT INTO tipo_producto(id_tipo_producto, descripcion) VALUES(1, 'Arbol')");
		this.query.actualizar(conexion, "INSERT INTO tipo_producto(id_tipo_producto, descripcion) VALUES(2, 'Flor')");
		this.query.actualizar(conexion, "INSERT INTO tipo_producto(id_tipo_producto, descripcion) VALUES(3, 'Decoracion')");
		
		return conexion;
	}

	
	//Este metodo conecta a la base de datos. Recibe todos los valores por parametro
	public Connection conectarDB() {		
		Connection conexion = query.conectar(this.hostname, this.puerto, this.nombreDB, this.usuario, this.password);
		return conexion;
	}
}
