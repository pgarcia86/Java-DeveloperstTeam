package s3_03_pablogarciabarros;

public class FabricaTablaFlor extends FabricaAbstractaTabla{


	@Override
	Tabla nuevaTabla() {
		
		System.out.println("--------------SE ESTA CREANDO LA TABLA FLOR-----------");
		System.out.println();
		
		return new TablaFlor();
	}

}
