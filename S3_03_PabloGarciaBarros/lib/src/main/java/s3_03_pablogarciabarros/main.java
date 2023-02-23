package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class main {

	static Scanner entrada = new Scanner(System.in);
	static ConexionBaseDatos conexion = null;
	static Connection conect = null;
	static String nombreDB;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int opcion;
		
		Connection conexionActual = primerMenu();
		
		do{
			opcion = menuPrincipal();
			manejoMenu(conexionActual, opcion);
			
		}while(opcion != 0);
	}
	
	public static Connection primerMenu() {
		System.out.println("INGRESE LA OPCION DESEADA" +
			"\n1. Conectarse a una floristeria existente" +
			"\n2. Crear una nueva floristeria");
		int opcion = entrada.nextInt();
		
		if(opcion == 1) {
			return conectarBaseDatos();
		}
		else if(opcion == 2) {
			return crearFloristeria();
		}
		else {
			return null;	
		}		
	}

	public static int menuPrincipal() {
		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Agregar productos"	+
			"\n2. Retirar productos" +
			"\n3. Imprimir stock" +
			"\n4. Crear ticket de venta" +
			"\n5. Mostrar historico de ventas" +
			"\n0. Salir de la aplicacion");	
		
		return entrada.nextInt();
	}
	
	public static void menuAgregarProductos(Connection conexionActual, String nombreDB) {
		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Agregar arbol" +
			"\n2. Agregar planta" +
			"\n3. Agregar decoracion" +
			"\n0. Volver al menu anterior");		
		
		switch(entrada.nextInt()) {
			case 1:
				System.out.println("Agregar arbol");
				insertarArbol(conexionActual);
				
				break;
				
			case 2:
				System.out.println("Agregar planta");
				insertarFlor(conexionActual);
				break;
				
			case 3:
				System.out.println("Agregar decoracion");
				insertarDecoracion(conexionActual);
				break;
				
			case 0:
				menuPrincipal();
				break;
				
			default:
				System.out.println("Opcion no valida");
				break;
		}		
	}
	
	public static void menuRetirarProductos() {
		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Retirar arbol" +
			"\n2. Retirar planta" +
			"\n3. Retirar decoracion" +
			"\n0. Volver al menu anterior");
		
		switch(entrada.nextInt()) {
			case 1:
				System.out.println("Retirar arbol");
				break;
				
			case 2:
				System.out.println("Retirar planta");
				break;
				
			case 3:
				System.out.println("Retirar decoracion");
				break;
			
			case 0:
				menuPrincipal();
				break;
				
			default:
				System.out.println("Opcion no valida");
				break;				
		}		
	}	
	
	public static void menuImprimirStock(Connection conexionActual) {
		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Imprimir stock total" +
			"\n2. Imprimir stock de arboles" +
			"\n3. Imprimir stock de plantas" +
			"\n4. Imprimir stock de decoraciones" +
			"\n0. Volver al menu anterior");
		
		switch(entrada.nextInt()) {
			case 1:
				System.out.println("Imprimir stock");
				break;
				
			case 2:
				System.out.println("Imprimir stock de arboles");
				imprimirStockArboles(conexionActual);
				break;
				
			case 3:
				System.out.println("Imprimir stock de flores");
				imprimirStockFlores(conexionActual);
				break;
				
			case 4:
				System.out.println("Imprimir stock de decoraciones");
				imprimirStockDecoraciones(conexionActual);
				break;
				
			case 0:
				menuPrincipal();
				break;
			
			default:
				System.out.println("Opcion no valida");
				break;
		}		
	}

	public static void menuHistoricoVentas() {
		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Imprimir ventas totales" +
			"\n2. Imprimir ventas de un determinado período" +
			"\n0. Volver al menu anterior");
		
		switch(entrada.nextInt()) {
			case 1:
				System.out.println("Imprimir ventas totales");
				break;
				
			case 2:
				System.out.println("Imprimir ventas de un determinado período");
				break;
				
			case 0:
				menuPrincipal();
				break;
				
			default:
				System.out.println("Opcion no valida");
				break;		
		}		
	}
	
	public static void manejoMenu(Connection conexionActual, int opcion) {
		
		switch(opcion) {		 
		 case 1:
			 menuAgregarProductos(conexionActual, nombreDB);
			 break;
		 
		 case 2:
			 menuRetirarProductos();
			 break;
			 
		 case 3:
			 menuImprimirStock(conexionActual);
			 break;
		 
		 case 4:
			 crearTicket();
			 break;
			 
		 case 5:
			 menuHistoricoVentas();
			 break;
			 
		 case 0:
			 System.out.println("SALIENDO DE LA APLICACION");
			 break;
			 
		 default:
			 System.out.println("Opcion no valida");
			 break;			 
		}		
	}
	
	
	//Conecto a una base de datos ya existente. Me devuelve un Objeto del tipo Connection. Trabajare sobre esa conexion
	public static Connection conectarBaseDatos() {
		
		System.out.println("Ingrese el nombre de la base de datos a la que quiere conectarse");
		nombreDB = entrada.next();
		
		ConexionBaseDatos conexion = new ConexionBaseDatos();
		Connection conect = conexion.conectarDB(nombreDB);	
		
		return conect;
		
	}
	
	
	//Creo la base de datos desde cero. Devuelvo el Objeto Connection. Trabajare sobre esa conexion
	public static Connection crearFloristeria() {

		System.out.println("Ingrese el nombre de la floristeria");
		
		nombreDB = entrada.next();
		
		String borrarDB = "DROP DATABASE IF EXISTS " + nombreDB;		//Creo la sentencia SQL para eliminar la DB si existe	
		String query = "CREATE DATABASE " + nombreDB + " CHARACTER SET utf8mb4";	//Creo la sentencia para crear la base de datos
		String usarDB = "USE " + nombreDB;
		
		String crearTablaTipoProducto = "CREATE TABLE tipo_producto(id_tipo_producto INT PRIMARY KEY, descripcion VARCHAR(20))";
		String crearTablaProductos = "CREATE TABLE productos(id_producto INT AUTO_INCREMENT PRIMARY KEY, id_tipo_producto INT NOT NULL,"
				+ "precio DOUBLE NOT NULL, FOREIGN KEY(id_tipo_producto) REFERENCES tipo_producto(id_tipo_producto))";
		String crearTablaArbol = "CREATE TABLE arbol(id_arbol INT AUTO_INCREMENT PRIMARY KEY, altura DOUBLE NOT NULL, "
				+ "precio DOUBLE NOT NULL, cantidad INT NOT NULL)";
		String crearTablaFlor = "CREATE TABLE flor(id_flor INT AUTO_INCREMENT PRIMARY KEY, color VARCHAR(20) NOT NULL, "
				+ "precio DOUBLE NOT NULL, cantidad INT NOT NULL)";
		String crearTablaDecoracion = "CREATE TABLE decoracion(id_decoracion INT AUTO_INCREMENT PRIMARY KEY, material VARCHAR(20) NOT NULL, "
				+ "precio DOUBLE NOT NULL, cantidad INT NOT NULL)";
		String crearTablaComanda = "CREATE TABLE comandas(id_comanda INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Dia DATE NOT NULL)";
		String crearTablaDetalleComanda = "CREATE TABLE detalle_comanda(id_comanda INT NOT NULL, id_producto INT, cantidad INT,"
				+ "FOREIGN KEY(id_comanda) REFERENCES comandas(id_comanda), FOREIGN KEY(id_producto) REFERENCES productos(id_producto))";
		
		
			ConexionBaseDatos conexion = new ConexionBaseDatos();
			Connection conect = conexion.conectarNuevaBD();
			
		try {
			Statement borrar = conect.createStatement();
			borrar.execute(borrarDB);	//Ejecuto la sentencia que elimina la DB
			Statement sqlQuery = conect.createStatement();
			sqlQuery.execute(query);	//Ejecuto la sentencia que crea la DB
			Statement usar = conect.createStatement();
			usar.execute(usarDB);
			PreparedStatement crearTabla1 = conect.prepareStatement(crearTablaTipoProducto);
			crearTabla1.execute();
			PreparedStatement crearTabla2 = conect.prepareStatement(crearTablaProductos);
			crearTabla2.execute();
			PreparedStatement crearTabla3 = conect.prepareStatement(crearTablaArbol);
			crearTabla3.execute();
			PreparedStatement crearTabla4 = conect.prepareStatement(crearTablaFlor);
			crearTabla4.execute();
			PreparedStatement crearTabla5 = conect.prepareStatement(crearTablaDecoracion);
			crearTabla5.execute();
			PreparedStatement crearTabla6 = conect.prepareStatement(crearTablaComanda);
			crearTabla6.execute();
			PreparedStatement crearTabla7 = conect.prepareStatement(crearTablaDetalleComanda);
			crearTabla7.execute();
			insertarTipoProducto(conect);
			System.out.println("Se creo la base de datos");
		}
		catch(SQLException e) {
			System.out.println("No se pudo crear la base de datos");	//Si hubo algun problema ejecuta esto
		}
		return conect;
	}
	
	
	//Inserto el tipo de producto al crear la base de datos nueva
	public static void insertarTipoProducto(Connection conexionActual) {
		
		//String insertar = "INSERT INTO tipo_producto(id_tipo_producto, descripcion) VALUES(1, 'Arbol')";
		
		try {
			PreparedStatement insertarValores = conexionActual.prepareStatement("INSERT INTO tipo_producto(id_tipo_producto, descripcion) "
					+ "VALUES(1, 'Arbol')");
			insertarValores.executeUpdate("INSERT INTO tipo_producto(id_tipo_producto, descripcion) "
					+ "VALUES(1, 'Arbol')");
			PreparedStatement insertarValores2 = conexionActual.prepareStatement("INSERT INTO tipo_producto(id_tipo_producto, descripcion) "
					+ "VALUES(2, 'Flor')");
			insertarValores2.executeUpdate("INSERT INTO tipo_producto(id_tipo_producto, descripcion) "
					+ "VALUES(2, 'Flor')");
			PreparedStatement insertarValores3 = conexionActual.prepareStatement("INSERT INTO tipo_producto(id_tipo_producto, descripcion) "
					+ "VALUES(3, 'Decoracion')");
			insertarValores3.executeUpdate("INSERT INTO tipo_producto(id_tipo_producto, descripcion) "
					+ "VALUES(3, 'Decoracion')");
			System.out.println("Se creo correctamente");
		}
		catch(SQLException e) {
			System.out.println("No se pudo crear");
		}
	}
	
	
	//Creo el Query que va a servir para insertarDato. Paso como paramentro el objeto del tipo Connection
	public static void insertarArbol(Connection conexionActual) {
		
		System.out.println("Ingrese la altura del arbol");
		double altura = entrada.nextDouble();
		System.out.println("Ingrese el precio del arbol");
		double precio = entrada.nextDouble();
		System.out.println("Ingrese la cantidad de arboles");
		int cantidad = entrada.nextInt();
		
		String insertar = "INSERT INTO arbol(altura, precio, cantidad) VALUES(" + altura + "," + precio + "," + cantidad +")";		
		insertarDato(insertar, conexionActual);
		
		String insertarProducto = "INSERT INTO productos(id_tipo_producto, precio) VALUES(1, " + precio + ")";		
		insertarDato(insertarProducto, conexionActual);
	}
	
	
	//Creo el Query que va a servir para insertarDato. Paso como paramentro el objeto del tipo Connection
	public static void insertarFlor(Connection conexionActual) {
		
		System.out.println("Ingrese el color de la flor");
		String color = entrada.next();
		System.out.println("Ingrese el precio de la flor");
		double precio = entrada.nextDouble();
		System.out.println("Ingrese la cantidad de flores");
		int cantidad = entrada.nextInt();
		
		String insertar = "INSERT INTO flor(color, precio, cantidad) VALUES('" + color + "'," + precio + "," + cantidad +")";
		insertarDato(insertar, conexionActual);
		String insertarProducto = "INSERT INTO productos(id_tipo_producto, precio) VALUES(2, " + precio + ")";		
		insertarDato(insertarProducto, conexionActual);
	}
	
	
	//Creo el Query que va a servir para insertarDato. Paso como paramentro el objeto del tipo Connection
	public static void insertarDecoracion(Connection conexionActual) {
		
		System.out.println("Ingrese el material de la decoracion");
		String tipoMaterial = entrada.next();
		System.out.println("Ingrese el precio de la decoracion");
		double precio = entrada.nextDouble();
		System.out.println("Ingrese la cantidad de decoraciones");
		int cantidad = entrada.nextInt();
		
		String insertar = "INSERT INTO decoracion(material, precio, cantidad) VALUES('" + tipoMaterial 
			+ "'," + precio + "," + cantidad +")";
		insertarDato(insertar, conexionActual);
		String insertarTipoProducto = "INSERT INTO productos(id_tipo_producto, precio) VALUES(3, " + precio + ")";		
		insertarDato(insertarTipoProducto, conexionActual);
	}
	
	
	//Inserto el dato en la BD. Recibe como parametro el query y la conexion
	public static void insertarDato(String insertar, Connection conexionActual) {
		
		try {
			PreparedStatement insertarValores = conexionActual.prepareStatement(insertar);
			insertarValores.executeUpdate(insertar);
			System.out.println("Se inserto correctamente");
		}
		catch(SQLException e) {
			System.out.println("No se pudo insertar");
		}		
	}
	
	
	//
	public static void imprimirStockArboles(Connection conexionActual) {
		
		String query = "SELECT SUM(cantidad) AS 'Cantidad de arboles' FROM arbol";
		
		try {
			Statement consultaStock = conexionActual.createStatement();
			ResultSet resultado = consultaStock.executeQuery(query);
			while(resultado.next()) {				
				System.out.println(resultado.getString("Cantidad de arboles"));
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}		
	}
	
	
	//
	public static void imprimirStockFlores(Connection conexionActual) {
		
		String query = "SELECT SUM(cantidad) AS 'Cantidad de flores' FROM flor";
		
		try {
			Statement consultaStock = conexionActual.createStatement();
			ResultSet resultado = consultaStock.executeQuery(query);
			while(resultado.next()) {				
				System.out.println(resultado.getString("Cantidad de flores"));
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}			
	}
	
	
	//
	public static void imprimirStockDecoraciones(Connection conexionActual) {
		
		String query = "SELECT SUM(cantidad) AS 'Cantidad de decoraciones' FROM decoracion";
		
		try {
			Statement consultaStock = conexionActual.createStatement();
			ResultSet resultado = consultaStock.executeQuery(query);
			while(resultado.next()) {				
				System.out.println(resultado.getString("Cantidad de decoraciones"));
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}	
		
	}
	
	
	//
	public static void crearTicket() {
		System.out.println("Creo ticket nuevo");
	}
}
