package s3_03_pablogarciabarros;

import java.util.Scanner;

//Desde esta clase instancio un objeto de la clase Decoracion, sin saber como es la clase decoracion.

public class FabricaDecoracion extends FabricaAbstracta{

	Scanner in = new Scanner(System.in);
	
	@Override
	Producto tipoProducto() {		
		System.out.println("Ingrese el ID de la Decoracion");
		int id = in.nextInt();
		System.out.println("Ingrese el precio de la Decoracion");
		float precio = in.nextFloat();
		System.out.println("Ingrese el material de la Decoracion");
		String material = in.next();
		System.out.println("Ingrese la cantidad de Decoraciones");
		int cant = in.nextInt();		
		
		return new Decoracion(id, cant, precio, material);
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
