package s3_03_pablogarciabarros;


//Uso esta clase para sobreescrir los metodos dependiendo de la Fabrica que lo utilice

public abstract class FabricaAbstracta {

	abstract Producto tipoProducto(); //Si creo un producto
	
	abstract Ticket creoTicket(); //Si creo un ticket
	
	abstract ConexionBaseDatos creoConexion(); //Si creo una conexion
	
}
