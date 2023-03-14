package s3_03_pablogarciabarros;


//ESTA
public class ProductoraFabricaTabla {
	
	public static FabricaAbstractaTabla getFabrica(int opcion) {
		switch(opcion) {
			case 1:
				return new FabricaTablaTipoProducto();
			case 2:
				return new FabricaTablaProducto();
			case 3:
				return new FabricaTablaArbol();				
			case 4:
				return new FabricaTablaFlor();
			case 5:
				return new FabricaTablaDecoracion();
			case 6:
				return new FabricaTablaComandas();
			case 7:
				return new FabricaTablaDetalleComanda();
		}
		return null;
	}
}

