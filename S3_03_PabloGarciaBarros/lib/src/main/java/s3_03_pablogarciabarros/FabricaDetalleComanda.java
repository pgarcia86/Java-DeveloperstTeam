package s3_03_pablogarciabarros;

public class FabricaDetalleComanda extends FabricaAbstracta{

	@Override
	Producto nuevoProducto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Ticket nuevoTicket() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	ConexionBaseDatos nuevaConexion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Producto cargarProducto(int id, int cant, float precio, int tipoProducto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	DetalleComanda nuevoDetalleComanda(int idComanda, int idProducto, int cantidad) {
		return new DetalleComanda(idComanda, idProducto, cantidad);
	}

}
