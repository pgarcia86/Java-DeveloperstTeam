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
		Connection conexionActual = null;
		
		do {
		conexionActual = primerMenu();
		}while(conexionActual == null);
		
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
			"\n6. Calcular total de ventas" +
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
	
	public static void menuRetirarProductos(Connection conexionActual) {
		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Retirar arbol" +
			"\n2. Retirar planta" +
			"\n3. Retirar decoracion" +
			"\n0. Volver al menu anterior");
		
		switch(entrada.nextInt()) {
			case 1:
				retirarArbol(conexionActual);
				break;
				
			case 2:
				retirarFlor(conexionActual);
				break;
				
			case 3:
				retirarDecoracion(conexionActual);
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
			"\n1. Imprimir stock de arboles" +
			"\n2. Imprimir stock de plantas" +
			"\n3. Imprimir stock de decoraciones" +
			"\n0. Volver al menu anterior");
		
		switch(entrada.nextInt()) {				
			case 1:
				imprimirStockArboles(conexionActual);
				break;
				
			case 2:
				imprimirStockFlores(conexionActual);
				break;
				
			case 3:
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
			 menuRetirarProductos(conexionActual);
			 break;
			 
		 case 3:
			 menuImprimirStock(conexionActual);
			 break;
		 
		 case 4:
			 crearTicket(conexionActual);
			 break;
			 
		 case 5:
			 historicoVentas(conexionActual);
			 break;
			 
		 case 6: 
			 calcularVentasTotal(conexionActual);
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
		String crearTablaProductos = "CREATE TABLE productos(id_producto INT UNIQUE PRIMARY KEY, id_tipo_producto INT NOT NULL,"
			+ "cantidad INT NOT NULL, precio DOUBLE NOT NULL, FOREIGN KEY(id_tipo_producto) REFERENCES tipo_producto(id_tipo_producto))";
		String crearTablaArbol = "CREATE TABLE arbol(id_arbol INT UNIQUE PRIMARY KEY, altura DOUBLE NOT NULL, "
			+ "precio DOUBLE NOT NULL, cantidad INT NOT NULL, id_tipo INT NOT NULL, FOREIGN KEY(id_tipo) REFERENCES tipo_producto(id_tipo_producto))";
		String crearTablaFlor = "CREATE TABLE flor(id_flor INT UNIQUE PRIMARY KEY, color VARCHAR(20) NOT NULL, "
			+ "precio DOUBLE NOT NULL, cantidad INT NOT NULL, id_tipo INT NOT NULL, FOREIGN KEY(id_tipo) REFERENCES tipo_producto(id_tipo_producto))";
		String crearTablaDecoracion = "CREATE TABLE decoracion(id_decoracion INT UNIQUE PRIMARY KEY, material VARCHAR(20) NOT NULL, "
			+ "precio DOUBLE NOT NULL, cantidad INT NOT NULL, id_tipo INT NOT NULL, FOREIGN KEY(id_tipo) REFERENCES tipo_producto(id_tipo_producto))";
		String crearTablaComanda = "CREATE TABLE comandas(id_comanda INT NOT NULL PRIMARY KEY, Dia DATE NOT NULL)";
		String crearTablaDetalleComanda = "CREATE TABLE detalle_comanda(id_comanda INT NOT NULL, id_producto INT, cantidad INT,"
			+ "FOREIGN KEY(id_comanda) REFERENCES comandas(id_comanda), FOREIGN KEY(id_producto) REFERENCES productos(id_producto))";
		
			ConexionBaseDatos conexion = new ConexionBaseDatos();
			Connection conect = conexion.conectarNuevaBD();
			
		try {
			conect.createStatement().execute(borrarDB);
			conect.createStatement().execute(query);			
			conect.createStatement().execute(usarDB);
			conect.prepareStatement(crearTablaTipoProducto).execute();
			conect.prepareStatement(crearTablaProductos).execute();			
			conect.prepareStatement(crearTablaArbol).execute();
			conect.prepareStatement(crearTablaFlor).execute();
			conect.prepareStatement(crearTablaDecoracion).execute();
			conect.prepareStatement(crearTablaComanda).execute();
			conect.prepareStatement(crearTablaDetalleComanda).execute();
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
		}
		catch(SQLException e) {
			System.out.println("No se pudo crear");
		}
	}
	
	
	//Creo el Query que va a servir para insertarDato. Paso como paramentro el objeto del tipo Connection
	public static void insertarArbol(Connection conexionActual) {
		
		System.out.println("Ingrese el id del producto");
		int id = entrada.nextInt();
		System.out.println("Ingrese la altura del arbol");
		double altura = entrada.nextDouble();
		System.out.println("Ingrese el precio del arbol");
		double precio = entrada.nextDouble();
		System.out.println("Ingrese la cantidad de arboles");
		int cantidad = entrada.nextInt();
		
		String insertar = "INSERT INTO arbol(id_arbol, altura, precio, cantidad, id_tipo) VALUES(" + id + "," + altura + "," + precio + 
			"," + cantidad +", 1)";		
		insertarDato(insertar, conexionActual);
		
		String insertarProducto = "INSERT INTO productos(id_producto, id_tipo_producto, cantidad, precio) VALUES(" + id + ", 1, " + cantidad + "," + 
			precio + ")";
		insertarDato(insertarProducto, conexionActual);
	}
	
	
	//Creo el Query que va a servir para insertarDato. Paso como paramentro el objeto del tipo Connection
	public static void insertarFlor(Connection conexionActual) {
		
		System.out.println("Ingrese el id de producto");
		int id = entrada.nextInt();
		System.out.println("Ingrese el color de la flor");
		String color = entrada.next();
		System.out.println("Ingrese el precio de la flor");
		double precio = entrada.nextDouble();
		System.out.println("Ingrese la cantidad de flores");
		int cantidad = entrada.nextInt();
		
		String insertar = "INSERT INTO flor(id_flor, color, precio, cantidad, id_tipo) VALUES(" + id + ",'" + color + "'," + precio + "," + 
			cantidad +", 2)";
		insertarDato(insertar, conexionActual); 
		String insertarProducto = "INSERT INTO productos(id_producto, id_tipo_producto, cantidad, precio) VALUES(" + id + ", 2, " + 
			cantidad + ", " + precio + ")";		
		insertarDato(insertarProducto, conexionActual);
	}
	
	
	//Creo el Query que va a servir para insertarDato. Paso como paramentro el objeto del tipo Connection
	public static void insertarDecoracion(Connection conexionActual) {
		
		System.out.println("Ingrese el id del producto");
		int id = entrada.nextInt();
		System.out.println("Ingrese el material de la decoracion");
		String tipoMaterial = entrada.next();
		System.out.println("Ingrese el precio de la decoracion");
		double precio = entrada.nextDouble();
		System.out.println("Ingrese la cantidad de decoraciones");
		int cantidad = entrada.nextInt();
		
		String insertar = "INSERT INTO decoracion(id_decoracion, material, precio, cantidad, id_tipo) VALUES(" + id + ",'" + tipoMaterial 
			+ "'," + precio + "," + cantidad +", 3)";
		insertarDato(insertar, conexionActual);
		String insertarTipoProducto = "INSERT INTO productos(id_producto, id_tipo_producto, cantidad, precio) VALUES(" + id +", 3, " 
			+ cantidad + ", " +	precio + ")";		
		insertarDato(insertarTipoProducto, conexionActual);
	}
	
	
	//Inserto el dato en la BD. Recibe como parametro el query y la conexion
	public static void insertarDato(String insertar, Connection conexionActual) {
		
		try {
			PreparedStatement insertarValores = conexionActual.prepareStatement(insertar);
			insertarValores.executeUpdate(insertar);
		}
		catch(SQLException e) {
			System.out.println("No se pudo insertar");
		}		
	}
	
	
	//
	public static void imprimirStockArboles(Connection conexionActual) {
		
		String query = "SELECT SUM(cantidad) AS 'Cantidad de arboles' FROM arbol";
	
		String columna = "Cantidad de arboles";
		
		imprimirStock(conexionActual, query, columna);			
	}
	
	
	//
	public static void imprimirStockFlores(Connection conexionActual) {
		
		String query = "SELECT SUM(cantidad) AS 'Cantidad de flores' FROM flor";

		String columna = "Cantidad de flores";
		
		imprimirStock(conexionActual, query, columna);			

	}
	
	
	//
	public static void imprimirStockDecoraciones(Connection conexionActual) {
		
		String query = "SELECT SUM(cantidad) AS 'Cantidad de decoraciones' FROM decoracion";
		
		String columna = "Cantidad de decoraciones";
		
		imprimirStock(conexionActual, query, columna);			
	}
	
	
	public static void imprimirStock(Connection conexionActual, String query, String columna) {
		
		try {
			Statement consultaStock = conexionActual.createStatement();
			ResultSet resultado = consultaStock.executeQuery(query);
			while(resultado.next()) {				
				System.out.println("Hay en stock: " + resultado.getString(columna));
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}
	}
	
	
	public static void retirarArbol(Connection conexionActual) {
		
		System.out.println("Ingrese el id del arbol");
		int idProdRetiro = entrada.nextInt();
		
		System.out.println("Ingrese la cantidad que desea retirar");
		int cantRetiro = entrada.nextInt();
		
		String query = "SELECT cantidad FROM arbol WHERE id_arbol = " + idProdRetiro;
		String queryProducto = "UPDATE productos SET cantidad = (cantidad - " + cantRetiro + ") WHERE id_producto = " + idProdRetiro;

		
		int stock = obtenerCantidad(conexionActual, query);
		
		query = "UPDATE arbol SET cantidad = (cantidad - " + cantRetiro + ") WHERE id_arbol = " + idProdRetiro;
		
		retirarProducto(conexionActual, query, cantRetiro, stock);
		retirarProducto(conexionActual, queryProducto, cantRetiro, stock);
	} 
	
	
	public static void retirarFlor(Connection conexionActual) {
		
		System.out.println("Ingrese el id de la flor");
		int idProdRetiro = entrada.nextInt();
		
		System.out.println("Ingrese la cantidad que desea retirar");
		int cantRetiro = entrada.nextInt();
		
		String query = "SELECT cantidad FROM flor WHERE id_flor = " + idProdRetiro;
		String queryProducto = "UPDATE productos SET cantidad = (cantidad - " + cantRetiro + ") WHERE id_producto = " + idProdRetiro;

		
		int stock = obtenerCantidad(conexionActual, query);
		
		query = "UPDATE flor SET cantidad = (cantidad - " + cantRetiro + ") WHERE id_flor = " + idProdRetiro;
		
		retirarProducto(conexionActual, query, cantRetiro, stock);	
		retirarProducto(conexionActual, queryProducto, cantRetiro, stock);
	}
	
	
	public static void retirarDecoracion(Connection conexionActual) {
		
		System.out.println("Ingrese el id de la decoracion");
		int idProdRetiro = entrada.nextInt();
		
		System.out.println("Ingrese la cantidad que desea retirar");
		int cantRetiro = entrada.nextInt();
		
		String query = "SELECT cantidad FROM decoracion WHERE id_decoracion = " + idProdRetiro;
		String queryProducto = "UPDATE productos SET cantidad = (cantidad - " + cantRetiro + ") WHERE id_producto = " + idProdRetiro;
		
		int stock = obtenerCantidad(conexionActual, query); 
		
		query = "UPDATE decoracion SET cantidad = (cantidad - " + cantRetiro + ") WHERE id_decoracion = " + idProdRetiro;
		
		retirarProducto(conexionActual, query, cantRetiro, stock);
		retirarProducto(conexionActual, queryProducto, cantRetiro, stock);
	}
	
	
	public static int obtenerCantidad(Connection conexionActual, String query) {
		
		int cantidad = 0;
		
		try {
			Statement consulta = conexionActual.createStatement();
			ResultSet resultado = consulta.executeQuery(query);
			
			while(resultado.next()) {
				return resultado.getInt(1);
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}
		
		return cantidad;
	}
	
	
	public static void retirarProducto(Connection conexionActual, String query, int cantRetiro, int stock) {
				
		if(cantRetiro < stock) {
			try {
				PreparedStatement consulta = conexionActual.prepareStatement(query);
				consulta.executeUpdate(query);
			}
			catch(SQLException e) {
				System.out.println("No se puede hacer la consulta");
			}
		}
		else {
			System.out.println("No se puede retirar mas de lo que se tiene en stock");
		}		
	}
	
	//
	public static void crearTicket(Connection conexionActual) {
	
		int idProd;
		int cant;
		String continuar = "n";
		System.out.println("Inserte el nro de la comanda");
		int idComanda = entrada.nextInt();
		System.out.println("Ingrese el año de la comanda");
		String anyo = entrada.next();
		System.out.println("Ingrese el mes de la comanda");
		String mes = entrada.next();
		System.out.println("Ingrese el dia de la comanda");
		String dia = entrada.next();
		String query = "INSERT INTO comandas(id_comanda, Dia) VALUES(" + idComanda + ", '" + anyo + "-" + mes + "-" + dia + "')";
		
		insertarDato(query, conexionActual);
		
		do {
			System.out.println("Ingrese el id del producto");
			idProd = entrada.nextInt();
			
			System.out.println("Ingrese la cantidad");
			cant = entrada.nextInt();
			
			query = "INSERT INTO detalle_comanda(id_comanda, id_producto, cantidad) VALUES(" + idComanda + ", " + idProd + ", " 
				+ cant + ")";
			
			insertarDato(query, conexionActual);
			
			System.out.println("Quiere ingresar mas productos?(S/N)");
			continuar = entrada.next();
			actualizarStock(conexionActual, idProd, cant, idComanda);
			
		}while(continuar.equalsIgnoreCase("s"));			
		precioFinalComanda(conexionActual, idComanda);
	}

	
	public static void precioFinalComanda(Connection conexionActual, int idComanda) {
		
		String query = "SELECT SUM(p.precio * dc.cantidad) AS 'Precio final' FROM productos AS p INNER JOIN detalle_comanda AS dc " +
			"ON p.id_producto = dc.id_producto INNER JOIN comandas AS c ON dc.id_comanda = c.id_comanda WHERE c.id_comanda =" 
			+ idComanda;	
		
		try {
			Statement consulta = conexionActual.createStatement();
			ResultSet resultado = consulta.executeQuery(query);
			while(resultado.next()) {
				System.out.println("El precio final de la comanda es: " + resultado.getInt(1) + "€");
			}
		}
		catch(SQLException e){
			System.out.println("No se puede hacer la consulta");
		}		
	}
	
	
	public static void actualizarStock(Connection conexionActual, int idProd, int cant, int idComanda) {
		
		String query;
		String queryUpdate;
		String queryTipoProducto;
		String tipoProducto;
		String queryRetiroTipoProducto;
		int stock;
		query = "SELECT cantidad FROM productos WHERE id_producto = " + idProd;
		stock = obtenerCantidad(conexionActual, query);
		queryUpdate = "UPDATE productos SET cantidad = (cantidad - " + cant + ") WHERE id_producto = " + idProd;
		retirarProducto(conexionActual, queryUpdate, cant, stock);
		queryTipoProducto = "SELECT descripcion FROM tipo_producto AS tp INNER JOIN productos AS p " +
			"ON tp.id_tipo_producto = p.id_tipo_producto WHERE p.id_producto = " + idProd;
		try {
			Statement consulta = conexionActual.createStatement();
			ResultSet resultado = consulta.executeQuery(queryTipoProducto);
			while(resultado.next()) {
				tipoProducto = resultado.getString(1).toLowerCase();
				queryRetiroTipoProducto = "UPDATE " + tipoProducto + " SET cantidad = (cantidad - " + cant +
					") WHERE id_" + tipoProducto + " = " + idProd;
				retirarProducto(conexionActual, queryRetiroTipoProducto, cant, stock);
			}
		}
		catch(SQLException e) {
			System.out.println("No se puede actualizar el stock");
		}
	}

	
	public static void historicoVentas(Connection conexionActual) {
		
		System.out.println("Ingrese el año de comienzo de la consulta");
		String anyoComienzo = entrada.next();
		System.out.println("Ingrese el mes de comienzo de la consulta");
		String mesComienzo = entrada.next();
		System.out.println("Ingrese el dia de comienzo de la consulta");
		String diaComienzo = entrada.next();
		
		System.out.println("Ingrese el año de final de la consulta");
		String anyoFinal = entrada.next();
		System.out.println("Ingrese el mes de final de la consulta");
		String mesFinal = entrada.next();
		System.out.println("Ingrese el dia de final de la consulta");
		String diaFinal = entrada.next();
		
		String query = "SELECT * FROM comandas AS c WHERE c.Dia >= '" + anyoComienzo + "-" + mesComienzo + "-" + diaComienzo + 
			"' AND c.Dia <= '" + anyoFinal + "-" + mesFinal + "-" + diaFinal + "'";
		
		try {
			Statement consulta = conexionActual.createStatement();
			ResultSet resultado = consulta.executeQuery(query);
			while(resultado.next()) {
				System.out.println("ID Comanda: " + resultado.getString(1) + " - Fecha: " + resultado.getString(2));
			}
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}		
	}


	public static void calcularVentasTotal(Connection conexionActual) {
		
		String query = "SELECT SUM(p.precio * dc.cantidad) AS 'Total ganado' FROM productos AS p INNER JOIN detalle_comanda AS dc " +
			"ON p.id_producto = dc.id_producto";
		try {
			Statement consulta = conexionActual.createStatement();
			ResultSet resultado = consulta.executeQuery(query);
			while(resultado.next()) {
				System.out.println("En total ha ganado: " + resultado.getDouble(1) + "€");
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}
		
	}
}
