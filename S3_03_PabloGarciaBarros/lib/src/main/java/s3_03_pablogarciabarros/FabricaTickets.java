package s3_03_pablogarciabarros;

import java.util.Scanner;

//Desde esta clase instancio un objeto de la clase Tickets, sin saber como es la clase tickets.


public class FabricaTickets extends FabricaAbstracta{

	Scanner in = new Scanner(System.in);
	
	@Override
	Ticket creoTicket() {		
		System.out.println("Ingrese el id de la comanda");
		int id = in.nextInt();
		System.out.println("Ingrese la fecha (formato AAAA-MM-DD)");
		String fecha = in.next();
		/*
		System.out.println("Ingrese el año (formato AAAA)");
		int anyo = in.nextInt();
		System.out.println("Ingrese el mes (formato MM)");
		int mes = in.nextInt();
		System.out.println("Ingrese el dia (formato DD)");
		int dia = in.nextInt();
		*/
		return new Ticket(id, fecha);
	}

	@Override
	Producto tipoProducto() {
		return null;
	}

	@Override
	ConexionBaseDatos creoConexion() {
		return null;
	}

	
}
