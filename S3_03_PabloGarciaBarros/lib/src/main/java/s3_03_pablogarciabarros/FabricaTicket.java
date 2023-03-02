package s3_03_pablogarciabarros;

import java.util.Scanner;

public class FabricaTicket extends FabricaAbstracta{

	Scanner in = new Scanner(System.in);

	@Override
	Producto nuevoProducto() {
		return null;
	}
	
	Ticket nuevoTicket() {
		System.out.println("Ingrese el id de la comanda");
		int id = in.nextInt();
		System.out.println("Ingrese la fecha de la comanda(formato AAAA-MM-DD)");
		String fecha = in.next();
		return new Ticket(id, fecha);		
	}

	@Override
	ConexionBaseDatos nuevaConexion() {
		return null;
	}
}
