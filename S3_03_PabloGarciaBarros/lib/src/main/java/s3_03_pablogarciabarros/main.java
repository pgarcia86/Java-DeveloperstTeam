package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class main {

	static Scanner in = new Scanner(System.in);
	static ConexionBaseDatos conexion = null;
	static Connection conect = null;
	static String nombreDB;
	static FabricaAbstracta fabrica = null;
	static QueriesSQL queries = new QueriesSQL();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int opcion;
		Connection conexion = null;
		
		do {
		conexion = primerMenu();
		}while(conexion == null);
		
		
		do{
			opcion = menuPrincipal();
			manejoMenu(conexion, opcion);
			
		}while(opcion != 0);
		
	}
	
	
	public static Connection primerMenu() {
		System.out.println("INGRESE LA OPCION DESEADA" +
			"\n1. Conectarse a una floristeria existente" +
			"\n2. Crear una nueva floristeria");
		int opcion = in.nextInt();
		
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
			"\n1. Agregar un producto"	+
			"\n2. Retirar un producto" +
			"\n3. Imprimir stock" +
			"\n4. Crear ticket de venta" +
			"\n5. Mostrar historico de ventas" +
			"\n6. Calcular total de ventas" +
			"\n7. Calcular total del valor del stock" +
			"\n0. Salir de la aplicacion");	
		
		return in.nextInt();
	}
	
	
	public static void manejoMenu(Connection conexion, int opcion) {
		
		switch(opcion) {		 
		 case 1:
			 agregarProductos(conexion);
			 break;
		 
		 case 2:
			 System.out.println("Ingrese el id del producto");
			 int id = in.nextInt();
			 System.out.println("Ingrese la cantidad a retirar");
			 int cantRetiro = in.nextInt();
			 retirarProducto(conexion, id, cantRetiro);
			 break;
			 
		 case 3:
			 imprimirStock(conexion);
			 break;
		 
		 case 4:
			 crearTicket(conexion);
			 break;
			 
		 case 5:
			 menuHistoricoVentas(conexion);
			 break;
			 
		 case 6: 
			 //calcularVentasTotal(conexionActual);
			 break;
			 
		 case 7:
			 //calcularValorTotalStock(conexionActual);
			 break;
			 
		 case 0:
			 System.out.println("SALIENDO DE LA APLICACION");
			 break;
			 
		 default:
			 System.out.println("Opcion no valida");
			 break;			 
		}		
	}	

	
	public static void agregarProductos(Connection conexion) {
		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Agregar arbol" +
			"\n2. Agregar planta" +
			"\n3. Agregar decoracion" +
			"\n0. Volver al menu anterior");		
		
		int opcion = in.nextInt();
		
		switch(opcion) {
			case 1:
				Arbol arbol = (Arbol)ProductoraFabricas.getFabrica(opcion).tipoProducto(0);
				arbol.agregarProducto(conexion, 1);
				arbol.agregarArbol(conexion);
				break;
				
			case 2:
				Flor flor = (Flor)ProductoraFabricas.getFabrica(opcion).tipoProducto(0);
				flor.agregarProducto(conexion, 2);
				flor.agregarFlor(conexion);
				break;
				
			case 3:
				Decoracion decoracion = (Decoracion)ProductoraFabricas.getFabrica(opcion).tipoProducto(0);
				decoracion.agregarProducto(conexion, 3);
				decoracion.agregarDecoracion(conexion);
				break;
				
			case 0:
				menuPrincipal();
				break;
				
			default:
				System.out.println("Opcion no valida");
				break;
		}		
	}
		
	
	public static void retirarProducto(Connection conexion, int id, int cantRetiro) {
		
		int tipoProducto = buscarTipoProducto(conexion, id); //En la posicion 1 del producto tengo el tipo del producto		
		String query = "";
		ResultSet datosProducto = null;
		
		switch(tipoProducto) {
			case 1:
				query = "SELECT * FROM arbol WHERE id_arbol = " + id; 
				break;
			case 2:
				query = "SELECT * FROM flor WHERE id_flor = " + id;
				break;
			case 3:
				query = "SELECT * FROM decoracion WHERE id_decoracion = " + id;
				break;
		}
		datosProducto = queries.ejecutarQuery(conexion, query);
		
		switch(tipoProducto) {
			case 1:
				retirarArbol(conexion, datosProducto, id, cantRetiro);
			break;
			
		case 2:
			retirarFlor(conexion, datosProducto, id, cantRetiro);
			break;
		case 3:
			retirarDecoracion(conexion, datosProducto, id, cantRetiro);	
			break;
		}
	}
	
	
	public static void retirarArbol(Connection conexion, ResultSet datosProducto, int id, int cantRetiro) {
		try {
			while(datosProducto.next()) {											
				Arbol arbol = new Arbol(datosProducto.getInt(1), datosProducto.getInt(4), datosProducto.getFloat(3), 
						datosProducto.getFloat(2));
				arbol.retirarArbol(conexion, id, cantRetiro);					
			}					
		} catch (SQLException e) {
			System.out.println("No se puede retirar el arbol");
		}
		
	}
	
	
	public static void retirarFlor(Connection conexion, ResultSet datosProducto, int id, int cantRetiro) {
		try {
			while(datosProducto.next()) {										
				Flor flor = new Flor(datosProducto.getInt(1), datosProducto.getInt(4), datosProducto.getFloat(3), 
						datosProducto.getString(2));
				flor.retirarFlor(conexion, id, cantRetiro);					
			}				
		} catch (SQLException e) {
			System.out.println("No se puede retirar la flor");			
		}
		
	}
		
	
	public static void retirarDecoracion(Connection conexion, ResultSet datosProducto, int id, int cantRetiro) {
		try {
			while(datosProducto.next()) {										
				Decoracion decoracion = new Decoracion(datosProducto.getInt(1), datosProducto.getInt(4), datosProducto.getFloat(3), 
						datosProducto.getString(2));
				decoracion.retirarDecoracion(conexion, id, cantRetiro);					
			}				
		} catch (SQLException e) {
			System.out.println("No se puede retirar la decoracion");
		}
	}
	
	
	public static int buscarTipoProducto(Connection conexion, int id) {
		ResultSet producto;
		int resultado = 0;
		String query = "SELECT id_tipo_producto FROM productos WHERE id_producto = " + id;
		producto = queries.ejecutarQuery(conexion, query);	
		try {
			while(producto.next()) {
				return resultado = producto.getInt(1); //En esta posicion tengo el id_tipo_producto
				//return new Producto(producto.getInt(1),producto.getInt(3), producto.getFloat(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	public static void imprimirStock(Connection conexion) {
		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Imprimir stock de arboles" +
			"\n2. Imprimir stock de plantas" +
			"\n3. Imprimir stock de decoraciones" +
			"\n0. Volver al menu anterior");
		
		switch(in.nextInt()) {				
			case 1:
				imprimirStockArboles(conexion);
				break;
				
			case 2:
				imprimirStockFlores(conexion);
				break;
				
			case 3:
				imprimirStockDecoraciones(conexion);
				break;
				
			case 0:
				menuPrincipal();
				break;
			
			default:
				System.out.println("Opcion no valida");
				break;
		}		
	}

	
	public static void imprimirStockArboles(Connection conexion) {
		
		String query = "SELECT SUM(cantidad) AS 'Cantidad de arboles' FROM arbol";		
		ResultSet result = queries.ejecutarQuery(conexion, query);
		
		try {
			while(result.next()) {
				System.out.println("Hay en stock " +result.getString("Cantidad de arboles") + " Arboles");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	
	public static void imprimirStockFlores(Connection conexion) {
		
		String query = "SELECT SUM(cantidad) AS 'Cantidad de flores' FROM flor";		
		ResultSet result = queries.ejecutarQuery(conexion, query);
		
		try {
			while(result.next()) {
				System.out.println("Hay en stock " + result.getString("Cantidad de flores") + " Flores");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	public static void imprimirStockDecoraciones(Connection conexion) {
		
		String query = "SELECT SUM(cantidad) AS 'Cantidad de decoraciones' FROM decoracion";		
		ResultSet result = queries.ejecutarQuery(conexion, query);
		
		try {
			while(result.next()) {
				System.out.println("Hay en stock " + result.getString("Cantidad de decoraciones") + " Decoraciones");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	

	public static void crearTicket(Connection conexion) {
	
		int id;
		int cant;
		String continuar = "n";
		System.out.println("Inserte el nro de la comanda");
		int idComanda = in.nextInt();
		System.out.println("Ingrese la fecha de la comanda (AAAA-MM-DD");
		String fecha = in.next();
		String query = "INSERT INTO comandas(id_comanda, Dia) VALUES(" + idComanda + ", '" + fecha + "')";
		
		queries.insertar(conexion, query);
		
		do {
			System.out.println("Ingrese el id del producto");
			id = in.nextInt();
			System.out.println("Ingrese la cantidad");
			cant = in.nextInt();
			retirarProducto(conexion, id, cant);
			
			query = "INSERT INTO detalle_comanda(id_comanda, id_producto, cantidad) VALUES(" + idComanda + ", " + id + ", " 
				+ cant + ")";
			
			queries.insertar(conexion, query);
			
			System.out.println("Quiere ingresar mas productos?(S/N)");
			continuar = in.next();
			
		}while(continuar.equalsIgnoreCase("s"));			
		precioFinalComanda(conexion, idComanda);
	}
	
	
	public static void precioFinalComanda(Connection conexionActual, int idComanda) {
		
		String query = "SELECT SUM(p.precio * dc.cantidad) AS 'Precio final' FROM productos AS p INNER JOIN detalle_comanda AS dc " +
			"ON p.id_producto = dc.id_producto INNER JOIN comandas AS c ON dc.id_comanda = c.id_comanda WHERE c.id_comanda =" 
			+ idComanda;	
		
		ResultSet resultado = queries.ejecutarQuery(conexionActual, query);
		
		try {
			while(resultado.next()) {
				System.out.println("El precio final de la comanda es: " + resultado.getInt(1) + "€\n");
			}
		}
		catch(SQLException e){
			System.out.println("No se puede hacer la consulta");
		}		
	}
	

	//Conecto a una base de datos ya existente. Me devuelve un Objeto del tipo Connection. Trabajare sobre esa conexion
	public static Connection conectarBaseDatos() {
		
		System.out.println("Ingrese el nombre de la base de datos a la que quiere conectarse");
		nombreDB = in.next();
		
		ConexionBaseDatos conexion = new ConexionBaseDatos();
		Connection conect = conexion.conectarDB(nombreDB);	
		
		return conect;		
	}
	
	
	//Creo la base de datos desde cero. Devuelvo el Objeto Connection. Trabajare sobre esa conexion
	public static Connection crearFloristeria() {

		System.out.println("Ingrese el nombre de la floristeria");
		
		nombreDB = in.next();
		
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

	
	
	public static void menuHistoricoVentas(Connection conexion) {
		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Imprimir ventas totales" +
			"\n2. Imprimir ventas de un determinado período" +
			"\n0. Volver al menu anterior");
		
		switch(in.nextInt()) {
			case 1:
				System.out.println("Imprimir ventas totales");
				break;
				
			case 2:
				historicoVentas(conexion);
				break;
				
			case 0:
				menuPrincipal();
				break;
				
			default:
				System.out.println("Opcion no valida");
				break;		
		}		
	}

	

	public static void historicoVentas(Connection conexion) {
		
		System.out.println("Ingrese la fecha de comienzo de la consulta (formato AAAA-MM-DD)");
		String comienzo = in.next();
		System.out.println("Ingrese la fecha de fin de la consulta (formato AAAA-MM-DD)");
		String fin = in.next();
				
		String query = "SELECT * FROM comandas AS c WHERE c.Dia >= '" +  comienzo +	"' AND c.Dia <= '" + fin + "'";
		ResultSet resultado = queries.ejecutarQuery(conexion, query);
		
		try {
			while(resultado.next()) {
				System.out.println("ID Comanda: " + resultado.getString(1) + " - Fecha: " + resultado.getString(2));
			}
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}		
	}


/*
	public static void calcularVentasTotal(Connection conexionActual) {
		
		String query = "SELECT SUM(p.precio * dc.cantidad) AS 'Total ganado' FROM productos AS p INNER JOIN detalle_comanda AS dc " +
			"ON p.id_producto = dc.id_producto";
		try {
			Statement consulta = conexionActual.createStatement();
			ResultSet resultado = consulta.executeQuery(query);
			while(resultado.next()) {
				System.out.println("En total ha ganado: " + resultado.getDouble(1) + "€\n");
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}		
	}
*/
	
/*
	public static void calcularValorTotalStock(Connection conexionActual) {
		
		String query = "SELECT SUM(p.precio * p.cantidad) AS 'Total valor stock' FROM productos AS p";
		
		try {
			Statement consulta = conexionActual.createStatement();
			ResultSet resultado = consulta.executeQuery(query);
			while(resultado.next()) {
				System.out.println("El valor total del stock es: " + resultado.getDouble(1) + "€\n");
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}
	}
	
	*/
}
