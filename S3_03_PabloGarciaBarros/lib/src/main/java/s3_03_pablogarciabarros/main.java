package s3_03_pablogarciabarros;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class main {

	static Scanner entrada = new Scanner(System.in);
	
	static ConexionBaseDatos conexion = new ConexionBaseDatos();
	static Connection conect = conexion.conectarBD();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Prueba
		
		int opcion;
		
		do{
			opcion = menuPrincipal();
			manejoMenu(opcion);
			
		}while(opcion != 0);
	}

	public static int menuPrincipal() {
		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Crear nueva floristeria" +
			"\n2. Agregar productos"	+
			"\n3. Retirar productos" +
			"\n4. Imprimir stock" +
			"\n5. Crear ticket de venta" +
			"\n6. Mostrar historico de ventas" +
			"\n0. Salir de la aplicacion");	
		
		return entrada.nextInt();
	}
	
	public static void menuAgregarProductos() {
		
		System.out.println("INGRESE LA OPCION DESEADA: " +
			"\n1. Agregar arbol" +
			"\n2. Agregar planta" +
			"\n3. Agregar decoracion" +
			"\n0. Volver al menu anterior");		
		
		switch(entrada.nextInt()) {
			case 1:
				System.out.println("Agregar arbol");
				break;
				
			case 2:
				System.out.println("Agregar planta");
				break;
				
			case 3:
				System.out.println("Agregar decoracion");
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
	
	
	public static void menuImprimirStock() {
		
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
				break;
				
			case 3:
				System.out.println("Imprimir stock de plantas");
				break;
				
			case 4:
				System.out.println("Imprimir stock de decoraciones");
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
	
	public static void manejoMenu(int opcion) {
		
		switch(opcion) {
		
		 case 1:
			 crearFloristeria();
			 break;
		 
		 case 2:
			 menuAgregarProductos();
			 break;
		 
		 case 3:
			 menuRetirarProductos();
			 break;
		 
		 case 4:
			 menuImprimirStock();
			 break;
			 
		 case 5:
			 crearTicket();
			 break;
		 
		 case 6:
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
	
	public static void crearFloristeria() {

		System.out.println("Ingrese el nombre de la floristeria");
		
		String nombreDB = entrada.next();
		
		String borrarDB = "DROP DATABASE IF EXISTS " + nombreDB; 		
		String query = "CREATE DATABASE " + nombreDB + " CHARACTER SET utf8mb4";
		
		try {
			Statement borrar = conect.createStatement();
			borrar.execute(borrarDB);
			Statement sqlQuery = conect.createStatement();
			sqlQuery.execute(query);
			System.out.println("Se creo la base de datos");
		}
		catch(SQLException e) {
			System.out.println("No se pudo crear la base de datos");
		}		
	}
	
	public static void crearTicket() {
		System.out.println("Creo ticket nuevo");
	}
}
