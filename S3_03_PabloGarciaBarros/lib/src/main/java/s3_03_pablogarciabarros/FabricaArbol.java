package s3_03_pablogarciabarros;

import java.util.Scanner;

public class FabricaArbol extends FabricaAbstracta{

	Scanner in = new Scanner(System.in);
	
	@Override
	Producto nuevoProducto() {
		
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
	Ticket nuevoTicket() {
		return null;
	}


	@Override
	ConexionBaseDatos nuevaConexion() {
		return null;
	}
}
