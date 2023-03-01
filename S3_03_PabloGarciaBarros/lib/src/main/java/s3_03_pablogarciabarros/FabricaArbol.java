package s3_03_pablogarciabarros;

import java.util.Scanner;

public class FabricaArbol extends FabricaAbstracta{

	@Override
	Producto tipoProducto(int opcionProducto) {
		
		Scanner in = new Scanner(System.in);
		
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

}
