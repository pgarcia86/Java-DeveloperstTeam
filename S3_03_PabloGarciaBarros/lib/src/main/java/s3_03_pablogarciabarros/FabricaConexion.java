package s3_03_pablogarciabarros;

import java.util.Scanner;

public class FabricaConexion extends FabricaAbstracta{

	Scanner in = new Scanner(System.in);

	
	//IMPLEMENTADO DE LA FABRICAABSTRACTA
	@Override
	ConexionBaseDatos nuevaConexion() {
		System.out.println("Ingrese el hostname");
		String hostname = in.next();
		System.out.println("Ingrese el nombre de la base de datos");
		String nombreDB = in.next();
		System.out.println("Ingrese el usuario");
		String usuario = in.next();
		System.out.println("Ingrese el password");
		String password = in.next();
		return new ConexionBaseDatos(hostname, nombreDB, usuario, password);
	}
	
	
	
	//METODOS HEREDADOS DE FABRICAABSTRACTA NO IMPLEMENTADOS
	@Override
	Producto nuevoProducto() {
		return null;
	}

	@Override
	Ticket nuevoTicket() {
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
