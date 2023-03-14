package s3_03_pablogarciabarros;

public class FabricaTablaDetalleComanda extends FabricaAbstractaTabla{


	@Override
	Tabla nuevaTabla() {

		System.out.println("--------------SE ESTA CREANDO LA TABLA DETALLE COMANDA-----------");
		System.out.println();
		
		return new TablaDetalleComanda();
	}

}
