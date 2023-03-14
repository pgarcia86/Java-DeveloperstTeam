package s3_03_pablogarciabarros;

public class FabricaTablaProducto extends FabricaAbstractaTabla{

	@Override
	Tabla nuevaTabla() {
		
		System.out.println("--------------SE ESTA CREANDO LA TABLA PRODUCTO-----------");
		System.out.println();
		
		return new TablaProducto();		
	}
}
