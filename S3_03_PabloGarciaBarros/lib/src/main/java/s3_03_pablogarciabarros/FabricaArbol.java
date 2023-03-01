package s3_03_pablogarciabarros;

import java.util.Scanner;

//Desde esta clase instancio un objeto de la clase Arbol, sin saber como es la clase arbol.

public class FabricaArbol extends FabricaAbstracta{

	Scanner in = new Scanner(System.in);
	
	@Override
	Producto tipoProducto() {		
		System.out.println("Ingrese el ID del Arbol");
		int id = in.nextInt();
		System.out.println("Ingrese el precio del Arbol");
		float precio = in.nextFloat();
		System.out.println("Ingrese la altura del Arbol");
		float altura = in.nextFloat();
		System.out.println("Ingrese la cantidad de Arboles");
		int cant = in.nextInt();		
		
		return new Arbol(id, cant, precio, altura);
	}

	@Override
	Ticket creoTicket() {
		return null;
	}

	@Override
	ConexionBaseDatos creoConexion() {
		return null;
	}

}
