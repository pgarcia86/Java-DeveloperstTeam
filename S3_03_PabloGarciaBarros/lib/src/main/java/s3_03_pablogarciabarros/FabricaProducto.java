package s3_03_pablogarciabarros;

public class FabricaProducto extends FabricaAbstracta{

	
	
	@Override
	Producto cargarProducto(int id, int cant, float precio, int tipoProducto) {
		// TODO Auto-generated method stub
		return new Producto(id, cant, precio, tipoProducto);
	}
	
	
	
	//METODOS HEREDADOS DE FABRICAABSTRACTA PERO NO IMPLEMENTADOS
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
	DetalleComanda nuevoDetalleComanda(int idComanda, int idProducto, int cantidad) {
		// TODO Auto-generated method stub
		return null;
	}



}
