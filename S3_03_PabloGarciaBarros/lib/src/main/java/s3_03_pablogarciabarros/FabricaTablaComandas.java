package s3_03_pablogarciabarros;

public class FabricaTablaComandas extends FabricaAbstractaTabla{

	@Override
	Tabla nuevaTabla() {
			System.out.println("--------------SE ESTA CREANDO LA TABLA COMANDAS-----------");
			System.out.println();
			
			return new TablaComandas();	
	}
}
