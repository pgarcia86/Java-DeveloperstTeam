package s3_03_pablogarciabarros;

import java.util.Scanner;

public class FabricaTicket extends FabricaAbstracta{

	Scanner in = new Scanner(System.in);

	//IMPLEMENTADO DE LA FABRICAABSTRACTA
	Ticket nuevoTicket() {
		System.out.println("Ingrese el id de la comanda");
		int id = in.nextInt();
		System.out.println("Ingrese la fecha de la comanda(formato AAAA-MM-DD)");
		String fecha = in.next();
		return new Ticket(id, fecha);		
	}
	
	
	//METODOS HEREDADOS DE FABRICAABSTRACTA NO IMPLEMENTADOS
	@Override
	Producto nuevoProducto() {
		return null;
	}

	@Override
	ConexionBaseDatos nuevaConexion() {
		return null;
	}


	@Override
	Producto cargarProducto(int id, int cant, float precio, int tipoProducto) {
		// TODO Auto-generated method stub
		return null;
	}


	
	@Override
	DetalleComanda nuevoDetalleComanda(int idComanda, int idProducto, int cantidad) {
		// TODO Auto-generated method stub
		return null;
	}
}
