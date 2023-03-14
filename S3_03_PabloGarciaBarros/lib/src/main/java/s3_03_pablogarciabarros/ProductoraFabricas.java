package s3_03_pablogarciabarros;

public class ProductoraFabricas {
	
	public static FabricaAbstracta getFabrica(int opcion) {
		switch(opcion) {
			case 1:
				return new FabricaArbol();
			case 2:
				return new FabricaFlor();
			case 3:
				return new FabricaDecoracion();
			case 4:
				return new FabricaTicket();
			case 5:
				return new FabricaConexion();
			case 6:
				return new FabricaProducto();
			case 7:
				return new FabricaDetalleComanda();
			default:
				return null;					
		}
	} 
}
