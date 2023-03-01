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
			return ProductoraFabricas.getFabrica(5).creoConexion().conectarDB();
		}
		else if(opcion == 2) {
			return ProductoraFabricas.getFabrica(5).creoConexion().conectarNuevaBD();
		}
		else {
			return null;	
		}		
	}

	
	public static int menuPrincipal() {		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Agregar un producto nuevo"	+
			"\n2. Actulizar cantidad de productos ya existentes" +
			"\n3. Retirar un producto" +
			"\n4. Imprimir stock" +
			"\n5. Crear ticket de venta" +
			"\n6. Mostrar historico de ventas" +
			"\n7. Calcular total del valor del stock" +
			"\n0. Salir de la aplicacion");	
		
		return in.nextInt();
	}
	
	
	public static void manejoMenu(Connection conexion, int opcion) {
		
		switch(opcion) {		 
		 case 1:
			 agregarProductoNuevo(conexion);
			 break;
			
		 case 2:
			 agregarProducto(conexion);
			 break;
		 
		 case 3:
			 System.out.println("Ingrese el id del producto");
			 int id = in.nextInt();
			 System.out.println("Ingrese la cantidad a retirar");
			 int cantRetiro = in.nextInt();
			 retirarProducto(conexion, id, cantRetiro);
			 break;
			 
		 case 4:
			 menuImprimirStock(conexion);
			 break;
		 
		 case 5:
			 crearTicket(conexion);
			 break;
			 
		 case 6:
			 menuHistoricoVentas(conexion);
			 break;
			 
		 case 7: 
			 calcularValorTotalStock(conexion);
			 break;
			 
		 case 0:
			 System.out.println("SALIENDO DE LA APLICACION");
			 break;
			 
		 default:
			 System.out.println("Opcion no valida");
			 break;			 
		}		
	}	

	
	public static void agregarProductoNuevo(Connection conexion) {
		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Agregar arbol" +
			"\n2. Agregar planta" +
			"\n3. Agregar decoracion" +
			"\n0. Volver al menu anterior");		
		
		int opcion = in.nextInt();
		
		switch(opcion) {
			case 1:
				Arbol arbol = (Arbol)ProductoraFabricas.getFabrica(opcion).tipoProducto();
				arbol.agregarProducto(conexion, 1);
				arbol.agregarArbol(conexion);
				break;
				
			case 2:
				Flor flor = (Flor)ProductoraFabricas.getFabrica(opcion).tipoProducto();
				flor.agregarProducto(conexion, 2);
				flor.agregarFlor(conexion);
				break;
				
			case 3:
				Decoracion decoracion = (Decoracion)ProductoraFabricas.getFabrica(opcion).tipoProducto();
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
		

	public static void agregarProducto(Connection conexion) {
		System.out.println("Ingrese el id del producto");
		int id = in.nextInt();
		System.out.println("Ingrese la cantidad a añadir");
		int cant = in.nextInt();
		
		int tipoProd = buscarTipoProducto(conexion, id);
		int idProdCargado = buscarProducto(conexion, id);
		
		if(idProdCargado == 0) {
			System.out.println("El producto no esta cargado en la Base de Datos");
		}
		else {
			queries.actualizar(conexion, "UPDATE productos SET cantidad = (cantidad + " + cant + ") WHERE id_producto = " + id);
		}
		
		switch(tipoProd) {
			case 1:
				queries.actualizar(conexion, "UPDATE arbol SET cantidad = (cantidad + " + cant + ") WHERE id_arbol = " + id);
				break;
			case 2:
				queries.actualizar(conexion, "UPDATE flor SET cantidad = (cantidad + " + cant + ") WHERE id_flor = " + id);
				break;
			case 3:
				queries.actualizar(conexion, "UPDATE decoracion SET cantidad = (cantidad + " + cant + ") WHERE id_decoracion = " + id);
				break;
		}
	}
	
	
	public static int buscarProducto(Connection conexion, int id) {
		
		ResultSet resultado = queries.ejecutarQuery(conexion, "SELECT id_producto FROM productos WHERE id_producto = " + id); 
		
		try {
			while(resultado.next()) {
				return resultado.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("ERROR");
		}
		return 0;
	}

	
	public static int buscarTipoProducto(Connection conexion, int id) {
		ResultSet producto;
		int resultado = 0;
		producto = queries.ejecutarQuery(conexion, "SELECT id_tipo_producto FROM productos WHERE id_producto = " + id);	
		try {
			while(producto.next()) {
				return resultado = producto.getInt(1); 
			}
		} catch (SQLException e) {
			System.out.println("ERROR");
		}
		return resultado;
	}

	
 	public static boolean retirarProducto(Connection conexion, int id, int cantRetiro) {
		
		int tipoProducto = buscarTipoProducto(conexion, id); //En la posicion 1 del producto tengo el tipo del producto		
		String query = "";
		ResultSet datosProducto = null;
		
		switch(tipoProducto) {
			case 1:
				datosProducto = queries.ejecutarQuery(conexion, "SELECT * FROM arbol WHERE id_arbol = " + id);
				break;
			case 2:
				datosProducto = queries.ejecutarQuery(conexion, "SELECT * FROM flor WHERE id_flor = " + id);
				break;
			case 3:
				datosProducto = queries.ejecutarQuery(conexion, "SELECT * FROM decoracion WHERE id_decoracion = " + id);
				break;
			default:
				break;
		}		
		
		switch(tipoProducto) {
			case 1:
				return retirarArbol(conexion, datosProducto, id, cantRetiro);			
			case 2:
				return retirarFlor(conexion, datosProducto, id, cantRetiro);
			case 3:
				return retirarDecoracion(conexion, datosProducto, id, cantRetiro);
		}
		return false;
	}
	
	
	public static boolean retirarArbol(Connection conexion, ResultSet datosProducto, int id, int cantRetiro) {
		try {
			while(datosProducto.next()) {											
				Arbol arbol = new Arbol(datosProducto.getInt(1), datosProducto.getInt(4), datosProducto.getFloat(3), 
						datosProducto.getFloat(2));
				return arbol.retirarArbol(conexion, id, cantRetiro);					
			}					
		} catch (SQLException e) {
			System.out.println("No se puede retirar el arbol");
		}
		return false;
	}
	
	
	public static boolean retirarFlor(Connection conexion, ResultSet datosProducto, int id, int cantRetiro) {
		try {
			while(datosProducto.next()) {										
				Flor flor = new Flor(datosProducto.getInt(1), datosProducto.getInt(4), datosProducto.getFloat(3), 
						datosProducto.getString(2));
				return flor.retirarFlor(conexion, id, cantRetiro);					
			}				
		} catch (SQLException e) {
			System.out.println("No se puede retirar la flor");			
		}
		return false;
	}
		
	
	public static boolean retirarDecoracion(Connection conexion, ResultSet datosProducto, int id, int cantRetiro) {
		try {
			while(datosProducto.next()) {										
				Decoracion decoracion = new Decoracion(datosProducto.getInt(1), datosProducto.getInt(4), datosProducto.getFloat(3), 
						datosProducto.getString(2));
				return decoracion.retirarDecoracion(conexion, id, cantRetiro);					
			}				
		} catch (SQLException e) {
			System.out.println("No se puede retirar la decoracion");
		}
		return false;
	}


	public static void menuImprimirStock(Connection conexion) {
		
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
			
		ResultSet result = queries.ejecutarQuery(conexion, "SELECT SUM(cantidad) AS 'Cantidad de arboles' FROM arbol");
		
		try {
			while(result.next()) {
				System.out.println("Hay en stock " +result.getString("Cantidad de arboles") + " Arboles");
			}
		} catch (SQLException e) {
			System.out.println("ERROR");
		}			
	}
	
	
	public static void imprimirStockFlores(Connection conexion) {
		
		ResultSet result = queries.ejecutarQuery(conexion, "SELECT SUM(cantidad) AS 'Cantidad de flores' FROM flor");		
		try {
			while(result.next()) {
				System.out.println("Hay en stock " + result.getString("Cantidad de flores") + " Flores");
			}
		} catch (SQLException e) {
			System.out.println("ERROR");
		}		
	}
	
	
	public static void imprimirStockDecoraciones(Connection conexion) {
		
		ResultSet result = queries.ejecutarQuery(conexion, "SELECT SUM(cantidad) AS 'Cantidad de decoraciones' FROM decoracion");		
		try {
			while(result.next()) {
				System.out.println("Hay en stock " + result.getString("Cantidad de decoraciones") + " Decoraciones");
			}
		} catch (SQLException e) {
			System.out.println("No");
		}			
	}
	

	public static void crearTicket(Connection conexion) {
	
		int id;
		int cant;
		String continuar = "n";
		
		Ticket ticket = (Ticket)ProductoraFabricas.getFabrica(4).creoTicket();
		do {
			System.out.println("Ingrese el id del producto");
			id = in.nextInt();
			System.out.println("Ingrese la cantidad");
			cant = in.nextInt();
			if(retirarProducto(conexion, id, cant)) {
				ticket.insertarTicket(conexion, "INSERT INTO comandas(id_comanda, Dia) VALUES(" + ticket.getId() + ", '" + 
						ticket.getFecha() + "')");
				queries.actualizar(conexion, "INSERT INTO detalle_comanda(id_comanda, id_producto, cantidad) VALUES(" + ticket.getId() +
						", " + id + ", " + cant + ")");
			}
			else {
				System.out.println("No se pudo añadir el producto a la comanda");
			}			
			System.out.println("Quiere ingresar mas productos?(S/N)");
			continuar = in.next();
			
		}while(continuar.equalsIgnoreCase("s"));			
		precioFinalComanda(conexion, ticket.getId());
	}
	
	
	public static void precioFinalComanda(Connection conexionActual, int idComanda) {
		
		ResultSet resultado = queries.ejecutarQuery(conexionActual, "SELECT SUM(p.precio * dc.cantidad) AS 'Precio final' FROM productos " +
			"AS p INNER JOIN detalle_comanda AS dc ON p.id_producto = dc.id_producto INNER JOIN comandas AS c ON " +
			"dc.id_comanda = c.id_comanda WHERE c.id_comanda = " + idComanda);
		
		try {
			while(resultado.next()) {
				System.out.println("El precio final de la comanda es: " + resultado.getInt(1) + "€\n");
			}
		}
		catch(SQLException e){
			System.out.println("No se puede hacer la consulta");
		}		
	}
	
		
	public static void menuHistoricoVentas(Connection conexion) {
		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Imprimir ventas totales" +
			"\n2. Imprimir ventas de un determinado período" +
			"\n0. Volver al menu anterior");
		
		switch(in.nextInt()) {
			case 1:
				calcularVentasTotal(conexion);
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
		
		ResultSet resultado = queries.ejecutarQuery(conexion, "SELECT * FROM comandas AS c WHERE c.Dia >= '" +  comienzo +	
				"' AND c.Dia <= '" + fin + "'");
		
		try {
			while(resultado.next()) {
				System.out.println("ID Comanda: " + resultado.getString(1) + " - Fecha: " + resultado.getString(2));
			}
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}		
	}


	public static void calcularVentasTotal(Connection conexion) {
		
		ResultSet resultado = queries.ejecutarQuery(conexion, "SELECT SUM(p.precio * dc.cantidad) AS 'Total ganado' FROM productos " +
				"AS p INNER JOIN detalle_comanda AS dc ON p.id_producto = dc.id_producto");
		try {
			while(resultado.next()) {
				System.out.println("En total ha vendido: " + resultado.getDouble(1) + "€\n");
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}		
	}

	
	public static void calcularValorTotalStock(Connection conexion) {
		
		ResultSet resultado = queries.ejecutarQuery(conexion, "SELECT SUM(p.precio * p.cantidad) AS 'Total valor stock' FROM productos " +
				"AS p");		
		try {
			while(resultado.next()) {
				System.out.println("El valor total del stock es: " + resultado.getDouble(1) + "€\n");
			}			
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}
	}
	
}
