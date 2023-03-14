package s3_03_pablogarciabarros;

import java.util.Scanner;

public class FabricaFlor extends FabricaAbstracta{

	Scanner in = new Scanner(System.in);
	
	//IMPLEMENTADO DE LA FABRICAABSTRACTA
	@Override
	Producto nuevoProducto() {		
		
		System.out.println("Ingrese el ID del Flor");
		int id = in.nextInt();
		System.out.println("Ingrese el precio del Flor");
		float precio = in.nextFloat();
		System.out.println("Ingrese el color de la Flor");
		String color = in.next();
		System.out.println("Ingrese la cantidad de Flores");
		int cant = in.nextInt();		
		
		return new Flor(id, cant, precio, 2, color);		
	}
	
	
	@Override
	Producto cargarProducto(int id, int cant, float precio, int tipoProducto) {
		return null;
	}

	
	//METODOS HEREDADOS DE FABRICAABSTRACTA NO IMPLEMENTADOS
	@Override
	Ticket nuevoTicket() {
		return null;
	}

	@Override
	ConexionBaseDatos nuevaConexion() {
		return null;
	}


	@Override
	DetalleComanda nuevoDetalleComanda(int idComanda, int idProducto, int cantidad) {
		// TODO Auto-generated method stub
		return null;
	}
}
