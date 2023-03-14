package s3_03_pablogarciabarros;

import java.util.Scanner;

public class FabricaDecoracion extends FabricaAbstracta{

	Scanner in = new Scanner(System.in);
	
	
	//IMPLEMENTADO DE LA FABRICAABSTRACTA
	@Override
	Producto nuevoProducto() {		
		
		System.out.println("Ingrese el ID de la Decoracion");
		int id = in.nextInt();
		System.out.println("Ingrese el precio de la Decoracion");
		float precio = in.nextFloat();
		System.out.println("Ingrese el material de la Decoracion");
		String material = in.next();
		System.out.println("Ingrese la cantidad de Decoraciones");
		int cant = in.nextInt();		
		
		return new Decoracion(id, cant, precio, 3, material);
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
