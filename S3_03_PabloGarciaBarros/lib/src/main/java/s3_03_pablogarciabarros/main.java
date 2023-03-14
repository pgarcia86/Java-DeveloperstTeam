package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	static Scanner in = new Scanner(System.in);
	static String nombreDB;
	static FabricaAbstracta fabrica = null;
	static QueriesConsulta queries = null;
	static Producto productoAuxiliar = null; 
	static Ticket nuevoTicket = null;
	static DetalleComanda detalleComanda = null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int opcion;
		Connection conexion = null;
		queries = new QueriesConsulta();
		
		do {
		conexion = primerMenu();
		}while(conexion == null);
		
		
		do{
			opcion = menuPrincipal();
			manejoMenu(conexion, opcion);
			
		}while(opcion != 0);		
	}
	
	//EN ESTE PRIMER MENU PREGUNTO SI ES UNA BASE DE DATOS NUEVA O SI LA DEBE CREAR
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

	//ESTE METODO SOLAMENTE IMPRIME POR PANTALLA LAS OPCIONES DISPONIBLES PARA EL MENU
	public static int menuPrincipal() {		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Insertar un nuevo producto"	+
			"\n2. Sumar un productos" +
			"\n3. Retirar un producto" +
			"\n4. Imprimir stock" +
			"\n5. Crear ticket de venta" +
			"\n6. Mostrar historico de ventas" +
			"\n7. Calcular total del valor del stock" +
			"\n0. Salir de la aplicacion");	
		
		return in.nextInt();
	}	
	
	//ESTE METODO MANEJA EL MENU	
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
			int cantidad = in.nextInt();
			 retirarProducto(conexion, id, cantidad);
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
			 System.out.println("El valor total del stock es: " + QueriesConsulta.calcularValorTotalStock(conexion) + "€");
			 break;
			 
		 case 0:
			 System.out.println("SALIENDO DE LA APLICACION");
			 break;
			 
		 default:
			 System.out.println("Opcion no valida");
			 break;			 
		}		
	}	

	
	
	/* 
	 PARA ESTOS METODOS CREO UN PRODUCTO LOCAL, LO MODIFICO Y LUEGO LO CARGO EN LA BASE DE DATOS
	 */	
	
	//ESTE METODO INSERTA EN LA BASE DE DATOS EL PRODUCTO INGRESADO ---- FUNCIONA PERFECTO
	public static void agregarProductoNuevo(Connection conexion) {		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Agregar arbol" +
			"\n2. Agregar planta" +
			"\n3. Agregar decoracion" +
			"\n0. Volver al menu anterior");		
		
		int opcion = in.nextInt();
		
		switch(opcion) {
			case 1:
				ProductoraFabricas.getFabrica(opcion).nuevoProducto().insertar(conexion);			
				break;
				
			case 2:
				ProductoraFabricas.getFabrica(opcion).nuevoProducto().insertar(conexion);
				break;
				
			case 3:
				ProductoraFabricas.getFabrica(opcion).nuevoProducto().insertar(conexion);
				break;
				
			case 0:
				menuPrincipal();
				break;
				
			default:
				System.out.println("Opcion no valida");
				break;
		}		
	}

	//ESTE METODO SUMA STOCK
	public static void agregarProducto(Connection conexion) {		
		System.out.println("Ingrese el id del producto");
		int id = in.nextInt();
		System.out.println("Ingrese la cantidad a agregar");
		int cantidad = in.nextInt();
		
		if(QueriesConsulta.buscar(conexion, id) == false) {
			System.out.println("El producto no esta cargado en la Base de datos");
		}
		else {
			//SI ENCONTRO EL PRODUCTO, EL METODO CARGAR PRODUCTO ME CREA UN OBJETO DEL TIPO PRODUCTO CON LOS DATOS DEL PRODUCTO CARGADO
			productoAuxiliar = cargarProducto(conexion, id); 
			productoAuxiliar.agregar(conexion, id, cantidad);
		}			
	}		
	
	//ESTE METODO RESTA STOCK
	public static Producto retirarProducto(Connection conexion, int id, int cantidad) {		
		if(QueriesConsulta.buscar(conexion, id) == false) {
			System.out.println("El producto no esta cargado en la Base de datos");
			return null;
		}
		else {
			Producto productoAuxiliar = cargarProducto(conexion, id); //CREO EL METODO EN PRODUCTO PARA AGREGARPRODUCTO
			if(productoAuxiliar.getCantidad() >= cantidad) {
				productoAuxiliar.retirar(conexion, id, cantidad);
				System.out.println("SE RETIRO EL PRODUCTO CORRECTAMENTE");
				return productoAuxiliar;
			}
			else {
				System.out.println("NO SE PUEDE RETIRAR MAS DE LO QUE TIENES EN STOCK");
				return null;
			}
		}
		
	}
		
	
	
	/*
	 * 
	 * 
	 PARA ESTOS METODOS EJECUTO DIRECTAMENTE LOS QUERIES PORQUE NO NECESITO CARGAR EL PRODUCTO PORQUE SOLO NECESITO OBTENER DATOS
	 ESTOS QUERIES ESTAN EN LA CLASE QUERIES_CONSULTA
	 *
	 *
	 */	
	
	//ESTE METODO IMPRIME POR PANTALLA EL MENU QUE IMPRIME POR PANTALLA
	public static void menuImprimirStock(Connection conexion) {		
		System.out.println("INGRESE LA OPCION DESEADA: " +
				"\n1. Imprimir stock arboles" +
				"\n2. Imprimir stock flores" +
				"\n3. Imprimir stock decoracion" +
				"\n4. Imprimir stock de un producto" +
				"\n5. Imprimir stock total" +
				"\n0. Volver al menu anterior");
		
		int tipoProducto = in.nextInt();
		
		switch(tipoProducto) {
			case 1:	//TENGO QUE CONTAR TODOS LOS ARBOLES
				imprimirStockCategoria(conexion, 1);
				break;
				
			case 2:	//TENGO QUE CONTAR LAS FLORES
				imprimirStockCategoria(conexion, 2);
				break;
				
			case 3:	//TENGO QUE CONTAR LAS DECORACION
				imprimirStockCategoria(conexion, 3);
				break;
				
			case 4:	//TENGO QUE CONTAR TODOS LOS PRODUCTOS
				imprimirStockProducto(conexion);
				break;
				
			case 5:
				imprimirStockTotal(conexion);
				break;
				
			case 0:
				menuPrincipal();
				break;
				
			default:
				System.out.println("Opcion no valida");
				break;
		}
		
	}
	
	//ESTE METODO IMPRIME EL STOCK
	public static void imprimirStockProducto(Connection conexion) {		
		System.out.println("Ingrese el id del producto");
		int id = in.nextInt();																		 
																		
		if(QueriesConsulta.buscar(conexion, id) == false) {
			System.out.println("El producto no esta cargado en la Base de datos");
		}
		else {	
			System.out.println("Hay en stock: " + QueriesConsulta.getStock(conexion, id));
		}		
	}
	
	//IMPRIMIR STOCK PARTICULAR
	public static void imprimirStockCategoria(Connection conexion, int tipoProducto) {			
		System.out.println("Hay en stock: " + QueriesConsulta.getStockCategoria(conexion, tipoProducto));
	}

	//IMPRIME EL STOCK TOTAL
	public static void imprimirStockTotal(Connection conexion) {		
		System.out.println("Hay en stock: " + QueriesConsulta.getStockTotal(conexion));
	}
	
	
	/*
	 * 
	 * 
	 ESTOS METODOS SON PARA CREAR TICKET
	 	ESTO FUNCIONA
	 * 
	 * 
	 */
		
	public static void crearTicket(Connection conexion) {			
		String continuar = "n";		
		nuevoTicket = (Ticket)ProductoraFabricas.getFabrica(4).nuevoTicket();	
		nuevoTicket.insertarTicket(conexion);
		
		do {		
			System.out.println("Ingrese el id del producto");
			int id = in.nextInt();
			System.out.println("Ingrese la cantidad a retirar");
			int cantidad = in.nextInt();			
			productoAuxiliar = retirarProducto(conexion, id, cantidad);			
			
			if(productoAuxiliar != null) {

				//CREO UN OBJETO DE LA CLASE DETALLEPRODUCTO Y LO INSERTO EN LA TABLA DETALLE PRODUCTO				
				detalleComanda = (DetalleComanda)ProductoraFabricas.getFabrica(7).nuevoDetalleComanda(nuevoTicket.getId(), productoAuxiliar.getId(), cantidad);
				detalleComanda.insertar(conexion);
				
				System.out.println("Quiere ingresar mas productos en el ticket?(S/N)");
				continuar = in.next();
			}			
		}while(continuar.equalsIgnoreCase("s"));
		System.out.println("El precio final de la comanda es " + QueriesConsulta.precioComanda(conexion, nuevoTicket.getId()) + "€");
	}
	
	
	/*
	 * 
	 * 
	 ESTOS METODOS SON PARA EL HISTORICO DE VENTAS	 
	 *
	 *  
	 */	

	//IMPRIME EL MENU DEL HISTORICO DE VENTAS
	public static void menuHistoricoVentas(Connection conexion) {
		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Imprimir ventas totales" +
			"\n2. Imprimir ventas de un determinado período" +
			"\n0. Volver al menu anterior");
		
		switch(in.nextInt()) {
			case 1:
				System.out.println("Se vendieron en total "	+ QueriesConsulta.calcularTotalVentas(conexion) + "€");
				break;
				
			case 2:
				historicoVentasFecha(conexion);
				break;
				
			case 0:
				menuPrincipal();
				break;
				
			default:
				System.out.println("Opcion no valida");
				break;		
		}		
	}

	//
	public static void historicoVentasFecha(Connection conexion) {
		
		System.out.println("Ingrese la fecha de comienzo de la consulta (formato AAAA-MM-DD)");
		String comienzo = in.next();
		System.out.println("Ingrese la fecha de fin de la consulta (formato AAAA-MM-DD)");
		String fin = in.next();		
		
		QueriesConsulta.buscarComandasFecha(conexion, comienzo, fin);
	}
	
	//CREO EL PRODUCTO CON LOS DATOS QUE LE PASO POR PARAMETRO QUE LOS OBTENGO DE LOS METODOS ANTERIORES
	public static Producto cargarProducto(Connection conexion, int id) {		
		int cantidad = QueriesConsulta.getCantidad(conexion, id);
		float precio = QueriesConsulta.getPrecio(conexion, id);
		int tipoProducto = QueriesConsulta.getTipoProducto(conexion, id);
		
		return ProductoraFabricas.getFabrica(6).cargarProducto(id, cantidad, precio, tipoProducto); 	
	}
		
}
