package s3_03_pablogarciabarros;

import java.util.Scanner;

public class FabricaFlor extends FabricaAbstracta{

	@Override
	Producto nuevoProducto() {
		
		
		Scanner in = new Scanner(System.in);
		
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
	Ticket nuevoTicket() {
		return null;
	}

	@Override
	ConexionBaseDatos nuevaConexion() {
		return null;
	}

}
