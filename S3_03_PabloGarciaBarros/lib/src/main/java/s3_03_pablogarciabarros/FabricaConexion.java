package s3_03_pablogarciabarros;

import java.util.Scanner;

//Desde esta clase instancio un objeto de la clase ConexionBaseDatos, sin saber como es la clase conexion.

public class FabricaConexion extends FabricaAbstracta{
	
	Scanner in = new Scanner(System.in);

	@Override
	Producto tipoProducto() {
		return null;
	}

	@Override
	Ticket creoTicket() {
		return null;
	}

	@Override
	ConexionBaseDatos creoConexion() {
		System.out.println("Ingrese el hostname");
		String host = in.next();
		System.out.println("Ingrese el nombre de la DB");
		String nombreDB = in.next();
		System.out.println("Ingrese el nombre de usuario");
		String usuario = in.next();
		System.out.println("Ingrese la contraseña");
		String password = in.next();
		
		return new ConexionBaseDatos(host, nombreDB, usuario, password);
	}

}
