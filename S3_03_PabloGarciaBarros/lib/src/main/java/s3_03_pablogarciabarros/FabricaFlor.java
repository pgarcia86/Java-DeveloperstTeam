package s3_03_pablogarciabarros;

import java.util.Scanner;

//Desde esta clase instancio un objeto de la clase Flor, sin saber como es la clase flor.


public class FabricaFlor extends FabricaAbstracta{

	Scanner in = new Scanner(System.in);
	
	@Override
	Producto tipoProducto() {		
		System.out.println("Ingrese el ID del Flor");
		int id = in.nextInt();
		System.out.println("Ingrese el precio del Flor");
		float precio = in.nextFloat();
		System.out.println("Ingrese el color de la Flor");
		String color = in.next();
		System.out.println("Ingrese la cantidad de Flores");
		int cant = in.nextInt();		
		
		return new Flor(id, cant, precio, color);		
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
