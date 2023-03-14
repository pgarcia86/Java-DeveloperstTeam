package s3_03_pablogarciabarros;

public class FabricaTabla extends FabricaAbstractaTabla{
	
	Tabla getFabricaTabla(int opcion) {
		switch(opcion) {
			case 1:
				return new FabricaTablaTipoProducto().nuevaTabla();
			case 2:
				return new FabricaTablaProducto().nuevaTabla();
			case 3:
				return new FabricaTablaArbol().nuevaTabla();				
			case 4:
				return new FabricaTablaFlor().nuevaTabla();
			case 5:
				return new FabricaTablaDecoracion().nuevaTabla();
			case 6:
				return new FabricaTablaComandas().nuevaTabla();
			case 7:
				return new FabricaTablaDetalleComanda().nuevaTabla();
		}
		return null;
	}

	@Override
	Tabla nuevaTabla() {
		// TODO Auto-generated method stub
		return null;
	}

}
