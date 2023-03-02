package s3_03_pablogarciabarros;

import java.sql.Connection;
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
			return ProductoraFabricas.getFabrica(5).nuevaConexion().conectarDB();
		}
		else if(opcion == 2) {
			return ProductoraFabricas.getFabrica(5).nuevaConexion().crearNuevaDB();
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
			 retirarProducto(conexion);
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
				Arbol arbol = (Arbol)ProductoraFabricas.getFabrica(opcion).nuevoProducto();
				arbol.agregarProducto(conexion);
				break;
				
			case 2:
				Flor flor = (Flor)ProductoraFabricas.getFabrica(opcion).nuevoProducto();
				flor.agregarProducto(conexion);
				break;
				
			case 3:
				Decoracion decoracion = (Decoracion)ProductoraFabricas.getFabrica(opcion).nuevoProducto();
				decoracion.agregarProducto(conexion);
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
		
		int tipoProd = queries.getIdTipoProducto(conexion, id);
		int idProdCargado = queries.getIdProducto(conexion, id);
		
		if(idProdCargado == 0) {
			System.out.println("El producto no esta cargado en la Base de Datos");
		}
		else {
			queries.sumarStock(conexion, id, cant, "producto"); //Paso como parametro la conexion, el id, la cantidad y la tabla que quiero actualizar
		}
		
		switch(tipoProd) {
			case 1:
				queries.sumarStock(conexion, id, cant, "arbol");
				break;
			case 2:
				queries.sumarStock(conexion, id, cant, "flor");
				break;
			case 3:
				queries.sumarStock(conexion, id, cant, "decoracion");
				break;
		}
	}


	public static int[] retirarProducto(Connection conexion) {
		System.out.println("Ingrese el id del producto");
		int id = in.nextInt();
		System.out.println("Ingrese la cantidad a retirar");
		int cant = in.nextInt();
		
		int tipoProd = queries.getIdTipoProducto(conexion, id);
		int idProdCargado = queries.getIdProducto(conexion, id);
		
		if(idProdCargado == 0) {
			System.out.println("El producto no esta cargado en la Base de Datos");
		}
		else {
			int stock = queries.getStock(conexion, id);
			if(cant <= stock) {
				queries.restarStock(conexion, id, cant, "producto");	//Paso como parametro la conexion, el id, la cantidad y la tabla que quiero actualizar
				switch(tipoProd) {
				case 1:
					queries.restarStock(conexion, id, cant, "arbol");
					return new int []{id, cant};
				case 2:
					queries.restarStock(conexion, id, cant, "flor");
					return new int []{id, cant};
				case 3:
					queries.restarStock(conexion, id, cant, "decoracion");
					return new int []{id, cant};
				}
			}
			else {
				System.out.println("No se puede retirar mas de lo cargado");
				
			}			
		}
		return new int[] {0,0};
	}


	//TODO ESTO LO PUEDO HACER EN UNO SOLO
	//DESDE AQUI
	
	//PUEDO REPLICAR LO DE RETIRARPRODUCTO HACIENDO LAS QUERIS EN LA CLASE QUERIESSQL
	
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
	
	//HASTA AQUI

	public static void crearTicket(Connection conexion) {
			
		String continuar = "n";
		boolean flag = false;
		
		Ticket ticket = (Ticket)ProductoraFabricas.getFabrica(4).nuevoTicket();
		int[] idCant;	
		do {
			idCant = retirarProducto(conexion);
			if(idCant[0] != 0 && flag == false) {
				ticket.insertarTicket(conexion, "INSERT INTO comandas(id_comanda, Dia) VALUES(" + ticket.getId() + ", '" + 
						ticket.getFecha() + "')");
				queries.insertarDetalleComanda(conexion, ticket.getId(), idCant[0], idCant[1]);
				flag = true;
			}
			else if(idCant[0] != 0 && flag == true){
				queries.insertarDetalleComanda(conexion, ticket.getId(), idCant[0], idCant[1]);
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
		
		ResultSet resultado = queries.ejecutarQuery(conexionActual, "SELECT SUM(p.precio * dc.cantidad) AS 'Precio final' FROM producto " +
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
				if(resultado != null) {
					System.out.println("ID Comanda: " + resultado.getString(1) + " - Fecha: " + resultado.getString(2));
				}
			}
		}
		catch(SQLException e) {
			System.out.println("No se puede hacer la consulta");
		}		
	}


	public static void calcularVentasTotal(Connection conexion) {
		
		ResultSet resultado = queries.ejecutarQuery(conexion, "SELECT SUM(p.precio * dc.cantidad) AS 'Total ganado' FROM producto " +
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
		
		ResultSet resultado = queries.ejecutarQuery(conexion, "SELECT SUM(p.precio * p.cantidad) AS 'Total valor stock' FROM producto " +
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
