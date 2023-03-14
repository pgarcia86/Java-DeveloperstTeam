package s3_03_pablogarciabarros;


//ESTA FABRICA ME CREA LAS FABRICAS DE PRODUCTO NECESARIAS PARA LUEGO UTILIZARLAS
public abstract class FabricaAbstracta {

	abstract Producto nuevoProducto();
	
	abstract Ticket nuevoTicket();
	
	abstract ConexionBaseDatos nuevaConexion();
	
	abstract Producto cargarProducto(int id, int cant, float precio, int tipoProducto);
	
	abstract DetalleComanda nuevoDetalleComanda(int idComanda, int idProducto, int cantidad);

}
