package s3_03_pablogarciabarros;

public class FabricaTablaDecoracion extends FabricaAbstractaTabla{

	@Override
	Tabla nuevaTabla() {

		System.out.println("--------------SE ESTA CREANDO LA TABLA DECORACION-----------");
		System.out.println();
		
		return new TablaDecoracion(); 
	}
}
