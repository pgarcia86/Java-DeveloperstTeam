package s3_03_pablogarciabarros;

import java.util.Scanner;

public class FabricaArbol extends FabricaAbstracta{

	Scanner in = new Scanner(System.in);
	
	
	//IMPLEMENTADO DE LA FABRICAABSTRACTA
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
		
		return new Arbol(id, cant, precio, 1, altura);
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
	Producto cargarProducto(int id, int cant, float precio, int tipoProducto) {
		return null;		
	}




	@Override
	DetalleComanda nuevoDetalleComanda(int idComanda, int idProducto, int cantidad) {
		// TODO Auto-generated method stub
		return null;
	}

}
